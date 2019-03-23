package Formularios;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sorpresa extends JFrame{

	public Sorpresa() {
		setTitle("");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(500, 500);
				
		add(new Fondo(), BorderLayout.CENTER);
	}
	
}

class Fondo extends JButton{//CAMBIAR ELEMENTO
	
	BufferedImage img;
	
	public Fondo() {
		try {
			
			img = ImageIO.read(new File("src/Images/sorpresa.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		
	}
}