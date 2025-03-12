package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private POI emergencySite;
    private List<POI> creekList;
    private List<POI> otherPOIs;

    public Report() {
        creekList = new ArrayList<>();
        otherPOIs = new ArrayList<>();
    }

    public void addPOI(POI poi) {
        if (poi.getKind() == POI.TypeOfPOI.EMERGENCY_SITE) {
            if (emergencySite == null) {
                emergencySite = poi;
            }
        } else if (poi.getKind() == POI.TypeOfPOI.CREEK) {
            creekList.add(poi);
        } else {
            otherPOIs.add(poi);
        }
    }

    public POI getEmergencySite() {
        return emergencySite;
    }

    public List<POI> getCreeks() {
        return creekList;
    }

    public List<POI> getOtherPOIs() {
        return otherPOIs;
    }

    
    private double calculateDistance(Position p1, Position p2) {
        int x = p1.getx() - p2.getx();
        int y = p1.gety() - p2.gety();
        return Math.sqrt(x * x + y * y);
    }


    public String getClosestCreekReport() {
        if (emergencySite == null) {
            return "No emergency site found.";
        }
        if (creekList.isEmpty()) {
            return "No creeks found.";
        }
        POI closestCreek = null;
        double minDistance = Double.MAX_VALUE;
        for (POI creek : creekList) {
            double distance = calculateDistance(emergencySite.getLocation(), creek.getLocation());
            if (distance < minDistance) {
                minDistance = distance;
                closestCreek = creek;
            }
        }
        return "Closest creek to the emergency site (ID: " + emergencySite.getID() + ") is Creek (ID: " +
                closestCreek.getID() + ") with a distance of " + String.format("%.2f", minDistance);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Report:\n");
        sb.append("Emergency Site: ");
        if (emergencySite != null) {
            sb.append("ID: ").append(emergencySite.getID())
              .append(", Location: ").append(emergencySite.getLocation()).append("\n");
        } else {
            sb.append("Not Found\n");
        }
        sb.append("Creeks:\n");
        if (creekList.isEmpty()) {
            sb.append("None\n");
        } else {
            for (POI creek : creekList) {
                sb.append("ID: ").append(creek.getID())
                  .append(", Location: ").append(creek.getLocation()).append("\n");
            }
        }
        sb.append("Other POIs:\n");
        if (otherPOIs.isEmpty()) {
            sb.append("None\n");
        } else {
            for (POI poi : otherPOIs) {
                sb.append("ID: ").append(poi.getID())
                  .append(", Type: ").append(poi.getKind())
                  .append(", Location: ").append(poi.getLocation()).append("\n");
            }
        }
        sb.append(getClosestCreekReport());
        return sb.toString();
    }
}
