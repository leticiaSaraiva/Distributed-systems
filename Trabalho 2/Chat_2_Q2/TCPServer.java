import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPServer {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	String mensagem = "";
	Scanner entrada = new Scanner(System.in);
	String data = "EOF:null";

	public void sendResponse(String msg) {
		try{
			this.out.writeUTF(msg);
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());}
		
	}


	public String getRequest() {
		try{
			return this.in.readUTF();
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());return "";}
		
	}
	public void close(){
		try{
			this.clientSocket.close();
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());}
		

	}


	public TCPServer() {
		
		try{
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			
			//while(true) {
				this.clientSocket = listenSocket.accept();
				System.out.println("conected");
					//Connection c = new Connection(clientSocket);
				this.in = new DataInputStream( clientSocket.getInputStream());
				this.out = new DataOutputStream( clientSocket.getOutputStream());
			
			//}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
