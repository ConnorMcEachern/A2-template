package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Echo implements Action {
    private String direction;

    public Echo(String direction) {
        this.direction = direction;
    }

    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", new JSONObject().put("direction", direction));
        return decision;
    }
}
