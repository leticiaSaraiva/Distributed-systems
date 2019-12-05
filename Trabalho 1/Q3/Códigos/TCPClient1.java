import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient1 {
	public static void main (String args[]) {
		Socket s = null;
		try{
			int serverPort = 7896;
			int clientPort = 4900;
			InetAddress aclient = InetAddress.getByName("127.0.0.1");
			s = new Socket(args[0], serverPort,aclient,clientPort);   
			Scanner scan = new Scanner(System.in);

			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out = new DataOutputStream( s.getOutputStream());

			int state = Integer.valueOf(in.readUTF());
			
			String operation;
			double element1;
			double element2;

			switch(state){
				case 0:
					System.out.print("Digite a operação: ");
					operation = scan.next();
					out.writeUTF(operation);  

					System.out.print("Digite o primeiro operando: ");
					element1 = scan.nextDouble();
					out.writeUTF(Double.toString(element1));  

					System.out.print("Digite o segundo operando: ");
					element2 = scan.nextDouble();
					out.writeUTF(Double.toString(element2)); 
					break;

				case 1:
					System.out.print("Digite o primeiro operando: ");
					element1 = scan.nextDouble();
					out.writeUTF(Double.toString(element1));  

					System.out.print("Digite o segundo operando: ");
					element2 = scan.nextDouble();
					out.writeUTF(Double.toString(element2)); 
					break;

				case 2:
					System.out.print("Digite o segundo operando: ");
					element2 = scan.nextDouble();
					out.writeUTF(Double.toString(element2)); 
					break;
				
				default:
					break;
			}
			String data = in.readUTF();	
			System.out.println("Received: "+ data) ; 

		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}

