package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Stop implements Action {

    @Override
    public void doAction(JSONObject response) {
        
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        return decision;
    }
}
