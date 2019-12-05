import java.net.*;
import java.io.*;
import java.util.*;
public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			List<User_Save> lista_cliente = new ArrayList<>();
			
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket,lista_cliente);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	String oper;
	String port;
	double valor1;
	double valor2;
	double value;
	List<User_Save> lista_cliente;
	public Connection (Socket aClientSocket,List<User_Save> lista_cliente) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.lista_cliente = lista_cliente;
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {
			String ip = clientSocket.getRemoteSocketAddress().toString();	
			String split_msg[] = new String[2];
  			split_msg = ip.split(":");
  			split_msg[0] = split_msg[0].replace("/","");
			System.out.println(split_msg[0]+" "+split_msg[1]);
			
			this.port = split_msg[1];
			int state = 0;
			//state = 0 faz tudo
			//state = 1 leu o operador
			//state = 2 leu o primeiro valor
			if(lista_cliente.size() != 0){			
				int i = 0;
				for(i = 0; i <lista_cliente.size(); i++){
					if(lista_cliente.get(i).port.equals(this.port)){
						System.out.println(this.port);

						if(lista_cliente.get(i).element1 != 0.0){
							state = 2;
							this.oper = lista_cliente.get(i).operacao;
							this.valor1 = lista_cliente.get(i).element1;
						}
						else {
							state = 1;
							this.oper = lista_cliente.get(i).operacao;
						}
						lista_cliente.remove(i);
					}
				}
			}
			out.writeUTF(Integer.toString(state));
			switch(state){
				case 0:					
					oper = in.readUTF();
					valor1 = Double.parseDouble(in.readUTF());
					valor2 = Double.parseDouble(in.readUTF());
					break;

				case 1:
					valor1 = Double.parseDouble(in.readUTF());
					valor2 = Double.parseDouble(in.readUTF());
					break;

				case 2:
					valor2 = Double.parseDouble(in.readUTF());
					break;

				default:
					break;
			}

			if(oper.equals("+")){
				value = valor1 + valor2;
				out.writeUTF(String.valueOf(value));
			}else if(oper.equals("-")){
				value = valor1 - valor2;
				out.writeUTF(String.valueOf(value));
			}else if(oper.equals("*")){
				value = valor1 * valor2;
				out.writeUTF(Double.toString(value));
			}else if(oper.equals("/")){
				value = valor1 / valor2;
				out.writeUTF(Double.toString(value));
			}else{
				out.writeUTF("Operação Inválida\n");
			}
			
		}catch (EOFException e){
			if(oper != null){	
				if(valor1 == 0.0) lista_cliente.add(new User_Save(port,oper));
				
				else lista_cliente.add(new User_Save(port,oper,valor1));
			
				System.out.println("Estado salvo!");
			}
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}
class User_Save {
	String port;
	String operacao;
	double element1;
	public User_Save(String port, String operacao, double element1){
		this.port = port;
		this.operacao = operacao;
		this.element1 = element1;
	}
	public User_Save(String port, String operacao){
		this.port = port;
		this.operacao = operacao;
	}
}
