package agh.ics.oop;

import java.util.*;

public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    List<Grass> grassList;
    List<Animal> animalList;

    HashMap<Vector2d, IMapElement> mapElements = new HashMap<>();

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
        for (IMapElement mapElement : mapElements.values()) {
            bottomLeftBorderDraw = bottomLeftBorderDraw.lowerLeft(mapElement.getPosition());
            topRightBorderDraw = topRightBorderDraw.upperRight(mapElement.getPosition());
        }
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
                && isGrassThere(position) && !(objectAt(position) instanceof Animal) ;
    }

    private void positionChangeGrass(Vector2d position) {
        Vector2d newPosition = randomGrassPlacer();
        Grass tempGrass = (Grass) objectAt(position);
        tempGrass.setPosition(newPosition);
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
        if (objectAt(animal.getPosition()) instanceof Animal){
            return false;
        }
        if (objectAt(animal.getPosition()) instanceof Grass ) {
            positionChangeGrass(animal.getPosition());
//            mapElements.put(animal.getPosition(), animal);
//            return true;
        }
        mapElements.put(animal.getPosition(), animal);
        animal.addObserver(this);

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
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        // jezeli bedzie tam trawa modify
        Animal tempAnimal = (Animal) mapElements.get(oldPosition);
        mapElements.remove(oldPosition);
        mapElements.put(newPosition, tempAnimal);
        return true;

    }
}
