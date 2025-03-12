package ca.mcmaster.se2aa4.island.teamXXX;
import org.json.JSONObject;

public interface Action {
    public void doAction(JSONObject response);
    public JSONObject getJSONObject();
}
