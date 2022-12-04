package agh.ics.oop;

import java.util.*;

public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    HashMap<Vector2d, IMapElement> mapElements = new HashMap<>();

    protected int grassNumber;

    public MapBoundary mapBoundary = new MapBoundary();

    MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected final Vector2d bottomLeftBorder;
    protected final Vector2d topRightBorder;
    protected Vector2d bottomLeftBorderDraw;
    protected Vector2d topRightBorderDraw;

    public AbstractWorldMap(Vector2d botLeft, Vector2d topRight, int grassNumber) {

        bottomLeftBorder = botLeft;
        topRightBorder = topRight;
        this.grassNumber = grassNumber;
    }

    protected Vector2d randomGrassPlacer() {
        int n = grassNumber;
        while (true) {
            Random random = new Random();
            Vector2d newPosition = new Vector2d(random.nextInt((int) Math.sqrt(n * 10)), random.nextInt((int) Math.sqrt(n * 10)));
            if (objectAt(newPosition) == null) {
                return newPosition;
            }
        }
    }
    @Override
    public String toString() {
        Vector2d[] borders = mapBoundary.getPositions();
        return mapVisualizer.draw(borders[0], borders[1]);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(bottomLeftBorder) &&
                position.precedes(topRightBorder)
                && isGrassThere(position) && !(objectAt(position) instanceof Animal);
    }

    private void positionChangeGrass(Vector2d position) {
        Vector2d newPosition = randomGrassPlacer();
        Grass tempGrass = (Grass) objectAt(position);
        tempGrass.setPosition(newPosition);
        mapBoundary.positionChanged(position, newPosition);
        mapElements.remove(position);
        mapElements.put(newPosition, tempGrass);
    }

    public boolean isGrassThere(Vector2d position) {
        if (objectAt(position) instanceof Grass) {
            positionChangeGrass(position);
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if (objectAt(animal.getPosition()) instanceof Animal) {
            throw new IllegalArgumentException(animal.getPosition().toString());
        }
        if (objectAt(animal.getPosition()) instanceof Grass) {
            positionChangeGrass(animal.getPosition());
//            mapElements.put(animal.getPosition(), animal);
//            return true;
        }
        mapElements.put(animal.getPosition(), animal);
        animal.addObserver(this);
        mapBoundary.addPosition(animal.getPosition());

        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        // jezeli bedzie tam trawa modify
        Animal tempAnimal = (Animal) mapElements.get(oldPosition);
        mapElements.remove(oldPosition);
        mapElements.put(newPosition, tempAnimal);
        mapBoundary.positionChanged(oldPosition, newPosition);
    }
}
