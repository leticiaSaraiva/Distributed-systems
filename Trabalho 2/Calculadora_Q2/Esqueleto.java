import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Esqueleto {

	public String add(String oper, String valor1, String valor2){
		Calculadora calc = new Calculadora();
		Double value1 = Double.parseDouble(valor1);
		Double value2 = Double.parseDouble(valor2);
		return  String.valueOf(calc.add(value1,value2));
				
	}



	public String sub(String oper, String valor1, String valor2){
		Calculadora calc = new Calculadora();
		Double value1 = Double.parseDouble(valor1);
		Double value2 = Double.parseDouble(valor2);
		return String.valueOf(calc.sub(value1,value2));

	}


	public String mult(String oper, String valor1, String valor2){
		Calculadora calc = new Calculadora();
		Double value1 = Double.parseDouble(valor1);
		Double value2 = Double.parseDouble(valor2);
		return  String.valueOf(calc.mult(value1,value2));
				
		
	}


	public String div(String oper, String valor1, String valor2){
		Calculadora calc = new Calculadora();
		Double value1 = Double.parseDouble(valor1);
		Double value2 = Double.parseDouble(valor2);
		return  String.valueOf(calc.div(value1,value2));
				
		
	}
	

		
}
