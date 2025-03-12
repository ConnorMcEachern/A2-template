package ca.mcmaster.se2aa4.island.teamXXX;


public class POI {

    public enum TypeOfPOI {
        CREEK,
        EMERGENCY_SITE
    }

    private TypeOfPOI kind;
    private Position location;
    private String id;

    public POI(TypeOfPOI kind, Position location, String id) {
        this.kind = kind;
        this.location = location;
        this.id = id;
    }

    public TypeOfPOI getKind() {
        return this.kind;
    }

    public Position getLocation() {
        return new Position(location.getx(), location.gety());
    }

    public String getID(){
        return this.id;
    }
 }


