package main;

import java.awt.image.BufferedImage;

public class TomatoGame {
	BufferedImage image = null; 	
	int solution = -1;
	
	public TomatoGame(BufferedImage image, int solution) {
		super();
		this.image = image;
		this.solution = solution;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public int getSolution() {
		return solution;
	}
}
