package ca.mcmaster.se2aa4.island.teamXXX;

/*
 * This class is for creating the main logic 
 * Create a subclass and fill in the abstract methods
 */
public abstract class Phase {
    protected Drone drone;
    protected Island island;

    public abstract Action nextAction();                    //Define logic behind next action
    public abstract boolean isOver();                       //Informs the explorer to progress to the next state
}
