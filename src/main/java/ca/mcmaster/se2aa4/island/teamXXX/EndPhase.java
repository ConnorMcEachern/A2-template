package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class EndPhase extends Phase {

    @Override
    public Action nextAction() {
        return new Stop();
    }

    @Override
    public boolean isOver() {
        return false;
    }
    
}
