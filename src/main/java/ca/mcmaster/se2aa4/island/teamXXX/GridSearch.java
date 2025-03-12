package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class GridSearch extends Phase {
    private int count = 0;
    private int target = 1;

    public GridSearch(Drone drone, Island map) {
        this.drone = drone;
        this.island = map;
    }

    @Override
    public Action nextAction() {
        if (island.isTileSearched(drone.position()) == false) {
            return new Scan(drone, island);  
        }
        count++;
        if (count%target == 0) {
            if (count == target*2) {
                count = 0;
                target++;
            }
            return new Right(drone);            
        }
        return new Fly(drone);
    }

    @Override
    public boolean isOver() {
        return drone.batteryLevel()<50;
    }

    @Override
    public void getInfoFromScan(JSONObject info) {

    }

    @Override
    public void getInfoFromEcho(JSONObject info) {
        
    }
    
}
