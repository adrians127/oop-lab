package agh.ics.oop;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    TreeSet<Vector2d> xSet = new TreeSet<>(Comparator.comparingInt(vector2d -> vector2d.x));
    TreeSet<Vector2d> ySet = new TreeSet<>(Comparator.comparingInt(vector2d -> vector2d.y));

    // alternative:

    public void addPosition (Vector2d position){
        xSet.add(position);
        ySet.add(position);

    }

    private void removePosition(Vector2d position) {
        xSet.remove(position);
        ySet.remove(position);
    }

    public Vector2d[] getPositions(){
        return new Vector2d[]{new Vector2d(xSet.first().x, ySet.first().y), new Vector2d(xSet.last().x, ySet.last().y)};
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removePosition(oldPosition);
        addPosition(newPosition);

    }
}
