package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void parseTest(){
        OptionsParser optionsParser = new OptionsParser();
        MoveDirection[] moveDirectionList = {
                MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD};
        //assertEquals(moveDirectionList, moveDirectionList1); dla tablic nie dziala trzeba uzyc arrayequals lub assertTrue
        assertTrue(Arrays.equals(moveDirectionList, optionsParser.parse(new String[]{
                "l", "b", "r", "f", "left", "backward", "right", "forward"
        })));
        assertArrayEquals(moveDirectionList, optionsParser.parse(new String[]{
                "l", "b", "r", "f", "left", "backward", "right", "forward"
        }));
        assertThrows(IllegalArgumentException.class, ()->new OptionsParser().parse(new String[] {
                "l", "b", "sheesh", "left"
        }));
    }

}