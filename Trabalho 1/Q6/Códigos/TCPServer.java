import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	String mensagem = "";
	Scanner entrada = new Scanner(System.in);
	String data = "EOF:null";
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out = new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public String retornar_hora(){
		Date date = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		return Integer.toString(hours)+ ":" + Integer.toString(min) +":" + Integer.toString(sec)+ "\n";
	}

	public void run(){
		try {			
			ThreadServer threadserver = new ThreadServer(in);

			while(true){
				mensagem = entrada.nextLine();
				if(mensagem.equals("hour")){
					out.writeUTF(retornar_hora());
				}
				else out.writeUTF(mensagem);
			}
		}catch (EOFException e){System.out.println("Disconnected client1");
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}

class ThreadServer extends Thread{
	DataInputStream in;
	String resposta = "EOF:null";

	public ThreadServer(DataInputStream inp){
			in = inp;
			this.start();
	}
	
	public void run(){
		try{
			while(!resposta.equals("exit")){
				resposta = "EOF:null";
				while(resposta.equals("EOF:null")){
					resposta = in.readUTF();
				}	
				System.out.println("Client: " + resposta);
			}
		}catch (EOFException e){System.out.println("Disconnected client");}
		 catch(IOException e) {System.out.println("readline:x"+e.getMessage());}
	}	
}
