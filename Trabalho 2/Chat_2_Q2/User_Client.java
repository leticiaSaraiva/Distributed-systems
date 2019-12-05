import java.net.*;
import java.io.*;
import java.util.Scanner;


public class User_Client {
	public static void main (String args[]) {
		
		String mensagem = "";
		
				
		Scanner entrada = new Scanner(System.in);
		
		Proxy_Client proxy  = new Proxy_Client();
		
		while(!mensagem.equals("exit") ){
				mensagem = entrada.nextLine();
				proxy.send(mensagem);
			}

		//tcpclient.close();
		System.exit(0);		
	}
}
