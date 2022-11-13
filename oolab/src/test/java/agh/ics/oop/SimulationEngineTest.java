package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {
    @Test
    void runAnimalTest () {
        //first test
        String[] directionsAsString =
                new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f","f","f"};
        Vector2d[] initialPositions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = engineStart(directionsAsString, initialPositions);
        engine.run();
        // (0,1) (9,4) should be
        Vector2d[] positionsAfterRun = new Vector2d[]{new Vector2d(2, 0), new Vector2d(3, 4)};
        areAnimalPlacedGood(engine, positionsAfterRun);

        // second test
        directionsAsString = new String[]{};
        initialPositions = new Vector2d[]{new Vector2d(3,3), new Vector2d(0,0)};
        engine = engineStart(directionsAsString, initialPositions);
        engine.run();
        positionsAfterRun = initialPositions;
        areAnimalPlacedGood(engine, positionsAfterRun);

        //third test
        directionsAsString = new String[]{"forward", "backward", "left", "left", "left", "left", "forward"};
        initialPositions = new Vector2d[]{new Vector2d(6,3), new Vector2d(4, 4), new Vector2d(2,3)};
        engine = engineStart(directionsAsString, initialPositions);
        engine.run();
        positionsAfterRun = new Vector2d[]{new Vector2d(5,4), new Vector2d(4, 3), new Vector2d(2,3)};
        areAnimalPlacedGood(engine, positionsAfterRun);

    }

    private SimulationEngine engineStart (String[] args, Vector2d[] initialPositions) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        SimulationEngine engine = new SimulationEngine(directions, map, initialPositions);
        areAnimalPlacedGood(engine, initialPositions);
        return engine;
    }

    private void areAnimalPlacedGood (SimulationEngine engine, Vector2d[] initialPositions) {
        List<Animal> animals = engine.getAnimals();
        assertEquals(engine.getAnimals().size(), initialPositions.length);
        for (int i = 0; i < animals.size(); i++){
            assertEquals(animals.get(i).getPosition(), initialPositions[i]);
        }
    }

}