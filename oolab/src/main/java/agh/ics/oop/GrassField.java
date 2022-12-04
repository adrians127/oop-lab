package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GrassField extends AbstractWorldMap{


    public GrassField(int grassNumber) {
        super(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE),
                new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE), grassNumber);
        createGrassField(grassNumber);
    }

    private void createGrassField(int n) {
        for (int i = 0; i < n; i++) {
            Vector2d generatedPosition = randomGrassPlacer();
            mapElements.put(generatedPosition, new Grass(generatedPosition));
            mapBoundary.addPosition(generatedPosition);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
