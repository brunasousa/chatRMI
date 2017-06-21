package cliente;

import InterfacesCliente.MsgCliente;

public class MsgClienteImp implements MsgCliente{
	String nome;
	JanelaChat janela;
	@Override
	public void notificarCliente(String mensagem) {
		System.out.println(mensagem);
		janela.jtaConversas.setText(janela.jtaConversas.getText()+"\n"+mensagem);
	}

	@Override
	public String nomeCliente() {
		// TODO Auto-generated method stub
		return nome;
	}
	
	public void setJanela(JanelaChat janela){
		this.janela = janela;
	}

}
