import java.net.*;
import java.io.*;
import java.util.*;
public class Proxy {
	TCPClient tcpclient;


	public double add(double element1, double element2) {
		String data_send = String.valueOf(element1)+" + " +String.valueOf(element2);
		System.out.println(data_send) ;

		this.tcpclient.sendRequest(data_send); 
		String data = this.tcpclient.getResponse();
		return Double.parseDouble(data);
	}

	public double sub(double element1, double element2) {
		String data_send = String.valueOf(element1)+" - " +String.valueOf(element2);
		System.out.println(data_send) ;

		this.tcpclient.sendRequest(data_send); 
		String data = this.tcpclient.getResponse();
		return Double.parseDouble(data);
	}

	public double mult(double element1, double element2) {
		String data_send = String.valueOf(element1)+" * " +String.valueOf(element2);
		System.out.println(data_send) ;

		this.tcpclient.sendRequest(data_send); 
		String data = this.tcpclient.getResponse();
		return Double.parseDouble(data);
	}

	public double div(double element1, double element2) {
		if(element2 != 0){
			String data_send = String.valueOf(element1)+" / " +String.valueOf(element2);
			this.tcpclient.sendRequest(data_send);
		}
		else System.out.println("Segundo operando é zero"); 
		 
		String data = this.tcpclient.getResponse();
		return Double.parseDouble(data);
	}
	public void close(){
		this.tcpclient.close();
	}


	public Proxy(){
		this.tcpclient = new TCPClient();

	}
}