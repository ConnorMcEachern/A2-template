package ca.mcmaster.se2aa4.island.teamXXX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;


public class ActionTesting {
    Island testIsland;
    Drone testDrone;

    @BeforeEach
    public void build() {
        testIsland = new Island();
        testIsland.initialize(10, 10);
        testDrone = new Drone(100, Direction.East(), new Position(0, 0));
    }
    
    @Test
    public void testBuild() {
        assertFalse(testIsland.isTileSearched(new Position(0, 0)));
        assertFalse(testIsland.isTileSearched(new Position(testIsland.getWidth()-1, testIsland.getHeight()-1)));
    }

    @Test
    public void testEcho() {
        JSONObject response = new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");

        new Echo(testDrone, testIsland, Direction.East()).doAction(response);
        assertTrue(testIsland.isTileSearched(new Position(0, 0)));
        assertTrue(testIsland.isTileSearched(new Position(1, 0)));
        assertTrue(testIsland.isTileSearched(new Position(2, 0)));
        assertFalse(testIsland.isTileSearched(new Position(3, 0)));
        assertEquals(99, testDrone.batteryLevel());
    }

    @Test
    public void testScan() {
        JSONObject response = new JSONObject("{ \"cost\": 1}");

        new Scan(testDrone, testIsland).doAction(response);
        assertTrue(testIsland.isTileSearched(new Position(0, 0)));
        assertEquals(99, testDrone.batteryLevel());
    }

    @Test
    public void testFly() {
        JSONObject response = new JSONObject("{ \"cost\": 1}");

        new Fly(testDrone).doAction(response);
        assertTrue(testDrone.position().equals(new Position(1, 0)));
        assertEquals(99, testDrone.batteryLevel());
    }

    @Test
    public void testTurns() {
        JSONObject response = new JSONObject("{ \"cost\": 1}");

        new Right(testDrone).doAction(response);
        assertTrue(testDrone.position().equals(new Position(1, 1)));
        assertTrue(testDrone.heading().equals(Direction.South()));
        new Left(testDrone).doAction(response);
        assertTrue(testDrone.position().equals(new Position(2, 2)));
        assertTrue(testDrone.heading().equals(Direction.East()));
        assertEquals(98, testDrone.batteryLevel());
    }
}
