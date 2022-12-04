package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationEngine implements IEngine {


    //private final MoveDirection[] moveDirections;
    private final List<MoveDirection> moveDirections;
    private final IWorldMap map;
    private final List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moveDirections, IWorldMap map, Vector2d[] coordinates) {
        this.moveDirections = Arrays.stream(moveDirections).toList();

        this.map = map;
        for (Vector2d coordinate : coordinates){
            Animal animal = new Animal(map, coordinate); // default orientation is North in Animal class
            map.place(animal);
            animals.add(animal);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < moveDirections.size(); i++){
            System.out.println(map.toString());
            animals.get(i % animals.size()).move(moveDirections.get(i));
        }
        System.out.println(map.toString());
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
