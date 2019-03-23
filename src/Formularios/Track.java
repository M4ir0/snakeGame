package Formularios;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import Clases.*;

public class Track extends Canvas implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	BufferedImage bImage;
	Graphics2D g2d;
	Head head;
	private int ancho, alto;
	Ejecutable ejecutable;
	Thread hilo;
	JLabel etiqueta;
	
	public Track(int width, int height, JLabel etiqueta) {
		
		this.ancho = width-20;
		this.alto = height-60;
		this.etiqueta = etiqueta;
		
		this.addKeyListener(this);
		
		try {
			bImage = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
			g2d = bImage.createGraphics();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		iniciar();
	}
	
	public void iniciar() {
		ejecutable = new Ejecutable(this, g2d, etiqueta);
		ejecutable.setDireccion(39);//EL PAREMETRO CORRESPONDE A LA DIRECCION DE INICIO DEL SPRITE
		//37 = IZQUIERDA
		//38 = ARRIBA
		//39 = DERECHA
		//40 = ABAJO
		hilo = new Thread(ejecutable);
		hilo.start();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g2d = (Graphics2D)g;
		g2d.drawImage(bImage, 0, 0, null);
	}
	
	public void update (Graphics g) 
	{ 
		paint(g2d);
		g.drawImage(bImage, 0, 0, null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		ejecutable.setDireccion(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
}

class Ejecutable implements Runnable{

	private JLabel etiqueta;
	private int puntos = 0;
	
	private Canvas c;
	private Graphics2D g2d;
	private Head head;
	
	//private Body body, body2, body3, body4;
	private ArrayList<Body> body = new ArrayList<>();
	
	private Food food;
	
	private boolean moviendo = true;
	private boolean bloqueoIzquierda = false;
	private boolean bloqueoArriba = false;
	private boolean bloqueoDerecha = false;
	private boolean bloqueoAbajo = false;
	private int direccion = 0;
	private int estado = 0;
	
	private int pos_x_anterior = 0;
	private int pos_y_anterior = 0;
	
	private Random random;
	
	private int abrirSorpresa = 5;
	
	public Ejecutable(Canvas c, Graphics2D g2d, JLabel etiqueta) {
		this.c = c;
		this.g2d = g2d;
		this.etiqueta = etiqueta;
	}
	
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public void run() {
		
		etiqueta.setText("Puntos: " + puntos);
		
		head = new Head(g2d);
		
		//INICIAR CUERPO EN 3 CIRCULOS
		body.add(new Body(g2d));
		body.add(new Body(g2d));
		body.add(new Body(g2d));
		
		
		random = new Random();
		
		food = new Food(g2d);
		
		food.setPosX(coordenada(780));
		food.setPosY(coordenada(580));
		
		while(moviendo) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			pos_x_anterior = head.getPosX();
			pos_y_anterior = head.getPosY();
			
			//IZQUIERDA
			if(direccion == 37 && !bloqueoIzquierda) {
				head.moverX_Izquierda();
				head.dibujar();
				c.repaint();
				bloqueoDerecha = true;
				bloqueoIzquierda = false;
				bloqueoArriba = false;
				bloqueoAbajo = false;
				estado = 37;
			}
			//DERECHA
			else if(direccion == 39 && !bloqueoDerecha) {
				head.moverX_Derecha();
				head.dibujar();
				c.repaint();
				bloqueoIzquierda = true;
				bloqueoDerecha = false;
				bloqueoArriba = false;
				bloqueoAbajo = false;
				estado = 39;
			}
			//ARRIBA
			else if(direccion == 38 && !bloqueoArriba) {			
				head.moverY_Arriba();
				head.dibujar();
				c.repaint();
				bloqueoAbajo = true;
				bloqueoArriba = false;
				bloqueoDerecha = false;
				bloqueoIzquierda = false;
				estado = 38;
			}
			//ABAJO
			else if(direccion == 40 && !bloqueoAbajo) {
				head.moverY_Abajo();
				head.dibujar();
				c.repaint();
				bloqueoArriba = true;
				bloqueoAbajo = false;
				bloqueoDerecha = false;
				bloqueoIzquierda = false;
				estado = 40;
			}
			else {				
				
				switch(estado) {
					case 37:
						head.moverX_Izquierda();
						direccion = 37;
						break;
					case 38:
						head.moverY_Arriba();
						direccion = 38;
						break;
					case 39:
						head.moverX_Derecha();
						direccion = 39;
						break;
					case 40:
						head.moverY_Abajo();
						direccion = 40;
						break;
					}
				
				head.dibujar();
				c.repaint();
			}
		
			
			//CARGAR POSICION X y Y
			
			
			for(int pos = body.size()-1; pos > 0; pos --){
				
				body.get(pos).setPosX(body.get(pos-1).getPosX());
				body.get(pos).setPosY(body.get(pos-1).getPosY());
				
			}
			
//			body.get(2).setPosX(body.get(1).getPosX());
//			body.get(2).setPosY(body.get(1).getPosY());
//			
//			body.get(1).setPosX(body.get(0).getPosX());
//			body.get(1).setPosY(body.get(0).getPosY());
			
			//CARGAR POSICION DE LA CABEZA EN EL PRIMER BODY
			body.get(0).setPosX(pos_x_anterior);
			body.get(0).setPosY(pos_y_anterior);
			
			
			for(int pos = 0; pos < body.size(); pos++) {
				body.get(pos).moveBody();
				body.get(pos).drawBody();		
			}
			
//			body.get(0).moveBody();
//			body.get(0).drawBody();
//			
//			body.get(1).moveBody();
//			body.get(1).drawBody();
//			
//			body.get(2).moveBody();
//			body.get(2).drawBody();
		
			
			food.drawFood();
			
			c.repaint();
			
			if(colisionaBorde()) {				
				reiniciar();
			}
			
			for(int posB = 0; posB < body.size(); posB++) {
				if(colisiona(body.get(posB), head)) {
					reiniciar();
				}
			}
			
			if(colisiona(head, food)) {
				System.out.println("COLISION COMIDA");
				
				food = new Food(g2d);
				food.setPosX(coordenada(780));
				food.setPosY(coordenada(580));
				
				body.add(new Body(g2d));
				
				puntos += 1;
				etiqueta.setText("Puntos: " + puntos);
				
			}
			
			/*
			if(puntos == abrirSorpresa) {
				
				abrirSorpresa = -1;
				
				Sorpresa s = new Sorpresa();
				s.setFocusable(false);
				s.setVisible(true);
				c.setFocusable(true);
			}
			*/
			
		}
		
	}

	private void reiniciar() {
		head.setPosX(0);
		head.setPosY(0);
	
		direccion = 39;
		estado = 39;
		
		puntos = 0;
		etiqueta.setText("Puntos: " + puntos);
		
		body.clear(); 
		body.add(new Body(g2d));
		body.add(new Body(g2d));
		body.add(new Body(g2d));
		
		food = new Food(g2d);
		food.setPosX(coordenada(780));
		food.setPosY(coordenada(580));
		
		System.out.println("COLISION BORDE");
	}
	
	boolean colisiona(Sprite A, Sprite B) {		
		boolean colision = false;
		//COMPROBAR COLISION EN LOS 4 EJES
		if(A.getPosX() < B.getPosX() + B.getWidth() && A.getPosY() < B.getPosY() + B.getHeight() &&
				A.getPosX() < B.getPosX() + B.getWidth() && A.getPosY() + A.getHeight() > B.getPosY() &&
				A.getPosX() + A.getWidth() > B.getPosX() && A.getPosY() < B.getPosY() + B.getHeight() &&
				A.getPosX() + A.getWidth() > B.getPosX() && A.getPosY() + A.getHeight() > B.getPosY()) {
			colision = true;
		}
		return colision;
	}
	
	boolean colisionaBorde() {
		boolean colision = false;
		
		if(head.getPosX() >= c.getWidth() || head.getPosY() >= c.getHeight() 
				|| head.getPosX() < 0 || head.getPosY() < 0) {
			colision = true;
		}
		
		return colision;
	}
	
	private int coordenada(int maximo) {
		
		int valor = 0;
		String unidad = "";
		String decena = "";
		String centena = "";
		
		valor = random.nextInt(maximo);
		
		String aux = String.valueOf((valor));
		
		System.out.println("ORIGINAL: " + aux);
		
		if(aux.length() == 3) {
			
			//CONVERTIR A DIVISIBLE ENTRE 20
			aux = aux.substring(0, aux.length()-1);
			aux = aux + "0";
			
			System.out.println("CONVERTIDO A DIVISIBLE: " + aux);
			
			unidad = "0";
			decena = aux.substring(1, aux.length()-1);
			centena = aux.substring(0, aux.length()-2);
			
			System.out.println("UNIDAD: " + unidad);
			System.out.println("DECENA: " + decena);
			System.out.println("CENTENA: " + centena);
			
			valor = Integer.parseInt(decena);
			
			
			if(valor%2 == 0) {
				
				aux = centena + decena + unidad;
				
				System.out.println("ACCEDIO AL IF: " + aux);
				
			}else {
				
				if(valor == 9) {
					valor -= 1;
				}else {
					valor += 1;
				}
				
				aux = centena + valor + unidad;
				
				System.out.println("ACCEDIO AL ELSE: " + aux);
			}
			
		}else if(valor < 100 && valor > 10){
			
			unidad = "0";
			decena = aux.substring(0, aux.length()-1);
			
			valor = Integer.parseInt(decena);
			
			if(valor%2 == 0) {
				
				aux = decena + unidad;
				
				System.out.println("ACCEDIO AL IF: " + aux);
				
			}else {
				
				valor += 1;			
				
				aux =  valor + unidad;
				
				System.out.println("ACCEDIO AL ELSE: " + aux);
			}
		}else if(valor < 10) {
			valor = 20;
		}
		
		System.out.println("RESULTADO: " + aux);
		
		return Integer.parseInt(aux);
	}
	
}