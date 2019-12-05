import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient {
	public static void main (String args[]) {
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket(args[0], serverPort);    
			
			DataOutputStream out = new DataOutputStream( s.getOutputStream());
			DataInputStream in = new DataInputStream( s.getInputStream());

			String mensagem = "";
			Scanner entrada = new Scanner(System.in);
			
			Mythread thread = new Mythread(in);
			
			mensagem = entrada.nextLine();
			out.writeUTF(mensagem);
			
			while(!mensagem.equals("exit") ){
				mensagem = entrada.nextLine();
				out.writeUTF(mensagem);
			}

		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("Disconnected server");
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}


class Mythread extends Thread{
	DataInputStream in;
	String respostaServidor = "EOF:null";

	public Mythread(DataInputStream inp){
			in = inp;
			this.start();	
	}
	public void run(){
		try{
			while(true){
				respostaServidor = "EOF:null";
				while(respostaServidor.equals("EOF:null")){
					respostaServidor = in.readUTF();
				}	
			
				System.out.println("Server: " + respostaServidor);
			}
		}catch (EOFException e){System.out.println("Disconnected server");}
		 catch(IOException e) {System.out.println(e.getMessage());}	
	}	
}