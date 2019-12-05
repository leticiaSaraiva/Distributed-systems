import java.net.*;
import java.io.*;
import java.util.Scanner;

public class User_Server {
	public static void main (String args[]) {


		Proxy_Server proxy  = new Proxy_Server();

		Scanner entrada = new Scanner(System.in);
		String mensagem = "";
		while(!mensagem.equals("exit")){
				mensagem = entrada.nextLine();
				proxy.send(mensagem);
		}
		System.exit(0);
	}
}

		
