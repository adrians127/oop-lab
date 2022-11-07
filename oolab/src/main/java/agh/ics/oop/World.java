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
        Animal animal = new Animal();
        System.out.println(animal.toString());
        String[] bom = {"f", "b", "geeee", "forward", "backward"};
        OptionsParser optionsParser = new OptionsParser();
        MoveDirection[] moveDirections;
        moveDirections = optionsParser.parse(bom);
        for (MoveDirection i : moveDirections){
            System.out.println(i);
        }
        System.out.println(animal.toString());

        MoveDirection[] moveDirectionList = {MoveDirection.LEFT};
        String[] wyr = {"l"};
        MoveDirection[] moveDirectionList1 = optionsParser.parse(wyr);
        for (MoveDirection i : moveDirectionList){
            System.out.println(i);
        }
        for (MoveDirection i : moveDirectionList1){
            System.out.println(i);
        }
        if (Arrays.equals(moveDirectionList1, moveDirectionList))
        {System.out.println("be");}

    }
}