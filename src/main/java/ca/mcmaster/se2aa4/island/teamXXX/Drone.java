package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone {
    private int batteryLevel;
    private Direction heading;
    private Position position;

    public Drone(int batteryLevel, Direction heading, Position position) {
        this.batteryLevel = batteryLevel;
        this.heading = heading;
        this.position = position;
    }

    public int batteryLevel() {
        return batteryLevel;
    }

    public Position position() {
        return position;
    }

    public Direction heading() {
        return heading;
    }

    public void reduceBattery(int amount) {
        batteryLevel -= amount;
    }

    public void fly() {
        position = position.move(heading);
    }

    public void turnLeft() {
        fly();
        heading = heading.turnLeft();
        fly();
    }

    public void turnRight() {
        fly();
        heading = heading.turnRight();
        fly();
    }
}
