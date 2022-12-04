package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {

    public static void run(Direction[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            System.out.print(args[i] + ", ");
        }
        if (args.length <= 0){
            return;
        }
        System.out.println(args[args.length - 1]);
        System.out.println("start");
        for (Direction at : args) {
            switch (at) {
                case F:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case B:
                    System.out.println("Zwierzak idzie do tylu");
                    break;
                case R:
                    System.out.println("Zwierzak skreca w prawo");
                    break;
                case L:
                    System.out.println("Zwierzak skreca w lewo");
                    break;
                case NONE:
                    break;
                default:
                    break;
            }
        }
        System.out.println("stop");
    }


    public static Direction[] stringToDirection(String[] tab) {
        return Arrays.stream(tab).map(s -> switch (s) {
            case "f" -> Direction.F;
            case "r" -> Direction.R;
            case "b" -> Direction.B;
            case "l" -> Direction.L;
            default -> null;
        }).filter(Objects::nonNull).toArray(Direction[]::new);
    }

    public static void main(String[] args) {
        // taken from lab4
        // f b r l f f r r f f f f f f f f for edit configurations
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(5);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }
    }
}