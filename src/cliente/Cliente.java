package cliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import javax.swing.JOptionPane;

import InterfacesCliente.MsgCliente;
import InterfacesServidor.CentralChat;

public class Cliente {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		MsgClienteImp mc = new MsgClienteImp();
		int opcao = 0;
		System.out.println("Digite o nome do cliente: ");
		mc.nome = JOptionPane.showInputDialog("Qual � o seu nome?");
		//mc.nome = sc.next();
		while(mc.nome != null && mc.nome.isEmpty()){
			mc.nome = JOptionPane.showInputDialog("Digite um novo valido. Qual � o seu nome?");
		}
		
		if(mc.nome != null){
			try {
				MsgCliente m = (MsgCliente)UnicastRemoteObject.exportObject(mc,0);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String host="localhost";
			try {
				Registry registry = LocateRegistry.getRegistry(host);
				CentralChat stub = (CentralChat) registry.lookup("CentralChat");
				if (stub!=null){
					JanelaChat jc = new JanelaChat(stub,mc);
					jc.setTitle(mc.nomeCliente());
					mc.setJanela(jc);
					stub.registrarCliente((MsgCliente)mc);
					System.out.println("Cliente Registrado");
					//stub.enviarMensagem("Olá", (MsgCliente)mc);
				}
			} catch (Exception e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
			}
		}
				
		
	
	}
}
