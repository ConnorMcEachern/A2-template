package ca.mcmaster.se2aa4.island.teamXXX;

public class Position {
    private int x;
    private int y;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }

    public Position move(Direction dir) {
        Position next = new Position(this.x, this.y);
        if (dir.equals(Direction.East())) {
            next.x += 1;
        } else if (dir.equals(Direction.West())) {
            next.x -= 1;
        } else if (dir.equals(Direction.North())) {
            next.y -= 1;
        } else if (dir.equals(Direction.South())) {
            next.y += 1;
        }
        return next;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
