package game.pacman.board;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Diogo on 25.01.2016.
 */
public class HUD extends Group {

    private int pacmanLives = 3;
    private int coinsEaten;
    private Group pacmanBar;

    private Text coinsEatenLabel;

    public HUD(int width,int height)
    {
        this.coinsEatenLabel = new Text(width-70,height+2,"Score ");
        this.coinsEatenLabel.setFill(Color.WHITE);
        this.coinsEatenLabel.setStyle("-fx-font-weight:bold");
        this.getChildren().add(coinsEatenLabel);

        pacmanBar = new Group();
        this.getChildren().add(pacmanBar);
        this.fillPacmanBar();
        this.pacmanBar.setLayoutY(height-10);

    }

    private void fillPacmanBar()
    {
        Text lifeLbl;
        pacmanBar.getChildren().removeAll(pacmanBar.getChildren());
        pacmanBar.getChildren().add((lifeLbl = new Text(5,12,"Lives ")));
        lifeLbl.setFill(Color.WHITE);
        lifeLbl.setStyle("-fx-font-weight:bold");
        for(int i =0;i < pacmanLives;i++)
        {
            ImageView pacmanLifeImg = new ImageView(new Image("pacman-l1.png"));
            pacmanLifeImg.setLayoutY(0);
            pacmanLifeImg.setLayoutX(lifeLbl.getLayoutBounds().getWidth() + 10 + i * 20);
            pacmanBar.getChildren().add(pacmanLifeImg);
        }
        pacmanBar.setStyle("-fx-background-color: red");
    }

    private void updateHUD()
    {
        this.coinsEatenLabel.setText("Score " +coinsEaten);
        fillPacmanBar();
    }

    public void increaseCoins()
    {
        coinsEaten ++;
        this.updateHUD();
    }

    public void setPacmanLives(int lives)
    {
        this.pacmanLives = lives;
        this.updateHUD();
    }

    public void showMessage(String message)
    {
        Text t = new Text(100,100,message);
        t.setFont(new Font(20));
        t.setFill(Color.WHITE);
        t.setStyle("-fx-font-weight: bold");
        getChildren().add(t);
    }
}
