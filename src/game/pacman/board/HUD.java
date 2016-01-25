package game.pacman.board;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
        this.coinsEatenLabel = new Text(10,20,"0 Coins");
        this.coinsEatenLabel.setFill(Color.RED);
        this.getChildren().add(coinsEatenLabel);

        pacmanBar = new Group();
        this.getChildren().add(pacmanBar);
        this.fillPacmanBar();
        this.pacmanBar.setLayoutY(height-16);

    }

    private void fillPacmanBar()
    {
        pacmanBar.getChildren().removeAll(pacmanBar.getChildren());
        for(int i =0;i < pacmanLives;i++)
        {
            ImageView pacmanLifeImg = new ImageView(new Image("pacman-l1.png"));
            pacmanLifeImg.setLayoutY(0);
            pacmanLifeImg.setLayoutX(i * 20);
            pacmanBar.getChildren().add(pacmanLifeImg);
        }
    }

    private void updateHUD()
    {
        this.coinsEatenLabel.setText(coinsEaten + " Coins");
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
}
