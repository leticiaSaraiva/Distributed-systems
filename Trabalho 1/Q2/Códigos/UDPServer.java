import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

public class UDPServer{
    public static void main(String args[]){ 
    	DatagramSocket aSocket = null;
		try{
	    	aSocket = new DatagramSocket(6789);
			
 			while(true){
 				byte[] buffer = new byte[1000];
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  				aSocket.receive(request);     
    			
  				DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
    				request.getAddress(), request.getPort());
    			
  				//Convertendo mensagem para string
  				String mensage = new String(reply.getData());
  				
  				String split_msg[] = new String[3];
  				split_msg = mensage.split(" ");
  				
  				double valor1 = Double.parseDouble(split_msg[0]);
				double valor2 = Double.parseDouble(split_msg[2]);  				

				double value  = 0;
				int error = 0; 
				if(split_msg[1].equals("+")){
					value = valor1+ valor2;
				}else if(split_msg[1].equals("-")){
					value = valor1 - valor2;
				}else if(split_msg[1].equals("*")){
					value = valor1 * valor2;
				}else if(split_msg[1].equals("/")){
					value = valor1 / valor2;
				}else{
					error = 1;	
				}

				if(error == 0 ){
					String saida =  String.valueOf(value);
					byte[] resposta = saida.getBytes();
					DatagramPacket resp = new DatagramPacket(resposta, resposta.length, 
	    				request.getAddress(), request.getPort());

	  				aSocket.send(resp);
  				}else{
  					String erro = "Operador Inválido";
					byte[] respos = erro.getBytes();
					DatagramPacket resp = new DatagramPacket(respos, respos.length, 
    					request.getAddress(), request.getPort());
  					aSocket.send(resp);
  				}
    		}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}
