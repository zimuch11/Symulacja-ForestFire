package com.example.symulacja_fire;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;

public class Main extends Application {

    private int heightCanvas = 600;
    private int widthCanvas = 600;
    private int refreshTime = 200;
    private Simulation simulation;
    private BoardView boardView;
    private StatsView statsView;

    @Override
    public void start(Stage stage) {

        simulation = new Simulation(100, 100, 0.6, 5, 0,30);

        simulation.initialize();

        Canvas canvas = new Canvas(widthCanvas, heightCanvas);

        boardView = new BoardView(simulation, simulation.getBoard(), canvas);

        statsView = new StatsView();

        boardView.draw();

        HBox root = new HBox(canvas,statsView.getContainer());

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(refreshTime),
                        e -> {
                            if (simulation.isRunning()) {
                                simulation.step();
                                boardView.draw();
                                statsView.update(simulation);
                            }
                            else {
                                ((Timeline) e.getSource()).stop();
                                System.out.println("Symulacja zakończona.");
                            }
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}