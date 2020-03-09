package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Scene scene = new Scene(root, 750, 450);
        scene.setFill(Color.TEAL);

        Polygon mainPolygon = new Polygon();
        mainPolygon.getPoints().addAll(new Double[]{
                385.0, 60.0,
                680.0, 180.0,
                495.0, 265.0,
                555.0, 390.0,
                290.0, 410.0,
                190.0, 205.0
        });
        SetPolygonParams(mainPolygon, Color.LIME);

        Line middleLine = new Line(190, 205, 495, 265);
        Line upperMustache = new Line(115, 60, 240, 170);
        Line lowerMustache = new Line(100, 365, 235, 295);
        SetLineParams(middleLine, 5);
        SetLineParams(upperMustache, 12);
        SetLineParams(lowerMustache, 12);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                645.0, 240.0,
                575.0, 355.0,
                525.0, 265.0
        });
        SetPolygonParams(triangle, Color.YELLOW);

        Rectangle upperEye = new Rectangle(320, 165, 18, 16);
        Rectangle lowerEye = new Rectangle(290, 270, 18, 16);
        SetRectangleParams(upperEye);
        SetRectangleParams(lowerEye);

        root.getChildren().addAll(mainPolygon, middleLine, triangle,
                lowerEye, upperEye, upperMustache, lowerMustache);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab 1");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void SetLineParams(Line line, int strokeWidth) {
        line.setStrokeWidth(strokeWidth);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        line.setSmooth(false);
    }

    private void SetPolygonParams(Polygon polygon, Color color) {
        polygon.setFill(color);
        polygon.setSmooth(false);
    }

    private void SetRectangleParams(Rectangle rectangle) {
        rectangle.setFill(Color.GREEN);
    }
}
