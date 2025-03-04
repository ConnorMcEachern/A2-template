package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;

public class Drone {
    private int batteryLevel;
    private Direction heading;
    private ArrayList<Action> decisions = new ArrayList<Action>();

    public Drone(int batteryLevel, Direction heading) {
        this.batteryLevel = batteryLevel;
        this.heading = heading;
        decisions.addLast(new Echo(heading));
        decisions.addLast(new Echo(Direction.South()));
        for (int i = 0; i<50; i++) {
            decisions.addLast(new Scan());
            decisions.addLast(new Fly());
        }
        decisions.addLast(new Stop());
    }
    
    public Action nextAction() {
        return decisions.removeFirst();
    }

    public void reduceBattery(int amount) {
        batteryLevel -= amount;
    }

    public void getInfoFromScan() {

    }

    public void getInfoFromEcho() {
        
    }
}
