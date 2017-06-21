package cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import InterfacesCliente.MsgCliente;
import InterfacesServidor.CentralChat;

public class JanelaChat extends JFrame implements ActionListener, KeyListener{
	
	JPanel jpPrincipal = new JPanel();
	JTextArea jtaConversas = new JTextArea();
	JTextField jtTexto = new JTextField();
	JButton jbEnviar = new JButton("Enviar");
	CentralChat central;
	MsgCliente mc;
	
	public JanelaChat(final CentralChat central, MsgCliente mc){
		jbEnviar.setBounds(509, 215, 80, 50);
		jbEnviar.addActionListener(this);
		jtTexto.setSize(500, 50);
		jtTexto.setBounds(7, 215, 500, 50);
		jtTexto.addKeyListener(this);
		jtaConversas.setEditable(false);
		jtaConversas.setSize(500, 200);
		jtaConversas.setBounds(7, 10, 585, 200);
		jpPrincipal.add(jtaConversas);
		jpPrincipal.add(jtTexto);
		jpPrincipal.add(jbEnviar);
		jpPrincipal.setLayout(null);
		jpPrincipal.setSize(600,300);
		jpPrincipal.setBounds(0, 0, 600, 300);
		jpPrincipal.setVisible(true);
		jpPrincipal.setBackground(new Color(200, 162, 200));
		this.central = central;
		this.mc = mc;
		this.setResizable(false);
		this.setContentPane(jpPrincipal);
		this.setLayout(null);
		this.setSize(600, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent evt) { 
				sair();
				System.exit(0);
			}
		});
	}
	
	private void enviarParaCentral(){
		try {
			central.enviarMensagem(jtTexto.getText(), mc);
			jtTexto.setText("");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void sair(){
		try {
			central.retirarCliente(mc);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		enviarParaCentral();		
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_ENTER){   
	            enviarParaCentral();
	      }
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
}
