import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Despachante {

	public String Invoke(String mensage){

 		String split_msg[] = new String[3];
 		split_msg = mensage.split(" ");
		String valor1 = split_msg[0];
		String valor2 = split_msg[2]; 

 		String oper = split_msg[1] ;
 		Esqueleto esq = new Esqueleto();

 		String result;
		if(oper.equals("+")){
			result = esq.add(oper,valor1,valor2);		
		}
		else if(oper.equals("-")){
			result = esq.sub(oper,valor1,valor2);	
		}
		else if(oper.equals("*")){
			result = esq.mult(oper,valor1,valor2);
		}
		else if(oper.equals("/")){
			result = esq.div(oper,valor1,valor2);
		}
		else{
			return "Operação Inválida\n";
		}
				  		
		return result;
	}
}