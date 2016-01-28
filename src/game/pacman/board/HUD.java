package game.pacman.board;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Classe gérant le HUD du jeux (S'ajoute à la scène)
 */
public class HUD extends Group {

    private int pacmanLives = PacMan.MAX_LIFE;
    private int coinsEaten;
    private Group pacmanBar;
    private int windowWidth;
    private int windowHeight;
    private Text message;

    private Text coinsEatenLabel;

    public HUD(int width,int height)
    {
        this.windowWidth = width;
        this.windowHeight = height;
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
    }

    private void updateHUD()
    {
        this.coinsEatenLabel.setText("Score " + coinsEaten);
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

    /**
     * Affiche un message sur le plateaux de jeu
     * @param msg
     * @param fontSize
     */
    public void showMessage(String msg,int fontSize)
    {
        this.message = new Text(100,100,msg);
        this.message.setFont(new Font(fontSize));
        this.message.setFill(Color.WHITE);
        this.message.setStyle("-fx-font-weight: bold");
        getChildren().add(this.message);
        this.message.setX(windowWidth / 2 - (this.message.getLayoutBounds().getWidth() / 2));
    }

    public void clearMessage()
    {
        if(message != null)
            getChildren().remove(message);
    }

    public int getCoinsEaten() {
        return coinsEaten;
    }

    public void setCoinsEaten(int coinsEaten) {
        this.coinsEaten = coinsEaten;
    }
}
