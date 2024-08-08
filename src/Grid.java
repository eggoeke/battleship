import java.util.Arrays;
import java.util.ArrayList;

public class Grid {
    private int columns;
    private int rows;
    private ArrayList<Ship> shipArr;

    // Making Grid public so it can be acessed anywhere
    // Constructor
    public Grid(ArrayList<Ship> ships, int columnNum, int rowNum){
        this.shipArr = ships;
        this.columns = columnNum;
        this.rows = rowNum;
    }

    public boolean checkShip(int x, int y){
        boolean hit = false;
        for(int i = 0; i < shipArr.size(); i++){
            Ship ship = shipArr.get(i);
            int length = ship.getNumCoordinates();
            for(int k = 0; k < length; k++){
                int xShip = ship.getCoordinates()[k][0];
                int yShip = ship.getCoordinates()[k][1];
                if((x==xShip)&& (y==yShip)){
                    hit = true;
                }
            }

        }
        return hit;
    }
}
