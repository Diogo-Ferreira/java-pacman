package game.pacman.board;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {
	private Collection<Sprite> mobile;
	private Sprite pacman;
	private Map map;
	private boolean startedMoving;
	private boolean inited;

	public Board() {
		// only design calls here
		this.setStyle("-fx-background-color: black;");
		this.getChildren().add(map = new Map("board.png"));
		mobile = new LinkedList<Sprite>();
		mobile.addAll(Arrays.asList(new Sprite[] {
				new Ghost("inky", 0, 0),
				new Ghost("pinky", 16, 0),
				new Ghost("blinky", 32, 0),
				new Ghost("clyde", 48, 0),
				pacman = new PacMan(64, 0)
			}));
		this.getChildren().addAll(mobile);
		createCoins();
		startedMoving = inited = false;
		//testMapData();
	}

	//WTF ?
	private void createCoins(){
		for(int i =3 ;i < map.getMapData().length-2; i++){
			for(int j = 3; j< map.getMapData()[0].length-2;j++){
				if(map.getMapData()[i][j] == 0 && j % 2 == 0 && i % 2 == 0)
				{
					getChildren().add(new Coin(j * 8 -8,i *8-8));
				}

			}
		}
	}

	private void testMapData(){
		for(int i =0 ;i < map.getMapData().length ; i++){
			for(int j = 0; j< map.getMapData()[0].length;j++){
				if(map.getMapData()[i][j] == 1)
				{
					Rectangle r = new Rectangle(j*8,i*8,8,8);
					r.setFill(Color.BLUE);
					getChildren().add(r);
				}

			}
		}
	}

	private void init() {
		// all game initialisation here
		// called after show(), so all sizes
		// are fixed
		for (Sprite s : mobile) {
			s.setMoveLimits(this.getWidth(), this.getHeight());
			s.setSpeed(1.);
		}
		inited = true;
	}

	public void animateAndMove() {
		if (!inited)
			init();

		for (Sprite s : mobile)
			s.animate();

		if (!startedMoving)
			return;

		for (Sprite s : mobile)
			s.move();

		// check collisions
		// ....
		/*
		 * Bound bp = pacman.getBoundsInParent(); for (Sprite s : all sprites) {
		 * Bound bs = s.getBoundsInParent(); if (bp intersect bs) moveBack() ? }
		 */
		if(pacman.getBoundsInParent().intersects(map.getBoudingBox(pacman.getLayoutX(),pacman.getLayoutY()))){
			pacman.moveBack();
		}

	}

	public void handleKeyPressed(KeyCode keyCode) {
		// System.err.println("key press: " + keyCode);
		startedMoving = true;

		switch (keyCode) {
		case UP:
			pacman.setDirection(Sprite.Direction.UP);
			break;
		case DOWN:
			pacman.setDirection(Sprite.Direction.DOWN);
			break;
		case LEFT:
			pacman.setDirection(Sprite.Direction.LEFT);
			break;
		case RIGHT:
			pacman.setDirection(Sprite.Direction.RIGHT);
			break;
		default:
			break;
		}
	}

}
