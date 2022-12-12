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

    @Override
    public String getImageSrc() {
        return "src/main/resources/grass.png";
    }
}
