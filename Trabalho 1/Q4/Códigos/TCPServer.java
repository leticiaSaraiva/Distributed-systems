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
			data = in.readUTF();

			while(!data.equals("exit")){
				System.out.print("Client: ");
				System.out.println(data);	
				
				System.out.print("Server: ");
				mensagem = entrada.nextLine();
				out.writeUTF(mensagem);

				data = "EOF:null";

				while(data.equals("EOF:null")){
					data = in.readUTF();
				}
			}
			System.out.println("Disconnected client");	
		}catch (EOFException e){System.out.println("Disconnected client");
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}
