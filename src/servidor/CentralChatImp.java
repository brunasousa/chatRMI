package servidor;

import java.rmi.RemoteException;
import java.util.ArrayList;

import InterfacesCliente.MsgCliente;
import InterfacesServidor.CentralChat;

public class CentralChatImp implements CentralChat{
	ArrayList<MsgCliente> clientes = new ArrayList<MsgCliente>();
	@Override
	public void registrarCliente(MsgCliente mc) {
		// TODO Auto-generated method stub
		clientes.add(mc);
		try {
			enviarMensagem(mc.nomeCliente()+" acabou de entrar.", null);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void enviarMensagem(String mensagem, MsgCliente mc) {
		if(mc==null){
			for(MsgCliente m : clientes)
			{
				try {
					m.notificarCliente(mensagem);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			if(clientes.contains(mc)){
				for(MsgCliente m : clientes)
				{
					try {
						m.notificarCliente(mc.nomeCliente()+" disse: "+ mensagem);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void retirarCliente(MsgCliente mc) throws RemoteException {
		if(clientes.contains(mc)){
			clientes.remove(mc);
			enviarMensagem(mc.nomeCliente()+" saiu.", null);
		}
		
	}

}