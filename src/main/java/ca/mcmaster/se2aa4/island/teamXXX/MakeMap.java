package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class MakeMap extends Phase implements EchoInfoReceiver{
    private Integer xsize;
    private boolean isOver = false;

    public MakeMap(Drone drone, Island island) {
        this.drone = drone;
        this.island = island;
    }

    @Override
    public Action nextAction() {
        if (xsize == null) {
            return new Echo(drone, island, Direction.East());
        }
        return new Echo(drone, island, Direction.South());
    }

    @Override
    public boolean isOver() {
        return isOver;
    }

    @Override
    public void getInfoFromEcho(JSONObject info) {
        if (xsize == null) {
            xsize = info.getInt("range") + 1;
            return;
        }
        int ysize = info.getInt("range") + 1; 
        island.initialize(xsize, ysize);
        isOver = true;
    }
    
}
