import java.rmi.*;

public interface PeapleList extends Remote {

	String getNome(String CPF) throws RemoteException;
	int getIdade(String CPF) throws RemoteException;
	boolean inserir(String CPF,String Nome, int Idade) throws RemoteException;
	boolean excluir(String CPF) throws RemoteException; 
}
