package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class MakeMap extends Phase {
    Integer xsize;

    public MakeMap(Drone drone, Island map) {
        this.drone = drone;
        this.islandMap = map;
    }

    public void initialize() {
        echo(drone.heading());
        echo(drone.heading().turnRight());
    }

    @Override
    public void getInfoFromScan(JSONObject info) {
    }

    @Override
    public void getInfoFromEcho(JSONObject info) {
        if (xsize == null) {
            xsize = info.getInt("range");
            return;
        }
        int ysize = info.getInt("range"); 
        islandMap.initialize(xsize, ysize);
        isOver = true;
    }
    
}
