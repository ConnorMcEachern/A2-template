package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Fly implements Action {
    private Drone drone;

    public Fly(Drone drone) {
        this.drone = drone;
    }

    @Override
    public void doAction(JSONObject response) {
        drone.reduceBattery(response.getInt("cost"));
        drone.fly();
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        return decision;
    }

}
