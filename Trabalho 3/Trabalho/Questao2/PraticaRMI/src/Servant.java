import java.sql.*;
import java.rmi.*;

public class Servant implements PeapleList{
	private Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	public Servant()throws RemoteException{
		this.connection = new ConnectionFactory().getConnection();;
		System.out.println("Connected");
	}
	
	public String getNome(String CPF) throws RemoteException{
		String sql = "SELECT * FROM PEAPLE WHERE CPF = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, CPF);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			String nome = rs.getString("nome");			
			stmt.close();
			
			return nome;
		}catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return null;
	}
	
	public int getIdade(String CPF) throws RemoteException{
		
		String sql = "SELECT * FROM PEAPLE WHERE CPF = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, CPF);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			int idade = rs.getInt("Idade");			
			stmt.close();
			
			return idade;
		}catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return -1;
	}
	
	public boolean inserir(String CPF,String Nome, int Idade) throws RemoteException{
		String sql = "INSERT INTO PEAPLE (NOME, CPF, IDADE) VALUES (?, ?, ?)";
		try{
		    ps = connection.prepareStatement(sql);
			
			ps.setString(1, Nome);
			ps.setString(2, CPF);
			ps.setInt(3, Idade);

			int rowsAffected = ps.executeUpdate();
			ps.close();
			System.out.println(rowsAffected);
			if(rowsAffected > 0){
				return true;
			}
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}	
		return false;
	}

	public boolean excluir(String CPF) throws RemoteException{
		String sql = "DELETE FROM PEAPLE WHERE CPF = ?";
		try{
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, CPF);
			
			int rowsAffected = ps.executeUpdate();
			ps.close();
			if(rowsAffected > 0){
				return true;
			}
		}catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return false;	
	}
}
