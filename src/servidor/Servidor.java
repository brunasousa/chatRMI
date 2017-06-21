package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import InterfacesServidor.CentralChat;

public class Servidor {
	
	public static void main(String args[]){
		CentralChatImp chat = new CentralChatImp();
		
		try {
			CentralChat stub = (CentralChat)UnicastRemoteObject.exportObject(chat, 0);
			
			Registry reg=null;
			try {
				System.out.println("Creating registry...");
				reg = LocateRegistry.createRegistry(1099);
			} catch(Exception e){
				try {
					reg = LocateRegistry.getRegistry(1099);
				} catch(Exception ee){ 
					System.exit(0); 
				} 
			}
			reg.rebind("CentralChat", stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
