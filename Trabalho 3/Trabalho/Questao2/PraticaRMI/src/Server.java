import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	public static void main(String[] args) {
        PeapleList stub = null;
        System.setSecurityManager(new SecurityManager());
        try{
            PeapleList peaplelist = new Servant();
            stub = (PeapleList) UnicastRemoteObject.exportObject(peaplelist,0);
            Naming.rebind("PeapleList", peaplelist);
        }
        catch(Exception e) {
            System.out.println("PeapleList server main " + e.getMessage());    
        }
	}
}
