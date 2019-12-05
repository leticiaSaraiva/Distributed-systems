import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

public class Client {
	public static void main(String args[]){
		if(System.getSecurityManager() == null){
        	System.setSecurityManager(new SecurityManager());
        } else System.out.println("Already has a security manager, so cant set RMI SM");
		
		Scanner in = new Scanner(System.in);
		PeapleList peaplelist = null;
		boolean terminar = false;
		boolean resultInsert = false;
		boolean resultDelete = false;
		int idade, opcao;
		String nome, cpf;

		try{
			peaplelist  = (PeapleList) Naming.lookup("//localhost/PeapleList");

			while(!terminar){
				System.out.println("-------------OPÇÕES-------------\n");
				System.out.println("| 0 - Sair");
				System.out.println("| 1 - Adicionar");
				System.out.println("| 2 - Excluir");
				System.out.println("| 3 - Buscar nome");
				System.out.println("| 4 - Buscar idade");

				opcao = in.nextInt();
				in.nextLine(); //Eliminar buffer
				switch(opcao){
					case 0:
						System.out.println("Tchau!");
						terminar = true;
						break;
					case 1:
						System.out.println("CPF: ");
						cpf = in.nextLine();
						System.out.println("Nome: ");
						nome = in.nextLine();
						System.out.println("Idade: ");
						idade = in.nextInt();
						resultInsert = peaplelist.inserir(cpf,nome,idade);
						if(resultInsert){
							System.out.println("Adicionado!");
						}else{
							System.out.println("Erro!");
						}
						break;
					case 2:
						System.out.println("CPF: ");
						cpf = in.nextLine();
						resultDelete = peaplelist.excluir(cpf);
						if(resultDelete){
							System.out.println("Deletado!");
						}else{
							System.out.println("Erro!");
						}
						break;
					case 3:
						System.out.println("CPF: ");
						cpf = in.nextLine();
						nome = peaplelist.getNome(cpf);
						System.out.println("Nome: " + nome);
						break;
					case 4:
						System.out.println("CPF: ");
						cpf = in.nextLine();
						idade = peaplelist.getIdade(cpf);
						System.out.println("Idade: " + idade);
						break;
					default:
						System.out.println("Opção inválida!");
						break;
				}
			}
			           
		}catch(RemoteException e) {System.out.println("allShapes: " + e.getMessage());
	    }catch(Exception e) {System.out.println("Lookup: " + e.getMessage());}
		
	}

}
