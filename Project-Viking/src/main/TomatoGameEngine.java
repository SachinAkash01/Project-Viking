package main;

import java.awt.image.BufferedImage;

public class TomatoGameEngine {
	String thePlayer = null;
	
	public TomatoGameEngine(String player) {
		thePlayer = player;
	}

	TomatoGameServer theGames = new TomatoGameServer();
	TomatoGame current = null;

	public BufferedImage nextGame() {
		current = theGames.getRandomGame();
		return current.getImage();

	}

	public boolean checkSolution( int i) {
		if (i == current.getSolution()) {
			return true;
		} else {
			return false;
		}
	}
}
