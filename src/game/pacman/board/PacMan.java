package game.pacman.board;


import javafx.scene.media.AudioClip;

public class PacMan extends Sprite {

	private AudioClip chompSound;
	private int lives = 3;

	public PacMan(double x, double y) {
		super("pacman", x, y);
		chompSound = new AudioClip("file:////C:/Users/Diogo/Documents/GitHub/java-pacman/resources/pacman_chomp.wav");
		chompSound.setCycleCount(AudioClip.INDEFINITE);
	}

	public void move()
	{
		super.move();
		if(!chompSound.isPlaying())
			chompSound.play();
	}

	public void stopPlayChompSound()
	{
		chompSound.stop();
	}


	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * Enlève une vie au pacman
	 */
	public void decreaseLife(){
		this.lives --;
	}

	/**
	 * Pacman en vie ?
	 * @return true si en vie, false sinon
	 */
	public boolean isAlive()
	{
		if(lives >= 0)
			return true;
		else
			return false;
	}

}
