package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    void equalsTest() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(2, 3);
        assertTrue(v1.equals(v2));
        v2 = new Vector2d(2, 4);
        assertFalse(v1.equals(v2));
        v2 = new Vector2d(3, 3);
        assertFalse(v1.equals(v2));
    }

    @Test
    void toStringTest() {
        Vector2d v1 = new Vector2d(2, 3);
        assertEquals(v1.toString(), "(2,3)");
        assertNotEquals(v1.toString(), "(2,2)");
    }

    @Test
    void precedesTest() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(4, 5);
        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v1));
    }

    @Test
    void followsTest() {
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d v1 = new Vector2d(4, 5);
        assertTrue(v1.follows(v2));
        assertFalse(v2.follows(v1));
    }

    @Test
    void upperRightTest() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(4, 5);
        assertEquals(v1.upperRight(v2), new Vector2d(4, 5));
        assertNotEquals(v1.upperRight(v2), new Vector2d(2, 3));
    }

    @Test
    void lowerLeftTest() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(4, 5);
        assertNotEquals(v1.lowerLeft(v2), new Vector2d(4, 5));
        assertEquals(v1.lowerLeft(v2), new Vector2d(2, 3));
    }

    @Test
    void oppositeTest() {
        Vector2d v1 = new Vector2d(0, 0);
        assertEquals(v1.opposite(), new Vector2d(0, 0));
        v1 = new Vector2d(1, 35);
        assertEquals(v1.opposite(), new Vector2d(-1, -35));
    }
    @Test
    void addTest(){
        Vector2d v1 = new Vector2d(1,3);
        Vector2d v2 = new Vector2d(11,3);
        assertEquals(v1.add(v2), new Vector2d(12,6));
    }@Test
    void subtractTest(){
        Vector2d v1 = new Vector2d(1,3);
        Vector2d v2 = new Vector2d(11,3);
        assertEquals(v1.subtract(v2), new Vector2d(-10,0));
    }
}
