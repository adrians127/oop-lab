package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    public VBox vBox;
    public Label label;
    public GuiElementBox(IMapElement iMapElement) {
        try {
            vBox = new VBox();
            Image image = new Image(new FileInputStream(iMapElement.getImageSrc()));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            if (iMapElement instanceof Animal) {
                label = new Label(iMapElement.getPosition().toString());
            } else {
                label = new Label("trawa");
            }
            vBox.getChildren().addAll(imageView, label);
            vBox.setAlignment(Pos.CENTER);

        } catch (FileNotFoundException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie udalo sie zaladowac pliku z grafiką");
        }
    }
}
