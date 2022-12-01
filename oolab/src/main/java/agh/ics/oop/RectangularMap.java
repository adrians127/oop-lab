package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{


    public RectangularMap(int width, int height){
        super(new Vector2d(0,0), new Vector2d(width,height), 0); // We assume width and height are always correct
    }
}
