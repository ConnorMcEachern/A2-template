package ca.mcmaster.se2aa4.island.teamXXX;

public class Direction {
    private int dir;
    private static Direction east = new Direction(0);
    private static Direction south = new Direction(1);
    private static Direction west = new Direction(2);
    private static Direction north = new Direction(3);
    
    private Direction (int dir) {
        this.dir = dir;
    }

    private static Direction fromDir(int direction) {
        if (direction == 0) {
            return Direction.East();
        } else if (direction == 1) {
            return Direction.South();
        } else if (direction == 2) {
            return Direction.West();
        } else if (direction == 3) {
            return Direction.North();
        } else {
            return null;
        }
    }

    public boolean equals(Direction other) {
        return this.dir == other.dir;
    }

    public Direction left() {
        return fromDir((dir + 3)%4);
    }

    public Direction right() {
        return fromDir((dir + 1)%4);
    }

    public static Direction East() {
        return east;
    }
    public static Direction South() {
        return south;
    }
    public static Direction West() {
        return west;
    }
    public static Direction North() {
        return north;
    }

    public static Direction directionFromString(String direction) {
        if (direction.equals("E")) {
            return Direction.East();
        } else if (direction.equals("S")) {
            return Direction.South();
        } else if (direction.equals("W")) {
            return Direction.West();
        } else if (direction.equals("N")) {
            return Direction.North();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        if (dir==0) {
            return "E";
        } else if (dir==1) {
            return "S";
        } else if (dir==2) {
            return "W";
        } else if (dir==3) {
            return "N";
        } else {
            return "ERROR " + dir;
        }
    }
}
