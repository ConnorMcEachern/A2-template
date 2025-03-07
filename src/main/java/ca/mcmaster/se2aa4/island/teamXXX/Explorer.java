package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private Island map;
    private Action action;
    private Queue<Phase> phases;
    private Phase currentPhase;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);

        drone = new Drone(batteryLevel, Direction.directionFromString(direction), new Position(0, 0));
        currentPhase = new MakeMap(drone, map);
        phases = new LinkedList<>();
    }

    @Override
    public String takeDecision() {
        JSONObject decision;
        if (phases.peek().isOver()) {
            if (phases.isEmpty()) {
                return new JSONObject().put("action", "stop").toString();
            }
            currentPhase = phases.remove();
        }

        action = phases.peek().nextAction();
        decision = action.getJSONObject();
        logger.info("** Decision: {}", decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));

        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        drone.reduceBattery(cost);

        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);

        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        if (action.getClass().equals(Scan.class)) {
            currentPhase.getInfoFromScan(extraInfo);
        } else if (action.getClass().equals(Echo.class)) {
            currentPhase.getInfoFromEcho(extraInfo);
        }
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
