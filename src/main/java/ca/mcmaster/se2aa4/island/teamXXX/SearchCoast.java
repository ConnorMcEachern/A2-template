package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class SearchCoast extends Phase {

    public SearchCoast(Drone drone, Island map) {
        this.drone = drone;
        this.islandMap = map;
    }

    @Override
    public void initialize() {
        for (int i = 0; i < islandMap.getWidth()/2; i++) {
            flyDrone();
        }
        turnDroneRight();
        echo(drone.heading());
    }

    @Override
    public void getInfoFromScan(JSONObject info) {
        isOver = true;
    }

    @Override
    public void getInfoFromEcho(JSONObject info) {
        for (int i = 0; i <= info.getInt("range"); i++) {
            flyDrone();
        }
        scan();
    }
    
}
