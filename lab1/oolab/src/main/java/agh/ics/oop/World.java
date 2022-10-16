package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {

    public static void run(Direction[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            System.out.print(args[i] + ", ");
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
        return Arrays.stream(tab).filter(s -> s.equals("f") || s.equals("r")
                || s.equals("b") || s.equals("l")).map(s -> switch (s) {
            case "f" -> Direction.F;
            case "r" -> Direction.R;
            case "b" -> Direction.B;
            case "l" -> Direction.L;
            default -> Direction.NONE;
        }).toArray(Direction[]::new);
    }

    public static void main(String[] args) {
        System.out.println("system wystartowal");
        Direction[] directions = stringToDirection(args);
        run(directions);


        System.out.println("system zakonczyl dzialanie");
    }
}