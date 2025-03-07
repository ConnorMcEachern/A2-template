package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public abstract class Phase {

    private List<Action> decisions = new ArrayList<Action>();
    protected Drone drone;
    protected Island islandMap;

    public abstract void getInfoFromScan(JSONObject info);
    public abstract void getInfoFromEcho(JSONObject info);
    public abstract boolean isOver();

    public Action nextAction() {
        return decisions.removeFirst();
    }

    public void turnDrone(Turn dir) {
        if (dir == Turn.RIGHT) {
            drone.turnRight();
        } else {
            drone.turnLeft();
        }
        decisions.addLast(new Heading(drone.heading()));
    }

    public void flyDrone() {
        drone.fly();
        decisions.addLast(new Fly());
    }

    public void echo(Direction dir) {
        decisions.addLast(new Echo(dir));
    }

    protected enum Turn {
        LEFT,
        RIGHT
    }
}
