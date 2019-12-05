import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient {
	public static void main (String args[]) {
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket(args[0], serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out = new DataOutputStream( s.getOutputStream());
			String mensagem = "";
			Scanner entrada = new Scanner(System.in);
			String respostaServidor = "EOF:null";

			System.out.print("Client: ");
			mensagem = entrada.nextLine();
			out.writeUTF(mensagem);

			while(!mensagem.equals("exit") ){
				while(respostaServidor.equals("EOF:null")){
					respostaServidor = in.readUTF();
				}	
			
				System.out.println("Server: " + respostaServidor);
				respostaServidor = "EOF:null";

				System.out.print("Client: ");
				mensagem = entrada.nextLine();
				out.writeUTF(mensagem);
				
			}

		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("Disconnected server");
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}
