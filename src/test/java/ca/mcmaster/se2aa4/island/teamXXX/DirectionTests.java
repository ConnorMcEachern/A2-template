package ca.mcmaster.se2aa4.island.teamXXX;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DirectionTests {
    @Test
    public void testFromString() {
        assertTrue(Direction.East().equals(Direction.directionFromString("E")));
        assertTrue(Direction.South().equals(Direction.directionFromString("S")));
        assertTrue(Direction.West().equals(Direction.directionFromString("W")));
        assertTrue(Direction.North().equals(Direction.directionFromString("N")));
    }

    @Test
    public void testToString() {
        assertEquals("N", Direction.North().toString());
        assertEquals("E", Direction.East().toString());
        assertEquals("S", Direction.South().toString());
        assertEquals("W", Direction.West().toString());
    }

    @Test
    public void rights() {
        Direction dir = Direction.North();

        assertTrue(Direction.East().equals(dir.right()));
        assertTrue(Direction.South().equals(dir.right().right()));
        assertTrue(Direction.West().equals(dir.right().right().right()));
        assertTrue(Direction.North().equals(dir.right().right().right().right()));
    }

    @Test
    public void lefts() {
        Direction dir = Direction.North();

        assertTrue(Direction.West().equals(dir.left()));
        assertTrue(Direction.South().equals(dir.left().left()));
        assertTrue(Direction.East().equals(dir.left().left().left()));
        assertTrue(Direction.North().equals(dir.left().left().left().left()));
    }
}
