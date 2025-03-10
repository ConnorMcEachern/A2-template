package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Heading implements Action {
    private String direction;

    public Heading(Direction direction) {
        this.direction = direction.toString();
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "heading");
        decision.put("parameters", new JSONObject().put("direction", direction));
        return decision;
    }
}
