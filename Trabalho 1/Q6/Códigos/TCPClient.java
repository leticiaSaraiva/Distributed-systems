import java.net.*;
import java.io.*;
import java.util.*;
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
				if(mensagem.equals("hour")){
					out.writeUTF(retornar_hora());
				}
				else out.writeUTF(mensagem);
			}
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("Disconnected server");
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }

    public static String retornar_hora(){
		Date date = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		return Integer.toString(hours)+ ":" + Integer.toString(min) +":" + Integer.toString(sec)+ "\n";
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