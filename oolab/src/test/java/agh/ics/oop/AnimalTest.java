package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    void isAtTest() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal animal = new Animal(map);
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(2,1)));
        assertTrue(animal.isAt(new Vector2d(2,1)));
    }
    @Test
    void moveTest() {
        int xmin = 0;
        int ymin = 0;
        IWorldMap map = new RectangularMap(10, 5);
        Animal animal = new Animal(map);

        //check walking
        animal.move(MoveDirection.BACKWARD);

        System.out.println(animal.getPosition());
        assertEquals(new Vector2d(2,1), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getDirection());
        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal.getDirection());
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(3,1), animal.getPosition());
        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, animal.getDirection());
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(3, ymin), animal.getPosition());

        //check borders
         for (int i = 0; i < 5; i++){
             animal.move(MoveDirection.FORWARD);
         }
        assertEquals(new Vector2d(3, 4), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getDirection());
        for (int i = 0; i < 5; i++){
            animal.move(MoveDirection.BACKWARD);
        }
        assertEquals(new Vector2d(3, ymin), animal.getPosition());
        animal.move(MoveDirection.RIGHT);
        for (int i = 0; i < 5; i++){
            animal.move(MoveDirection.FORWARD);
        }
        assertEquals(new Vector2d(8, ymin), animal.getPosition());
        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, animal.getDirection());
        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, animal.getDirection());
        for (int i = 0; i < 5; i++){
            animal.move(MoveDirection.FORWARD);
        }
        assertEquals(new Vector2d(3, ymin), animal.getPosition());
    }

}