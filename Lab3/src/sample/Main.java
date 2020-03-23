package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 720);

        Color redEdge = Color.rgb(172, 19, 24);
        Color red = Color.rgb(221, 23, 32);
        Color lightRedLine = Color.rgb(220, 78, 63);
        Color brown = Color.rgb(114, 57, 4);
        Color lightPink = Color.rgb(251, 219, 206);

        LinearGradient colorInsideHeart = new LinearGradient(65,85,145,166,
                false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(245, 137, 108)), new Stop(1, Color.rgb(234, 29, 36)));

        LinearGradient colorInsideTape = new LinearGradient(0,170, 75,170,
                false, CycleMethod.REFLECT,
                new Stop(0, Color.rgb(152, 92, 38)), new Stop(1, Color.rgb(253, 243, 171)));

        Color repaint = Color.rgb(234, 29, 36);
        LinearGradient colorInsideSmallEdge = new LinearGradient(109,232, 137,280,
                false, CycleMethod.NO_CYCLE,
                new Stop(0, repaint), new Stop(1, Color.rgb(248, 135, 103)));
        LinearGradient colorInsideSmallTriangle = new LinearGradient(0,260, 0,285,
                false, CycleMethod.NO_CYCLE,
                new Stop(0, repaint), new Stop(1, Color.rgb(252, 107, 80)));

        // ліва задня часть ленти
        {
            Path path = new Path();
            path.getElements().addAll(
                    new MoveTo(40, 158),
                    //new ArcTo(30, 10, 0, 0, 142, false, false)
                    //new ArcTo(30, 10, 0, 232, 132, false, true)
                    new ArcTo(24, 10, 0, 34, 128, false, true)
            );
            path.setStroke(brown);
            path.setFill(brown);
            root.getChildren().add(path);
        }
        // права задня часть ленти
        {
            Path path = new Path();
            path.getElements().addAll(
                    new MoveTo(240, 176),
                    new ArcTo(30, 10, 15, 287, 195, false, true),
                    new ArcTo(30, 10, 0, 230, 195, false, true)
                    //new ArcTo(30, 12, 10, 231, 195, true, true));
            );
            path.setStroke(brown);
            path.setFill(brown);
            root.getChildren().add(path);
        }

        // 'грубе' серце
        {
            Path path = new Path();
            path.getElements().addAll(
                    // left upper heart hide
                    new MoveTo(47, 160),
                    new ArcTo(50, 60, 0, 151, 99, true, true),
                    // right upper heart hide
                    new ArcTo(45, 45, 0, 251, 125, false, true),

                    new QuadCurveTo(230, 220, 141, 273),
                    new QuadCurveTo(47, 160, 47, 160)
            );
            path.setStrokeWidth(13);
            path.setStrokeLineJoin(StrokeLineJoin.BEVEL);
            path.setStrokeLineCap(StrokeLineCap.BUTT);
            path.setStroke(redEdge);
            path.setFill(colorInsideHeart);
            root.getChildren().add(path);
        }
        // нижня частина серця (перекраска границь)
        {
            Path path = new Path();
            path.getElements().addAll(
                    new MoveTo(251, 125),
                    new QuadCurveTo(230, 220, 141, 273),
                    new QuadCurveTo(47, 160, 47, 160)
            );
            path.setStrokeWidth(13.2);
            path.setStrokeLineJoin(StrokeLineJoin.BEVEL);
            path.setStrokeLineCap(StrokeLineCap.BUTT);
            path.setStroke(Color.rgb(234, 29, 36));
            root.getChildren().add(path);
        }
        // нижня частина серця
        {
            Path p1 = new Path();
            p1.getElements().addAll(
                    new MoveTo(112, 230),
                    new QuadCurveTo(146, 260, 134, 285),
                    new QuadCurveTo(138, 276, 106, 240),
                    new LineTo(112, 230)
            );
            p1.setStroke(colorInsideSmallEdge);
            p1.setFill(colorInsideSmallEdge);

            Path p2 = new Path();
            p2.getElements().addAll(
                    new MoveTo(127, 250),
                    new LineTo(184, 250),
                    new QuadCurveTo(136, 287, 134, 285),
                    new QuadCurveTo(140, 255, 127, 250)
            );
            p2.setStroke(colorInsideSmallTriangle);
            p2.setFill(colorInsideSmallTriangle);
            root.getChildren().addAll(p2, p1);
        }

        // curve inside heart
        {
            Path p = new Path();
            int startX = 144;
            int startY = 98;
            p.getElements().addAll(
                    new MoveTo(startX, startY),
                    new LineTo(158, startY-4),
                    new QuadCurveTo(172, 100, 180, startY-4),
                    new QuadCurveTo(168, 105, 154, 105),
                    new QuadCurveTo(146, 105, startX, startY)
            );
            p.setStroke(redEdge);
            p.setFill(redEdge);
            root.getChildren().add(p);
        }
        // міні полоски над серцем
        {
            Path p1 = new Path();
            p1.getElements().addAll(
                    new MoveTo(160, 102),
                    new QuadCurveTo(156, 102, 148, 81),
                    new QuadCurveTo(160, 60, 197, 55),
                    new QuadCurveTo(142, 80, 160, 102)
            );
            p1.setStroke(red);
            p1.setFill(red);

            Path p2 = new Path();
            p2.getElements().addAll(
                    new MoveTo(148, 81),
                    new LineTo(145, 77),
                    new QuadCurveTo(160, 55, 197, 55),
                    new QuadCurveTo(142, 80, 148, 81)
            );
            p2.setStroke(lightRedLine);
            p2.setFill(lightRedLine);
            root.getChildren().addAll(p2, p1);
        }
        // блік в серці
        {
            Path p = new Path();
            p.getElements().addAll(
                    new MoveTo(54, 120),
                    new QuadCurveTo(64, 67, 120, 77),
                    new QuadCurveTo(74, 77, 54, 120)
            );
            p.setStroke(lightPink);
            p.setFill(lightPink);
            root.getChildren().add(p);
        }

        // нижнє поранення
        {
            Path p = new Path();
            p.getElements().addAll(
                    new MoveTo(211,88),
                    new QuadCurveTo(219, 89, 226,97),
                    new QuadCurveTo(232, 106, 231,113),
                    new QuadCurveTo(224, 98, 211, 88)
            );
            p.setStroke(Color.rgb(157, 2, 10));
            p.setFill(Color.rgb(157, 2, 10));
            root.getChildren().add(p);
        }

        // задня частина стріли (піря)
        {
            Path p1 = new Path();
            p1.getElements().addAll(
                    new MoveTo(251, 71),
                    new CubicCurveTo(260, 70, 242, 37, 251, 35),
                    new LineTo(295, 1),
                    new CubicCurveTo(292, 8, 303, 23, 300, 34),
                    new LineTo(251, 71)
            );
            LinearGradient color = new LinearGradient(293,8, 268,56,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(160,116,55)), new Stop(1, Color.rgb(254,204,93)));
            p1.setStroke(color);
            p1.setFill(color);

            Path p2 = new Path();
            p2.getElements().addAll(
                    new MoveTo(254, 78),
                    new CubicCurveTo(259, 74, 284, 90, 290, 85),
                    new LineTo(334, 52),
                    new CubicCurveTo(330, 55, 306, 39, 303, 41),
                    new LineTo(254, 78)
            );
            color = new LinearGradient(305,40, 290,73,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(160,116,55)), new Stop(1, Color.rgb(254,204,93)));
            p2.setStroke(color);
            p2.setFill(color);

            Path p3 = new Path();
            p3.getElements().addAll(
                    new MoveTo(225, 104),
                    new LineTo(311, 34),
                    new QuadCurveTo(308, 30, 306, 28),
                    new LineTo(219, 97),
                    new LineTo(225, 104)
            );
            color = new LinearGradient(244,76, 251,84,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(255, 211, 143)), new Stop(1, Color.rgb(160, 105,25)));
            p3.setStroke(color);
            p3.setFill(color);

            root.getChildren().addAll(p1, p2, p3);
        }

        // слід поранення
        {
            Path p = new Path();
            p.getElements().addAll(
                    new MoveTo(231,113),
                    new QuadCurveTo(224, 98, 211, 88)
            );
            p.setStroke(Color.rgb(255,107,94));
            root.getChildren().add(p);
        }

        // середина стріли
        {
            Path p = new Path();
            p.getElements().addAll(
                    new MoveTo(225, 104),
                    new LineTo(160, 158),
                    new LineTo(152, 153),
                    new LineTo(219, 97),
                    new LineTo(225, 104)
            );
            LinearGradient color = new LinearGradient(225,104, 208,109,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(240,95,66)), new Stop(1, Color.rgb(227, 53,42)));
            p.setStroke(color);
            p.setFill(color);

            root.getChildren().addAll(p);
        }

        // нижнє поранення
        {
            Path p = new Path();
            p.getElements().addAll(
                    new MoveTo(92,213),
                    new LineTo(101,223),
                    new LineTo(89,216),
                    new LineTo(92,213)
            );
            p.setStroke(Color.rgb(157, 2, 10));
            p.setFill(Color.rgb(157, 2, 10));
            root.getChildren().add(p);
        }

        // кінець стріли
        {
            // кінець палки
            Path palka = new Path();
            palka.getElements().addAll(
                    new MoveTo(92, 214),
                    new LineTo(60, 237),
                    new LineTo(55, 230),
                    new LineTo(85, 207),
                    new LineTo(92, 214)
            );
            LinearGradient color = new LinearGradient(62, 224, 65, 227,
                    false, CycleMethod.REFLECT,
                    new Stop(0, Color.rgb(191, 145, 67)), new Stop(1, Color.rgb(243, 194, 99)));
            palka.setStroke(color);
            palka.setFill(color);

            // коло
            Circle circle = new Circle(53, 238, 10);
            RadialGradient circleColor = new RadialGradient(0,
                    .1, 48, 233, 12, false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(255, 234, 160)), new Stop(1, Color.rgb(162, 92, 7)));
            circle.setFill(circleColor);

            // серцевина стріли
            Path endInside = new Path();
            endInside.getElements().addAll(
                    new MoveTo(47, 235),
                    new QuadCurveTo(27, 218, 13, 271),
                    new QuadCurveTo(66, 266, 54, 245),
                    new LineTo(47, 235)
            );
            color = new LinearGradient(27, 242, 40, 256,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(198, 133, 53)), new Stop(1, Color.rgb(255, 221, 137)));
            endInside.setStroke(color);
            endInside.setFill(color);

            // верхня зовнішня сторона стріли
            Path endOutside1 = new Path();
            endOutside1.getElements().addAll(
                    new MoveTo(53, 236),
                    new QuadCurveTo(20, 200, 2, 280),
                    new LineTo(53, 236)
            );
            color = new LinearGradient(20, 230, 27, 238,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(190, 149, 59)), new Stop(1, Color.rgb(244,207,136)));
            endOutside1.setStroke(color);
            endOutside1.setFill(color);

            // нижня зовнішня сторона стріли
            Path endOutside2 = new Path();
            endOutside2.getElements().addAll(
                    new MoveTo(2,280),
                    new QuadCurveTo(90, 274,53, 236),
                    new LineTo(2,280)
            );
            color = new LinearGradient(36,251,48,270,
                    false, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.rgb(168,91,9)), new Stop(1, Color.rgb(255,221,137)));
            endOutside2.setStroke(color);
            endOutside2.setFill(color);

            root.getChildren().addAll(endOutside1, endOutside2, endInside, circle, palka  );
        }

        // лента
        {
            Path p = new Path();
            p.getElements().addAll(
                    new MoveTo(0, 142),
                    new CubicCurveTo(85, 202, 197, 72, 287, 142),
                    new LineTo(287, 192),
                    new CubicCurveTo(197, 132,85, 252,  0, 192),
                    new LineTo(0, 192)
            );
            p.setStroke(colorInsideTape);
            p.setFill(colorInsideTape);
            root.getChildren().add(p);
        }

        //Animation
        int cycleCount = 5;
        int time = 2000;

        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(2000), root);
            translateTransition.setFromX(50);
            translateTransition.setToX(600);
            translateTransition.setToY(550);
            translateTransition.setCycleCount(cycleCount);
            translateTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
            rotateTransition.setByAngle(360f);
            rotateTransition.setCycleCount(cycleCount);
            rotateTransition.setAutoReverse(true);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
            scaleTransition.setToX(-1);
            scaleTransition.setToY(-1);
            scaleTransition.setAutoReverse(true);


        ParallelTransition parallelTransition = new ParallelTransition();
            parallelTransition.getChildren().addAll(
                rotateTransition,
                scaleTransition
                //translateTransition
                );
            parallelTransition.setCycleCount(Timeline.INDEFINITE);
            parallelTransition.play();

        //End
        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
