package Clases;
 
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import java.awt.*;

public class Body extends Sprite{
	
	public Body(Graphics2D g2d) {
		this.g2d = g2d;
		width = TAMAÑO;
		height = TAMAÑO;
	}
	
	public void setPosX(int x) {
		posX = x;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosY(int y) {
		posY = y;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void moveBody() {
		ellipse = new Ellipse2D.Double(posX, posY, TAMAÑO, TAMAÑO);
	}
	
    public void drawBody() {
    	
    	g2d.setColor(Color.decode("#319942"));
		g2d.fill(ellipse);
	}
    
	
	private final int TAMAÑO = 20;
	private Graphics2D g2d;
	private Ellipse2D ellipse;
}
