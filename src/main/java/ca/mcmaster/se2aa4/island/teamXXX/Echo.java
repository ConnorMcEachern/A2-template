package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Echo implements Action {
    private Direction direction;
    private Drone drone;
    private Island island;

    public Echo(Drone drone, Island island, Direction direction) {
        this.drone = drone;
        this.island = island;
        this.direction = direction;
    }

    @Override
    public void doAction(JSONObject response) {
        drone.reduceBattery(response.getInt("cost"));
        Position pos = drone.position();
        JSONObject info = response.getJSONObject("extras");
        for (int i = 0; i <= info.getInt("range"); i++) {
            island.search(pos);
            pos = pos.move(direction);
        }
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", new JSONObject().put("direction", direction));
        return decision;
    }
}
