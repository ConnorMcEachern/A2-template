package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class EndPhase extends Phase {

    @Override
    public void initialize() {
        stop();
    }

    @Override
    public void getInfoFromScan(JSONObject info) {
        
    }

    @Override
    public void getInfoFromEcho(JSONObject info) {
        
    }
    
}
