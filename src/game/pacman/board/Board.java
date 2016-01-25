package game.pacman.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {

	//Marge pour les coins
	private final int COINS_PADDING_UP = 3;
    private final int COINS_PADDING_DOWN = 2;
    private final int COINS_PADDING_LEFT = 3;
    private final int COINS_PADDING_RIGHT = 2;
	private Collection<Sprite> mobile;
	private ArrayList<Sprite> coins;
	private PacMan pacman;
	private Map map;
	private boolean startedMoving;
	private boolean inited;

	private Rectangle posHelper;
	private Rectangle posHelper2;

	public Board() {

		// only design calls here
		this.setStyle("-fx-background-color: black;");
		this.getChildren().add(map = new Map("board.png"));
		mobile = new LinkedList<Sprite>();
		coins = new ArrayList<Sprite>();
		createCoins();
		mobile.addAll(Arrays.asList(new Sprite[]{
				new Ghost("inky", 133, 134),
				new Ghost("pinky", 124, 134),
				new Ghost("blinky", 115, 134),
				new Ghost("clyde", 106, 134),
				pacman = new PacMan(120, 204)
		}));
		posHelper = new Rectangle(8,8);
		posHelper2 = new Rectangle(8,8);
		posHelper2.setFill(Color.GREEN);
		posHelper.setFill(Color.RED);
		getChildren().add(posHelper);
		getChildren().add(posHelper2);
		this.getChildren().addAll(coins);
		this.getChildren().addAll(mobile);
		map.setLayoutX(0);
		map.setLayoutY(0);
		startedMoving = inited = false;
		//testMapData();
	}

	/**
	 * Créer et ajoute les coins à la scène
	 */
	private void createCoins(){
		for(int i = COINS_PADDING_UP;i < map.getMapData().length- COINS_PADDING_DOWN; i++){
			for(int j = COINS_PADDING_LEFT; j< map.getMapData()[0].length- COINS_PADDING_RIGHT;j++){
				if(map.getMapData()[i][j] == 0)
				{
					coins.add(new Coin(j * 8 + 2, i * 8 + 2));
				}
			}
		}
	}

	/**
	 * Fonction de test pour afficher les murs de collision
	 */
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

		//Collisions avec les murs
		for (Sprite s : mobile) {
			s.move();
			if(s.getBounds().intersects(map.getBoudingBox(s.getDirection(), s.getLayoutX(), s.getLayoutY())))
				s.moveBack();
		}

		//Collisions pacman et fantomes
		for(Sprite s: mobile){
			if(!(s instanceof PacMan))
			{
				if(s.getBounds().intersects(pacman.getBounds()))
				{
					pacman.decreaseLife();
					System.out.println(pacman.isAlive());
				}
			}
		}

		//Collisions pacman coins
		for(int i=0;i<coins.size();i++)
		{
			if(pacman.getBounds().intersects(coins.get(i).getBoundsInParent()))
				getChildren().remove(coins.get(i));
		}

		posHelper.setLayoutY(pacman.getBounds().getMinY());
		posHelper.setLayoutX(pacman.getBounds().getMinX());
		posHelper.toFront();
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
