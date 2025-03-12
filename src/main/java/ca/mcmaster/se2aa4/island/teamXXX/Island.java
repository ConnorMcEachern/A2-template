package ca.mcmaster.se2aa4.island.teamXXX;

public class Island {
    private Tile[][] tiles;

    public void initialize(int xdistance, int ydistance) {
        tiles = new Tile[xdistance][ydistance];
        System.out.println("test");

    }

    public void search(Position pos) {
        if (tiles == null) return;
        tiles[pos.getx()][pos.gety()] = new Tile();
    }

    public boolean isTileSearched(Position pos) {
        if (tiles == null) return false;
        return tiles[pos.getx()][pos.gety()] != null;
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

}
