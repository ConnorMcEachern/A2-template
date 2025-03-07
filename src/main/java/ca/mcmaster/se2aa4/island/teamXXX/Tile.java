package ca.mcmaster.se2aa4.island.teamXXX;

public class Tile {
    boolean isSearched = false;

    public void search() {
        isSearched = true;
    }

    public boolean isSearched() {
        return isSearched;
    }
}
