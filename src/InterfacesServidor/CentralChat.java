package InterfacesServidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

import InterfacesCliente.MsgCliente;

public interface CentralChat extends Remote{
	public void registrarCliente(MsgCliente mc) throws RemoteException; 
	public void enviarMensagem(String mensagem, MsgCliente mc)  throws RemoteException;
	public void retirarCliente(MsgCliente mc) throws RemoteException;
}
