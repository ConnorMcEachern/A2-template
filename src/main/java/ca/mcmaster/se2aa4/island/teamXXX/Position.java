package ca.mcmaster.se2aa4.island.teamXXX;

public class Position {
    private int y;
    private int x;

    public Position (int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public boolean equals(Position other) {
        return this.y == other.y && this.x == other.x;
    }

    public Position move(Direction dir) {
        Position next = new Position(this.y, this.x);
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
        return "(" + this.y + ", " + this.x + ")";
    }
}
