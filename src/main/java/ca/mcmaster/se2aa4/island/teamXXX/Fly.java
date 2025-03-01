package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Fly implements Action {
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        return decision;
    }

}
