package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Stop implements Action {
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        return decision;
    }

}
