import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient {
	private DataOutputStream out;
	private DataInputStream in;
	private Socket s;
	
	public void sendRequest(String msg){
		try{
			this.out.writeUTF(msg);
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());}
		
	

	}
	public  String getResponse(){
		try{
			return this.in.readUTF();
		} catch(IOException e) {//System.out.println("readline:"+e.getMessage());
		  return "";}
		
	}
	public void close(){
		try{
			this.s.close();
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());}
		

	}


	public TCPClient() {
		try{
			int serverPort = 7896;
			String localhost = "localhost";
			this.s = new Socket(localhost, serverPort);    
			
			this.out = new DataOutputStream( s.getOutputStream());
			this.in = new DataInputStream( s.getInputStream());


		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("Disconnected server");
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}//finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}


