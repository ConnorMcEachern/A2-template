package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class Controller {

    private List<Action> decisions = new ArrayList<Action>();

    public Controller() {
        decisions.addLast(new Echo(Direction.East()));
        decisions.addLast(new Echo(Direction.South()));
        for (int i = 0; i<50; i++) {
            decisions.addLast(new Scan());
            decisions.addLast(new Fly());
        }
        decisions.addLast(new Stop());
    }

    public Action nextAction() {
        return decisions.removeFirst();
    }

    public void getInfoFromScan(JSONObject info) {

    }

    public void getInfoFromEcho(JSONObject info) {
        
    }
}
