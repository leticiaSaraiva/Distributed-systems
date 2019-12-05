import java.net.*;
import java.io.*;
import java.util.Scanner;

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
	public void run(){
		try {
			ThreadServer threadserver = new ThreadServer(in);

			while(true){
				mensagem = entrada.nextLine();
				out.writeUTF(mensagem);
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
