import java.net.*;
import java.io.*;


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

	public void sendResponse(String msg) {
		try{
			out.writeUTF(msg);
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());}
		
	}


	public String getRequest() {
		try{
			return in.readUTF();
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());return "";}
		
	}

	public Connection (Socket aClientSocket) {
		try {
		
 			clientSocket = aClientSocket;
			this.in = new DataInputStream( clientSocket.getInputStream());
			this.out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}

	}

	
	public void run(){
 		
 		try {		

	 		String mensage = getRequest();

			Despachante desp = new Despachante();
			String send = desp.Invoke(mensage);
			sendResponse(send);
    	
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}
