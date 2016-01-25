package game.pacman.board;

public class PacMan extends Sprite {

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

	private int lives = 3;

	public PacMan(double x, double y) {
		super("pacman", x, y);
	}

}
