package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class App extends Application {

    private AbstractWorldMap map;

    public App() {
        super();
    }

    public void draw(Stage primaryStage){
        Vector2d botLeft = map.mapBoundary.getPositions()[0];
        Vector2d topRight = map.mapBoundary.getPositions()[1];

        int height = 40;
        int width = 40;

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        grid.getRowConstraints().add(new RowConstraints(height));
        grid.getColumnConstraints().add(new ColumnConstraints(width));

        Label label = new Label("x/y");
        GridPane.setHalignment(label, HPos. CENTER); // podobno lepsze niz label.setAlignment

        grid.add(label, 0, 0);

        for (int x = botLeft.x; x <= topRight.x; x++){
            label = new Label(Integer.toString(x));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(width));
            grid.add(label, x-botLeft.x+1, 0);
        }

        for (int y = topRight.y; y >= botLeft.y; y--){
            label = new Label((Integer.toString(y)));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(height));
            grid.add(label, 0, topRight.y - y + 1);
        }

        for (int x = botLeft.x; x <=topRight.x; x++){
            for (int y = topRight.y; y >= botLeft.y; y--) {
                Vector2d pos = new Vector2d(x, y);
                if (map.objectAt(pos) != null){
                    label = new Label(map.objectAt(pos).toString());
                    GridPane.setHalignment(label, HPos.CENTER);
                    label.setFont(new Font("Arial", 35));
                    label.setTextFill(Color.rgb(255,0,0));
                    grid.add(label, x - botLeft.x + 1, topRight.y - y + 1);
                }
            }
        }

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(15);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            draw(primaryStage);

        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }
    }


    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

}
