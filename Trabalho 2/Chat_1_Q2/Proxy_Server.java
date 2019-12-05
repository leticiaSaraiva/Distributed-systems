class Proxy_Server {
	TCPServer tcpserver ;

	public void send(String msg){
		this.tcpserver.sendResponse(msg);
	}
	public Proxy_Server(){
		this.tcpserver = new TCPServer();
		MyThreadS mythread = new MyThreadS(this.tcpserver);
	}
}


class MyThreadS extends Thread{
	TCPServer tcp;
	String resposta = "EOF:null";

	public MyThreadS(TCPServer tcps){
		this.tcp = tcps;
		this.start();	
	}
	public void run(){
		while(!this.resposta.equals("exit") && !resposta.equals(null) ){
			this.resposta =  this.tcp.getRequest();			
			System.out.println("User: " + this.resposta);
		}
		System.exit(0);
	}
}
