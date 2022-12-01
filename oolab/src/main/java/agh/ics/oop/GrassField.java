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
            grassList.add(new Grass(randomGrassPlacer()));
        }
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)){
            return true;
        }
        for (Grass at : grassList){
            if (at.getPosition().equals(position))
                return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        for (Grass grass : grassList){
            if (grass.getPosition().equals(position)){
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
