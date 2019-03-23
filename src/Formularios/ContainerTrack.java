package Formularios;
import javax.swing.*;
import java.awt.*;

public class ContainerTrack extends JFrame{

	public ContainerTrack() {
		this.setTitle("Game Snake - Playing");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		//container = new JPanel();
		//container.setBackground(new Color(50, 200, 100));
		//container.setLayout(new BorderLayout());
		
		JLabel etiqueta = new JLabel("Puntaje:10 Vidas:4");
		
		Track trackGame = new Track(this.getWidth(), this.getHeight(), etiqueta);
		//trackGame.setBackground(Color.WHITE);
		//container.add(trackGame, BorderLayout.CENTER);
		
		trackGame.setFocusable(true);
		
		this.add(etiqueta, BorderLayout.NORTH);		
		
		this.add(trackGame, BorderLayout.CENTER);
	}
}
