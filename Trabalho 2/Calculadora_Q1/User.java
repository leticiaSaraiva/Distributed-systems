import java.net.*;
import java.io.*;
import java.util.Scanner;


public class User {
	public static void main (String args[]) {
		
		TCPClient tcpclient = new TCPClient();
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Digite a operação: ");
		String msg = scan.next();
		//tcpclient.sendRequest(msg);  
		
		System.out.print("Digite o primeiro operando: ");
		Double element1 = scan.nextDouble();
		//tcpclient.sendRequest(Double.toString(element1));  

		System.out.print("Digite o segundo operando: ");
		Double element2 = scan.nextDouble();
		

		String data_send = String.valueOf(element1)+" " + msg + " " +String.valueOf(element2);

		System.out.println(data_send) ;

		tcpclient.sendRequest(data_send); 

				

		String data = tcpclient.getResponse();
		


		System.out.println("Received: "+ data) ; 

		tcpclient.close();
		
	}
}