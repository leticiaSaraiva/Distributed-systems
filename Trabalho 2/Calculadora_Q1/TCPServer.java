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
	String oper;
	String port;
	double valor1;
	double valor2;
	double value;	

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

 			Calculadora calc = new Calculadora();
 			String mensage = getRequest();
 			String split_msg[] = new String[3];
  			split_msg = mensage.split(" ");
  				
  			this.valor1 = Double.parseDouble(split_msg[0]);
			this.valor2 = Double.parseDouble(split_msg[2]); 

 			this.oper = split_msg[1] ;
 			
 			//this.valor1 = Double.parseDouble(getRequest());
			
			//this.valor2 = Double.parseDouble(getRequest());
			 	

			if(oper.equals("+")){
				//value = valor1 + valor2;
				this.value = calc.add(valor1, valor2);
				
				sendResponse(String.valueOf(value));
			}
			else if(oper.equals("-")){
				this.value = calc.sub(valor1,valor2);
				sendResponse(String.valueOf(value));
			}
			else if(oper.equals("*")){
				this.value = valor1 * valor2;
				sendResponse(Double.toString(value));
			}
			else if(oper.equals("/")){
				this.value = valor1 / valor2;
				sendResponse(Double.toString(value));
			}
			else{
				sendResponse("Operação Inválida\n");
			}
				
    	
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}
