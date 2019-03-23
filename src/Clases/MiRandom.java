package Clases;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MiRandom{
	
	public MiRandom() {
		random = new Random();
	}
	
	public void setValoresInvalidos() {
		
	}
	
	public int nextValueX(int maxX) {
		maxX = random.nextInt(maxX);
		return maxX;
	}
	
	public int nextValueY(int maxY) {
		maxY = random.nextInt(maxY);
		return maxY;
	}
	
	private int[] valoresInvalidos;
	private int maxX, maxY;
	private Random random;
}
