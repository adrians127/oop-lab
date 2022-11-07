package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    @Override
    public String toString() {
        return "Polozenie: " + position + ", Orientacja: " + direction;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position); //do zobaczenia
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> {
                this.position = this.position.add(this.direction.toUnitVector());
                this.position = this.position.lowerLeft(new Vector2d(4, 4));
                this.position = this.position.upperRight((new Vector2d(0, 0)));
            }
            case BACKWARD -> {
                this.position = this.position.subtract(this.direction.toUnitVector());
                this.position = this.position.lowerLeft(new Vector2d(4, 4));
                this.position = this.position.upperRight((new Vector2d(0, 0)));
            }
        }
    }

}
