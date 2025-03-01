package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;

public class Drone {
    private int batteryLevel;
    private Direction heading;
    private ArrayList<Action> decisions = new ArrayList<Action>();

    public Drone(int batteryLevel, String heading) {
        this.batteryLevel = batteryLevel;
        this.heading = heading;
        decisions.addLast(new Echo(heading));
        decisions.addLast(new Echo("S"));
        for (int i = 0; i<50; i++) {
            decisions.addLast(new Scan());
            decisions.addLast(new Fly());
        }
        decisions.addLast(new Stop());
    }
    
    public Action nextAction() {
        return decisions.removeFirst();
    }
}
