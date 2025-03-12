package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

/*
 * This class is for creating the main logic 
 * Create a subclass and fill in the abstract methods
 */
public abstract class Phase {
    protected Drone drone;
    protected Island island;

    public abstract Action nextAction();
    public abstract boolean isOver();

    public abstract void getInfoFromScan(JSONObject info);
    public abstract void getInfoFromEcho(JSONObject info);

}
