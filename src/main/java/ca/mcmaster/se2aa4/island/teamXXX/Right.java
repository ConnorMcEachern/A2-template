package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Right implements Action {
    private Drone drone;

    public Right(Drone drone) {
        this.drone = drone;
    }

    @Override
    public void doAction(JSONObject response) {
        drone.reduceBattery(response.getInt("cost"));
        drone.turnRight();
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "heading");
        decision.put("parameters", new JSONObject().put("direction", drone.heading().right()));
        return decision;
    }
}
