package ca.mcmaster.se2aa4.island.teamXXX;

public class Island {
    private Tile[][] tiles;

    public void initialize(int xdistance, int ydistance) {
        tiles = new Tile[xdistance][ydistance];
    }

    public void search(Position pos) {
        tiles[pos.getx()][pos.gety()].search();
    }

    public boolean isTileSearched(Position pos) {
        return tiles[pos.getx()][pos.gety()].isSearched();
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

}
