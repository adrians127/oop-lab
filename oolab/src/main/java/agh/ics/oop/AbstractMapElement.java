package agh.ics.oop;

public class AbstractMapElement implements IMapElement{
    protected Vector2d position;
    @Override
    public Vector2d getPosition() {
        return position;
    }
}
