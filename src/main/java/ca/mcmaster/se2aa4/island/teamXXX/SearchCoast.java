package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.LinkedList;
import java.util.Queue;

import org.json.JSONObject;

public class SearchCoast extends Phase {

    Queue<Action> actions;

    public SearchCoast(Drone drone, Island map) {
        this.drone = drone;
        this.island = map;
        
    }

    public void initialize() {
        actions = new LinkedList<>();
        for (int i = 0; i < island.getWidth()/2; i++) {
            actions.add(new Fly(drone));
        }
        actions.add(new Right(drone));
        actions.add(new Echo(drone, island, Direction.South()));
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
        for (int i = 0; i <= info.getInt("range"); i++) {
            actions.add(new Fly(drone));
        }
        actions.add(new Scan(drone, island));
    }
    
}
