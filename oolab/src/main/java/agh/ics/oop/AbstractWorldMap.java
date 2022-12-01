package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AbstractWorldMap implements IWorldMap {

    List<Grass> grassList;
    List<Animal> animalList;
    protected int grassNumber;

    MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected final Vector2d bottomLeftBorder;
    protected final Vector2d topRightBorder;
    protected Vector2d bottomLeftBorderDraw;
    protected Vector2d topRightBorderDraw;

    public AbstractWorldMap(Vector2d botLeft, Vector2d topRight, int grassNumber) {
        grassList = new ArrayList<>();
        animalList = new ArrayList<>();
        bottomLeftBorder = botLeft;
        topRightBorder = topRight;
        this.grassNumber = grassNumber;
    }

    protected Vector2d randomGrassPlacer () {
        int n = grassNumber;
        while (true) {
            Random random = new Random();
            Vector2d newPosition = new Vector2d(random.nextInt((int)Math.sqrt(n*10)), random.nextInt((int)Math.sqrt(n*10)));
            if (objectAt(newPosition) == null) {
                return newPosition;
            }
        }
    }

    private void setBorders () {
        bottomLeftBorderDraw = topRightBorder;
        topRightBorderDraw = bottomLeftBorder;
        // change for animalList or grass depends what you want to see
        animalList.forEach(
                at -> {
                    bottomLeftBorderDraw = bottomLeftBorderDraw.lowerLeft(at.getPosition());
                    topRightBorderDraw = topRightBorderDraw.upperRight(at.getPosition());
                }
        );
        bottomLeftBorderDraw.x -= 1;
        bottomLeftBorderDraw.y -= 1;
        topRightBorderDraw.x += 1;
        topRightBorderDraw.y += 1;
    }
    @Override
    public String toString() {
        setBorders();
        return mapVisualizer.draw(bottomLeftBorderDraw, topRightBorderDraw);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(bottomLeftBorder) &&
                position.precedes(topRightBorder)
                && isGrassThere(position) && !isOccupied(position) ;
    }
    public boolean isGrassThere(Vector2d position) {
        if (objectAt(position) instanceof Grass) {
            ((Grass) objectAt(position)).setPosition(randomGrassPlacer());
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if (objectAt(animal.getPosition()) instanceof Grass ) {
            ((Grass)objectAt(animal.getPosition())).setPosition(randomGrassPlacer());
            animalList.add(animal);
            return true;
        }
        if (!isOccupied(animal.getPosition())){
            animalList.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal at : animalList){
            if (at.getPosition().equals(position))
                return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: animalList) {
            if (animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }
}