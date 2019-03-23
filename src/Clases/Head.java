package Clases;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Head extends Sprite{
	
	public Head(Graphics2D g2d) {
		
		this.g2d = g2d;
		width = TAMAÑO;
		height = TAMAÑO;
		
		try {
			//EL ANCHO Y ALTO DE LA IMAGEN TIENE QUE SER EL MISMO, "VARIABLE TAMAÑO" 
			img = cargarImagen("src/Images/head.png", TAMAÑO, TAMAÑO);
			//img = cargarImagen("head.png", TAMAÑO, TAMAÑO);
			
			imgL = rotarImagen(img, -90); //ROTAR HACIA LA IZQUIERDA
			imgU = rotarImagen(img, 0);   //ROTAR HACIA ARRIBA(POR DEFECTO)
			imgR = rotarImagen(img, 90);  //ROTAR HACIA LA DERECHA
			imgD = rotarImagen(img, 180); //ROTAR HACIA ABAJO
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("ERROR EN LA CLASE \"Head\"" + e.getMessage());
		}
	}
	
	
	public void moverX_Izquierda() {
		posX -= DESPLAZAMIENTO;
		img = imgL;
	}
	
	public void moverY_Arriba() {
		posY -= DESPLAZAMIENTO;
		img = imgU;
	}
	
	public void moverX_Derecha() {
		posX += DESPLAZAMIENTO;
		img = imgR;
	}
	
	public void moverY_Abajo() {
		posY += DESPLAZAMIENTO;
		img = imgD;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private BufferedImage cargarImagen(String path, int ancho, int alto){
		BufferedImage bImage = null;
		Image image = null;
		try {
			image = ImageIO.read(new File(path));
			image = image.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
			bImage = imageToBufferedImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bImage;
	}
	
	private BufferedImage imageToBufferedImage(Image image) {
		BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bImage.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bImage;
	}
	
	private BufferedImage rotarImagen(BufferedImage imagen, int grados) {	
		BufferedImage bImage = new BufferedImage(imagen.getHeight(), imagen.getWidth(),  BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bImage.createGraphics();
		AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(grados), bImage.getWidth()/2, bImage.getHeight()/2);
		g.drawImage(imagen, at, null);
		g.dispose();
		return bImage;
	}
	
	public void dibujar() {
		
		g2d.setBackground(Color.decode("#BDBDBD"));
		g2d.clearRect(0, 0, 800, 600);
		g2d.drawImage(img, posX, posY, TAMAÑO, TAMAÑO, null);
	}
	
	private final int TAMAÑO = 20;
	private final int DESPLAZAMIENTO = 20;
	private BufferedImage img, imgL, imgU, imgR, imgD; 
	private Graphics2D g2d;
}
