package InterfacesCliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MsgCliente extends Remote {

	public void notificarCliente(String mensagem) throws RemoteException; 
	public String nomeCliente() throws RemoteException;
}
