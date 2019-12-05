import java.net.*;
import java.io.*;
import java.util.Scanner;

public class User_Server {
	public static void main (String args[]) {



		TCPServer tcpserver = new TCPServer();
		MyThreadS mythread = new MyThreadS(tcpserver);
		Scanner entrada = new Scanner(System.in);
		String mensagem = "";
		while(!mensagem.equals("exit")){
				mensagem = entrada.nextLine();
				tcpserver.sendResponse(mensagem);
		}
		System.exit(0);
	}
}

		

class MyThreadS extends Thread{
	TCPServer tcp;
	String resposta = "EOF:null";

	public MyThreadS(TCPServer tcps){
			this.tcp = tcps;
			this.start();	
	}
	public void run(){
		
			
			while(!this.resposta.equals("exit") && !resposta.equals(null) ){
		//		resposta = "EOF:null";
			//	while(resposta.equals("EOF:null")){
					this.resposta =  this.tcp.getRequest();
					//System.out.println("fff: " + resposta);
				
		//		}	
			
				System.out.println("User: " + this.resposta);
			}
			System.exit(0);
		}
}
