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

        phases.add(new GoMiddle(drone, island));    //Fly to the middle of the map
        phases.add(new GridSearch(drone, island));  //Grid search turning left
        phases.add(new GridSearch2(drone, island)); //Once the site is found, grid search turning right
        //Find inlets
        //Find emergency cite

        phases.add(new EndPhase());             //Must be the final phase
    }

    @Override
    public String takeDecision() {

        if (phases.peek().isOver()) {   //progess to next phase
            phases.remove();
        }
        logger.info("** Phase: " + phases.peek().getClass());

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

        JSONObject extras = response.getJSONObject("extras");
        if (action.getClass().equals(Scan.class)) {

            JSONArray creeks = extras.getJSONArray("creeks");
            for (Object id : creeks) {
                POI creekPOI = new POI(POI.TypeOfPOI.CREEK, drone.position(), id.toString());
                report.addPOI(creekPOI);
            }

            JSONArray sites = extras.getJSONArray("sites");
            for (Object id : sites) {
                POI creekPOI = new POI(POI.TypeOfPOI.EMERGENCY_SITE, drone.position(), id.toString());
                report.addPOI(creekPOI);
            }
            Phase currentPhase = phases.peek();
            if(currentPhase instanceof ScanInfoReceiver){
                ((ScanInfoReceiver) phases.peek()).getInfoFromScan(extras);
            }
        } else if (action.getClass().equals(Echo.class)) {
            Phase currentPhase = phases.peek();
            if(currentPhase instanceof EchoInfoReceiver){
                ((EchoInfoReceiver) phases.peek()).getInfoFromEcho(extras);
            }
        }
    }

    @Override
    public String deliverFinalReport() {
        logger.info(report);
        String finalReport = report.toString();

        return finalReport;
    }

}
