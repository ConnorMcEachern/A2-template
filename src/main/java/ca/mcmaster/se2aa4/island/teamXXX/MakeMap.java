package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class MakeMap extends Phase {
    Integer xsize;

    public MakeMap(Drone drone, Island map) {
        this.drone = drone;
        this.islandMap = map;
        echo(drone.heading());
        echo(drone.heading().turnRight());
    }

    public void getInfoFromScan(JSONObject info) {

    }

    public void getInfoFromEcho(JSONObject info) {
        if (xsize == null) {
            xsize = info.getInt("range");
            return;
        }
        int ySize = info.getInt("range"); 
        islandMap = new Island(xsize, ySize);
    }

    public boolean isOver() {
        return false;
    }
    
}
