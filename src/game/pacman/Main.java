package game.pacman;

import game.pacman.board.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Pac-Man");
		Board board = new Board();
		Scene scene = new Scene(board);
		scene.addEventFilter(KeyEvent.KEY_PRESSED,
				event -> board.handleKeyPressed(event.getCode()));
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(100),
				e -> board.animateAndMove()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
	}

}
