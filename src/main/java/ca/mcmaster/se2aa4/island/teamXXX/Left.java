package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Left implements Action {
    private Drone drone;

    public Left(Drone drone) {
        this.drone = drone;
    }

    @Override
    public void doAction(JSONObject response) {
        drone.reduceBattery(response.getInt("cost"));
        drone.turnLeft();
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "heading");
        decision.put("parameters", new JSONObject().put("direction", drone.heading().left()));
        return decision;
    }
}
