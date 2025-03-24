package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONArray;
import org.json.JSONObject;

public class GridSearch2 extends Phase implements ScanInfoReceiver{
    private int count = -1;
    private int target = 1;
    private boolean echoNext = false;

    public GridSearch2(Drone drone, Island map) {
        this.drone = drone;
        this.island = map;
    }

    @Override
    public Action nextAction() {
        if (echoNext) {
            echoNext = false;
            return new Echo(drone, island, drone.heading());
        }
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
        try {
            island.isTileSearched(drone.position().move(drone.heading()));
        } catch (Exception e) {
            return true;
        }
        return drone.batteryLevel()<20;
    }

    @Override
    public void getInfoFromScan(JSONObject info) {
        JSONArray biomes = info.getJSONArray("biomes");
        if (biomes.toString().contains("OCEAN")) {
            echoNext = true;
        }
    }
    
}
