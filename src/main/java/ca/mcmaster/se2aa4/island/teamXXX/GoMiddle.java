package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.LinkedList;
import java.util.Queue;

import org.json.JSONObject;

public class GoMiddle extends Phase {

    Queue<Action> actions;

    public GoMiddle(Drone drone, Island map) {
        this.drone = drone;
        this.island = map;
        
    }

    public void initialize() {
        actions = new LinkedList<>();
        for (int i = 0; i < island.getWidth()/2; i++) {
            actions.add(new Fly(drone));
        }
        actions.add(new Right(drone));
        for (int i = 0; i < island.getHeight()/2; i++) {
            actions.add(new Fly(drone));
        }
        
    }

    @Override
    public Action nextAction() {
        if (actions == null) {
            initialize();
        }
        return actions.remove();
    }

    @Override
    public boolean isOver(){
        return actions.isEmpty();
    }

    @Override
    public void getInfoFromScan(JSONObject info) {
    }

    @Override
    public void getInfoFromEcho(JSONObject info) {
    }
    
}
