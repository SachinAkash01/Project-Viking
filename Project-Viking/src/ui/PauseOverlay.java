package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

import static utilz.Constants.UI.PauseButtons.*;

public class PauseOverlay {
	
	private BufferedImage backgroundImage;
	private int bgX, bgY, bgW, bgH;
	private SoundButton musicButton, sfxButton;
	
	public PauseOverlay() {
		loadBackground();
		createSoundButtons();
	}
	
	private void createSoundButtons() {
		int soundX = (int)(450 * Game.SCALE);
		int musicY = (int)(140 * Game.SCALE);
		int sfxY = (int)(186 * Game.SCALE);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void loadBackground() {
		backgroundImage = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int)(backgroundImage.getWidth() * Game.SCALE);
		bgH = (int)(backgroundImage.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int)(25 * Game.SCALE);
	}

	public void update() {
		musicButton.update();
		sfxButton.update();
	}
	
	public void draw(Graphics g) {
		//background
		g.drawImage(backgroundImage, bgX, bgY, bgW, bgH, null);
		
		//sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);
	}
	
	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		
		if (isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMouseOver(true);
	}
	
	public void mouseReleased(MouseEvent e) {
		if (isIn(e, musicButton)) {
			if (musicButton.isMousePressed())
				musicButton.setMuted(!musicButton.isMuted());
		} else if (isIn(e, sfxButton))
			if (sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());
		
		musicButton.resetBools();
		sfxButton.resetBools();
	}
	
	public void mousePressed(MouseEvent e) {
		if (isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}
