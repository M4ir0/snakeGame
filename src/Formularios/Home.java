package Formularios;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Clases.MiRandom;

public class Home extends JFrame{

	public Home() {
		this.setTitle("Game Snake - Home");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		iniciar = new JButton("Iniciar");
		
		iniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				ContainerTrack containerTrack = new ContainerTrack();
				containerTrack.setVisible(true);
				setVisible(false);
				
			}
		});
		
		contenedor = new JPanel();
		
		JLabel jLabel = new JLabel();
		
		MiRandom random = new MiRandom();
		
		jLabel.setText("X: "+random.nextValueX(this.getWidth()) + " Y: "+random.nextValueY(this.getHeight()));
		
		contenedor.add(iniciar);
		contenedor.add(jLabel);
		
		
		this.add(contenedor);
		
	}
	
	JPanel contenedor;
	JButton iniciar;
	
	public static void main(String[] args) {

		new Home().setVisible(true);
	}
}
