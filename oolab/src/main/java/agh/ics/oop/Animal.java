package agh.ics.oop;

public class Animal extends AbstractMapElement {
    private MapDirection direction;
    private IWorldMap map;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map){
        this();
        //this.direction = MapDirection.NORTH;
        //this.position = new Vector2d(2, 2);
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this(map);
        this.position = initialPosition;
    }

    @Override
    public String toString() {
        return switch (this.direction) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position); //do zobaczenia
    }

    public MapDirection getDirection() {
        return direction;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> this.position = executeMove(this.direction.toUnitVector());
            case BACKWARD -> this.position = executeMove(this.direction.toUnitVector().opposite()); // changing sign for using same method "add"
        }
    }

    private Vector2d executeMove(Vector2d step){
        Vector2d newPosition = position.add(step);
        return map.canMoveTo(newPosition) ? newPosition : position;
    }

}
