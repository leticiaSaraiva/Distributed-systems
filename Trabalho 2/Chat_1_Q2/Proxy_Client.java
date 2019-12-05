
class Proxy_Client {
	TCPClient tcpclient ;

	public void send(String msg){
		this.tcpclient.sendRequest(msg);
	}
	public Proxy_Client(){
		this.tcpclient = new TCPClient();
		MyThread mythread = new MyThread(this.tcpclient);
	}
}

class MyThread extends Thread{
	TCPClient tcpclient;
	String respostaServidor;

	public MyThread(TCPClient tcpc){
		this.tcpclient = tcpc;
		this.start();	
	}
	public void run(){
		respostaServidor = "EOF:null";
		while( !respostaServidor.equals("exit") && !respostaServidor.equals(null)){
			respostaServidor = tcpclient.getResponse();	
			System.out.println("User_Server: " + respostaServidor);
		}
		this.tcpclient.close();
		System.exit(0);	
	}	
}
