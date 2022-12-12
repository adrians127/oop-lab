package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {


    //private final MoveDirection[] moveDirections;
    private final List<MoveDirection> moveDirections;
    private final IWorldMap map;
    private App app;
    private final int delay;
    private ArrayList<Animal> animals = new ArrayList<>();
    public SimulationEngine(MoveDirection[] moveDirections, IWorldMap map, Vector2d[] coordinates, App app, MapDirection direction) throws IllegalArgumentException{
        this.moveDirections = Arrays.stream(moveDirections).toList();
        this.app = app;
        this.map = map;
        this.delay = 300;
        for (Vector2d coordinate : coordinates){
            Animal animal = new Animal(map, coordinate, direction); // default orientation is North in Animal class
            map.place(animal);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        for (IMapElement val : map.getMapElements().values()) {
            if (val instanceof Animal) {
                animals.add((Animal) val);
                System.out.println(((Animal) val).position);
            }
        }
        for (int i = 0; i < moveDirections.size(); i++){
            System.out.println(map.toString());
            animals.get(i % animals.size()).move(moveDirections.get(i));
            Platform.runLater(() -> {
                app.draw((GrassField) map);
            });
            try {
                Thread.sleep(delay);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(map.toString());
    }

}
