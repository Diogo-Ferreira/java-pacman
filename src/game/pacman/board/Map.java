package game.pacman.board;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;


/**
 * Created by diogo on 11.12.15.
 */
public class Map extends ImageView {

    private int[][] mapData;

    private final int COLLISION_TILE_WIDTH = 8;
    private final int COLLISION_TILE_HEIGHT = 8;

    public Map(String imgPath)
    {
        super(imgPath);
        mapData = new int[(int)getImage().getHeight() / COLLISION_TILE_HEIGHT][(int)getImage().getWidth() / COLLISION_TILE_WIDTH];
        initMapData();
    }

    private void initMapData(){
        PixelReader pixReader = getImage().getPixelReader();

        //Parcours chaque sous case de 8x8 par exemple
        for(int map_y = 0;map_y < getImage().getHeight(); map_y+= COLLISION_TILE_HEIGHT){
            for(int map_x =0;map_x<getImage().getWidth(); map_x+= COLLISION_TILE_WIDTH){

                //Ensuite chaque pixel afin de savoir s'il contient du bleu
                boolean hasBlue = false;
                for(int tile_x = map_x; tile_x < map_x + COLLISION_TILE_WIDTH ; tile_x++){
                    for(int tile_y = map_y;tile_y < map_y + COLLISION_TILE_HEIGHT; tile_y++){
                        if(pixReader.getColor(tile_x,tile_y).equals(Color.web("#2121de"))){
                            hasBlue = true;
                        }
                    }
                }
                if(hasBlue) {
                    mapData[map_y / COLLISION_TILE_HEIGHT][map_x / COLLISION_TILE_WIDTH] = 1;
                    System.out.println("Hello");
                }
                else
                    mapData[map_y/COLLISION_TILE_HEIGHT][map_x/COLLISION_TILE_WIDTH] = 0;
            }
        }
    }


    public int[][] getMapData() {
        return mapData;
    }

    public void setMapData(int[][] mapData) {
        this.mapData = mapData;
    }
}
