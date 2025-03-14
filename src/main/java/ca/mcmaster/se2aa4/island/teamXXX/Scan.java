package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Scan implements Action {
    private Drone drone;
    private Island island;

    public Scan(Drone drone, Island island) {
        this.drone = drone;
        this.island = island;
    }

    @Override
    public void doAction(JSONObject response) {
        drone.reduceBattery(response.getInt("cost"));
        island.search(drone.position());
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        return decision;
    }
}
