package game.pacman.board;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class Sprite extends StackPane {

	public enum Direction {
		LEFT(0), RIGHT(1), UP(2), DOWN(3);
		public final int value;
		private Direction(int v) { value = v; }
		public int signal() { return 2 * (value % 2) - 1; }
	};

	private final String name;
	private final ImageView[][] image;

	private Direction direction;
	private int frame;
	private double speed;
	private double maxX, maxY;
	protected double prevX, prevY;
	protected double startX,startY;

	private static String[][] getDefaultFileNames(String name) {
		char[] tc = { 'l', 'r', 'u', 'd' };
		String[][] names = new String[4][2];
		for (int d = 0; d < 4; d++)
			for (int f = 0; f < 2; f++)
				names[d][f] = name + "-" + tc[d] + (f + 1) + ".png";
		return names;
	}

	public Sprite(String name, double x, double y) {
		this(name, x, y, getDefaultFileNames(name));
	}

	public Sprite(String name, double x, double y, String[][] fileNames) {
		this.name = name;
		direction = Direction.DOWN;
		frame = 0;
		speed = maxX = maxY = 0;
		image = new ImageView[4][];
		setLayoutX(prevX = x);
		setLayoutY(prevY = y);
		startX = x;
		startY = y;

		// names.length == 4, otherwise out-of-bounds exception below
		for (int d = 0; d < 4; d++) {
			image[d] = new ImageView[fileNames[d].length];
			for (int f = 0; f < fileNames[d].length; f++) {
				image[d][f] = new ImageView(fileNames[d][f]);
				image[d][f].setVisible(d == direction.value && f == frame);
				this.getChildren().add(image[d][f]);
				// System.err.println("loading " + names[d][f] +
				// (image[d][f].isVisible()?"":" in") + "visible");
			}
		}
		// this.setStyle("-fx-background-color: white;");
	}

	public void setMoveLimits(double x, double y) {
		maxX = x - getWidth();
		maxY = y - getHeight();
	}

	public void setDirection(Direction newDirection) {
		image[newDirection.value][frame].setVisible(true);
		image[direction.value][frame].setVisible(false);
		direction = newDirection;
	}
	public Direction getDirection(){
		return direction;
	}

	public void animate() {
		// animate into different frames
		int newFrame = (frame + 1) % image[direction.value].length;
		image[direction.value][newFrame].setVisible(true);
		image[direction.value][frame].setVisible(false);
		frame = newFrame;
	}

	public void moveBack() {
		setLayoutX(prevX);
		setLayoutY(prevY);
	}

	public void move() {
		if (speed == 0.)
			return;

		double newX = prevX = getLayoutX();
		double newY = prevY = getLayoutY();
		double step = direction.signal() * speed * getWidth() / 4.;

		switch (direction) {
		case UP:
		case DOWN:
			newY += step;
			if (newY < 0.)
				newY = 0.;
			else if (newY > maxY)
				newY = maxY;
			setLayoutY(newY);
			break;
		case LEFT:
		case RIGHT:
			newX += step;
			if (newX < 0.)
				newX = 0.;
			else if (newX > maxX)
				newX = maxX;
			setLayoutX(newX);
			break;
		default:
			break;
		}
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public Bounds getBounds(){
		BoundingBox b = new BoundingBox(this.getLayoutX()+4,this.getLayoutY()+4,8,8);
		return (Bounds)b;
		//return this.getBoundsInParent();
	}

	public void resetPos()
	{
		setLayoutY(startY);
		setLayoutX(startX);
	}
}