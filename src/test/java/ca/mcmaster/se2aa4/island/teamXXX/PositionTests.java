package ca.mcmaster.se2aa4.island.teamXXX;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PositionTests {
    @Test
    public void testToString() {
        Position pos = new Position(4, 3);
        
        assertEquals("(7, 3)", pos.move(Direction.East()).move(Direction.East()).move(Direction.East()).toString());
        assertEquals("(6, 3)", pos.move(Direction.East()).move(Direction.East()).toString());
        assertEquals("(5, 3)", pos.move(Direction.East()).toString());
        assertEquals("(4, 3)", pos.toString());

    }

    @Test
    public void getters() {
        Position pos = new Position(2, 3);
        assertEquals(2, pos.getx());
        assertEquals(3, pos.gety());
    }

    @Test
    public void moving() {
        Position pos = new Position(0, 0);
        assertTrue(pos.move(Direction.North()).equals(new Position(0, -1)));
        assertTrue(pos.move(Direction.East() ).equals(new Position(1, 0)));
        assertTrue(pos.move(Direction.South()).equals(new Position(0, 1)));
        assertTrue(pos.move(Direction.West() ).equals(new Position(-1, 0)));
        assertTrue(pos.equals(new Position(0, 0)));
    }
}
