package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone {
    private int batteryLevel;
    private Direction heading;

    public Drone(int batteryLevel, Direction heading) {
        this.batteryLevel = batteryLevel;
        this.heading = heading;
    }

    public void reduceBattery(int amount) {
        batteryLevel -= amount;
    }


}
