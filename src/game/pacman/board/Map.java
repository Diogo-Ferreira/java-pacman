package game.pacman.board;
import game.pacman.board.Sprite.Direction;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;



/**
 * Created by diogo on 11.12.15.
 */
public class Map extends ImageView {

    //Code d'objets sur la carte
    public final static int NO_COLL_CODE = 0;
    public final static int WALL_CODE = 1;
    public final static int COIN_CODE = 2;

    //Constantes
    private final int COLL_TILE_WIDTH = 8;
    private final int COLL_TILE_HEIGHT = 8;
    private final Color COLL_COLOR = Color.web("#2121de");
    private final Color COINS_COLOR = Color.web("#2aff00");

    private int[][] mapData;
    private Image coinsLayer;

    public Map(String imgPath)
    {
        super(imgPath);
        mapData = new int[(int)getImage().getHeight() / COLL_TILE_HEIGHT][(int)getImage().getWidth() / COLL_TILE_WIDTH];
        coinsLayer = new Image("board-coins-layer.png");
        initMapData();
    }

    /**
     * Construit le tableau de données mapData suivant le niveau
     */
    private void initMapData(){
        for(int x = 0;x < getImage().getHeight(); x += COLL_TILE_HEIGHT) {
            for(int y =0;y<getImage().getWidth(); y+= COLL_TILE_WIDTH){

                if(hasColor(y,x,COINS_COLOR,coinsLayer))
                {
                    mapData[x / COLL_TILE_HEIGHT][y / COLL_TILE_WIDTH] = COIN_CODE;
                }
                else if(hasColor(y,x, COLL_COLOR,getImage())) {
                    mapData[x / COLL_TILE_HEIGHT][y / COLL_TILE_WIDTH] = WALL_CODE;
                }
                else {
                    mapData[x / COLL_TILE_HEIGHT][y / COLL_TILE_WIDTH] = NO_COLL_CODE;
                }
            }
        }
    }

    /**
     * Vérifie si le sous-carré (de taille de tuile collision) de l'image contient la couleur spécifier
     * @param startX position
     * @param startY position
     * @param color couleur à chercher
     * @param imgToRead image dans laquel chercher
     * @return true si la couleur est présente, false sinon
     */
    private boolean hasColor(int startX,int startY,Color color,Image imgToRead)
    {
        PixelReader pixReader = imgToRead.getPixelReader();
        boolean hasColor = false;
        for(int x = startX; x < startX + COLL_TILE_WIDTH && !hasColor ; x++){
            for(int y = startY;y < startY + COLL_TILE_HEIGHT && !hasColor; y++){
                if(pixReader.getColor(x,y).equals(color)){
                    hasColor = true;
                }
            }
        }
        return hasColor;
    }

    /**
     * Retourne le mur à la position du pacman
     * @param dir direction dans laquel le pacman se balade
     * @param x
     * @param y
     * @return le mur si le pacman est sur celui-ci, sinon un mur bidon hors de la carte
     */
    public BoundingBox getBoudingBox(Direction dir,double x,double y){
        int x_offset=0;
        int y_offset=0;

        //Correction suivant la direction
        switch(dir){
            case RIGHT:
            case DOWN:
                x_offset += 8;
                y_offset += 8;
                break;
            case LEFT:
            case UP:
                x_offset += 4;
                y_offset += 4;
                break;
        }

        //Position en tiles
        int xPosInTile = (int) ((x+x_offset) / COLL_TILE_WIDTH);
        int yPosInTile = (int)((y+y_offset) / COLL_TILE_HEIGHT);

        //Le pacman va t'il contre un mur ?
        if(mapData[yPosInTile][xPosInTile] == WALL_CODE){
            return new BoundingBox(x-4,y-4,8,8);
        }else{
            return new BoundingBox(1000,1000,1,1);
        }
    }


    public int[][] getMapData() {
        return mapData;
    }
}
