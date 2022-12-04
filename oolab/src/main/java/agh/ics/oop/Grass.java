package agh.ics.oop;

public class Grass extends AbstractMapElement {

    public Grass(Vector2d position) {
        this.position = position;
    }
    public void setPosition(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
