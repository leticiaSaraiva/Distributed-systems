import java.net.*;
import java.io.*;
import java.util.Scanner;


public class User_Client {
	public static void main (String args[]) {
		
		String mensagem = "";
		TCPClient tcpclient = new TCPClient();
				
		Scanner entrada = new Scanner(System.in);
			
		Mythread thread = new Mythread(tcpclient);
		
		mensagem = entrada.nextLine();
		tcpclient.sendRequest(mensagem);

		while(!mensagem.equals("exit") ){
				mensagem = entrada.nextLine();
				tcpclient.sendRequest(mensagem);
			}

		//tcpclient.close();
		System.exit(0);		
	}
}

class Mythread extends Thread{
	TCPClient tcpclient;
	String respostaServidor;

	public Mythread(TCPClient tcpc){
			tcpclient = tcpc;
			this.start();	
	}
	public void run(){
		//try{
			
				respostaServidor = "EOF:null";
				while( !respostaServidor.equals("exit") && !respostaServidor.equals(null)){
					respostaServidor = tcpclient.getResponse();
				
			
				System.out.println("User_Server: " + respostaServidor);
				}
				this.tcpclient.close();
				System.exit(0);
		//}catch (EOFException e){System.out.println("Disconnected server");}
		// catch(IOException e) {System.out.println(e.getMessage());}	
	}	
}
