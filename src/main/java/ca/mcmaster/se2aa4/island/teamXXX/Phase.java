package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


/*
 * This class is for creating the main logic 
 * Create a subclass and fill in the abstract methods
 */
public abstract class Phase {

    private List<Action> decisions = new ArrayList<Action>();
    protected Drone drone;
    protected Island islandMap;
    protected boolean isOver =  false;                          //Set to true when the Phase is complete

    public abstract void getInfoFromScan(JSONObject info);
    public abstract void getInfoFromEcho(JSONObject info);
    public abstract void initialize();

    public boolean isOver() {
        return isOver;
    };

    public Action nextAction() {
        return decisions.removeFirst();
    }

    /*
     * Use the following methods to add to the action queue
     */
    public void turnDroneRight() {
        drone.turnRight();
        decisions.addLast(new Heading(drone.heading()));
    }

    public void turnDroneLeft() {
        drone.turnLeft();
        decisions.addLast(new Heading(drone.heading()));
    } 

    public void flyDrone() {
        drone.fly();
        decisions.addLast(new Fly());
    }

    public void echo(Direction dir) {
        decisions.addLast(new Echo(dir));
    }

    public void scan() {
        decisions.addLast(new Scan());
    }

    public void stop() {
        decisions.addLast(new Stop());
    }
}
