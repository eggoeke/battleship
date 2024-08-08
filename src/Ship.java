public class Ship {
    private int[][] coordinates;

    // Constructor
    public Ship(int[][] coordinates){
        this.coordinates = coordinates;
    }

    public int getNumCoordinates(){
        return coordinates.length;
    }

    public int[][] getCoordinates(){
        return coordinates;
    }
}
