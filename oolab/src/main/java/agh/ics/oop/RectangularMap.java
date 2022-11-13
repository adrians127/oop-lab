package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    private final int width, height, xmin, ymin; // xmin, ymin?
    private final MapVisualizer mapVisualizer;
    private final List<Animal> animals;

    public RectangularMap(int width, int height){ // We assume width and height are always correct
        this.width = width;
        this.height = height;
        this.xmin = 0;
        this.ymin = 0;
        this.animals = new ArrayList<>();
        this.mapVisualizer = new MapVisualizer(this);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(xmin, ymin)) &&
                position.precedes(new Vector2d(width - 1, height - 1)) &&
                !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals){
            if (animal.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if (animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(width - 1, height - 1));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
