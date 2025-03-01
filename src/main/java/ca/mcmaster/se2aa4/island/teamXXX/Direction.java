package ca.mcmaster.se2aa4.island.teamXXX;

import static eu.ace_design.island.runner.Runner.run;

public class Direction {
    private int dir;

    private Direction (int dir) {
        this.dir = dir;
    }

    public boolean equals(Direction other) {
        return this.dir == other.dir;
    }

    public Direction turnLeft() {
        return new Direction((dir + 3)%4);
    }

    public Direction turnRight() {
        return new Direction((dir + 1)%4);
    }

    public static Direction East() {
        return new Direction(0);
    }
    public static Direction South() {
        return new Direction(1);
    }
    public static Direction West() {
        return new Direction(2);
    }
    public static Direction North() {
        return new Direction(3);
    }

    public static Direction directionFromString(String dir) {
        if (dir.equals("E")) {
            return Direction.East();
        } else if (dir.equals("S")) {
            return Direction.South();
        } else if (dir.equals("W")) {
            return Direction.West();
        } else if (dir.equals("N")) {
            return Direction.North();
        } else {
            return null;
        }
    }

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
