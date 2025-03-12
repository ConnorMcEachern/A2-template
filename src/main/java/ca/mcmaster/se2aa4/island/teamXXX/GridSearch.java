package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class GridSearch extends Phase {

    public GridSearch(Drone drone, Island map) {
        this.drone = drone;
        this.island = map;
    }

    @Override
    public Action nextAction() {
        
        if (island.isTileSearched(drone.position())) {
            return new Fly(drone);
        }
        return new Scan(drone, island);   
    }

    @Override
    public boolean isOver() {
        return drone.position().move(drone.heading()).gety()>=island.getHeight();
    }

    @Override
    public void getInfoFromScan(JSONObject info) {

    }

    @Override
    public void getInfoFromEcho(JSONObject info) {
        
    }
    
}
