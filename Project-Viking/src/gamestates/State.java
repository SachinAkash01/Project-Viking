package gamestates;

import java.awt.event.MouseEvent;

import audio.AudioPlayer;
import main.Game;
import main.GameWindow;
import ui.LoginUI;
import ui.MenuButton;

public class State {

	protected Game game;
	protected LoginUI login;

	public State(Game game) {
		this.game = game;
	}

	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	public Game getGame() {
		return game;
	}

	@SuppressWarnings("incomplete-switch")
	public void setGamestate(Gamestate state) {
		switch (state) {
		case MENU -> game.getAudioPlayer().playSong(AudioPlayer.MENU_1);
		case PLAYING -> game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
		case QUIT -> login.removeUserSession();
		}

		Gamestate.state = state;
	}

}