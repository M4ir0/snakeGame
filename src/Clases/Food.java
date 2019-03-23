package Clases;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Food extends Sprite{

	public Food(Graphics2D g2d) {
		this.g2d = g2d;
		width = TAMAÑO;
		height = TAMAÑO;
		
		colorAleatorio = new Random();
	    //color = new Color(colorAleatorio.nextInt(200), colorAleatorio.nextInt(200), colorAleatorio.nextInt(200));
		color = new Color(200, 50, 0);
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
	
	public void drawFood() {
		g2d.setColor(color);
		g2d.fillRect(posX, posY, TAMAÑO, TAMAÑO);
	}
	
	private Graphics2D g2d;
	private final int TAMAÑO = 20;
	private Color color;
	private Random colorAleatorio;
}
