package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private Island island;
    private Action action;
    private Queue<Phase> phases;
    private Report report = new Report();


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
        island = new Island();
    
        phases = new LinkedList<>();            //ADD Phases here
        phases.add(new MakeMap(drone, island));    //Must be first phase

        phases.add(new SearchCoast(drone, island));
        phases.add(new GridSearch(drone, island));
        //Find inlets
        //Find emergency cite

        phases.add(new EndPhase());             //Must be the final phase
    }

    @Override
    public String takeDecision() {

        if (phases.peek().isOver()) {   //progess to next phase
            phases.remove();
            logger.info("***Starting next phase: " + phases.peek().getClass());
        }

        action = phases.peek().nextAction();
        JSONObject decision = action.getJSONObject();
        logger.info("** Decision:\n"+decision.toString(2));
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));

        try {
            action.doAction(response);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        

        logger.info("** Drone is at: "+ drone.position());
        logger.info("** Drone is facing: "+ drone.heading());
        logger.info("** Battery level is: "+ drone.batteryLevel());

        if (action.getClass().equals(Scan.class)) {
            JSONObject extras = response.getJSONObject("extras");
            if (extras.has("creeks")) {
                JSONArray creeks = extras.getJSONArray("creeks");
                for (int i = 0; i < creeks.length(); i++) {
                    JSONObject creekObj = creeks.getJSONObject(i);
                    int x = drone.position().getx();
                    int y = drone.position().gety();
                    String id = creekObj.getString("id");
                    POI creekPOI = new POI(POI.TypeOfPOI.CREEK, new Position(x, y), id);
                    report.addPOI(creekPOI);
                }
            }

            if (extras.has("sites")) {
                JSONObject emergencyObj = extras.getJSONObject("sites");
                int x = drone.position().getx();
                int y = drone.position().gety();
                String id = emergencyObj.getString("id");
                POI emergencyPOI = new POI(POI.TypeOfPOI.EMERGENCY_SITE, new Position(x, y), id);
                report.addPOI(emergencyPOI);
            }
            phases.peek().getInfoFromScan(response.getJSONObject("extras"));
        } else if (action.getClass().equals(Echo.class)) {
            phases.peek().getInfoFromEcho(response.getJSONObject("extras"));
        }
    }

    @Override
    public String deliverFinalReport() {
        String finalReport = report.toString();
        return finalReport;
    }

}
