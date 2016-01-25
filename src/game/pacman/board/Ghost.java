package game.pacman.board;

public class Ghost extends Sprite {

	public Ghost(String name, double x, double y) {
		super(name, x, y);
	}

	@Override
	public void move() {
		recalculateDirection();
		super.move();
	}

	private void recalculateDirection() {
		double x = Math.random();
		if (x >= 0.7)
			setDirection(Direction.values()[(int) ((x - .7) * 10 + .5)]);
	}

	@Override
	public void moveBack(){
		super.moveBack();
		recalculateDirection();
	}

}
