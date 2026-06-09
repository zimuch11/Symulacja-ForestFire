package com.example.symulacja_fire;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StatsView {

    private final VBox container;
    private final Label burntLabel;
    private final Label burningLabel;

    public StatsView() {
        burntLabel = new Label("Spalony las: 0.0%");
        burntLabel.setFont(new Font("Arial", 16));
        burntLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");

        burningLabel = new Label("Palący się las: 0.0%");
        burningLabel.setFont(new Font("Arial", 16));
        burningLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");

        container = new VBox(30, burntLabel, burningLabel);
        container.setStyle("-fx-padding: 10; -fx-alignment: center;");

        this.container.setPrefWidth(250);

        this.container.setStyle(
                "-fx-padding: 20; " +
                        "-fx-background-color: #f5f5f5; " +
                        "-fx-alignment: center; " +
                        "-fx-border-color: transparent transparent transparent #333333; " +
                        "-fx-border-width: 0px 0px 0px 2px;"
        );
    }

    public VBox getContainer() {
        return container;
    }

    public void update(Simulation simulation) {
        double burntPercent = simulation.getBurntPercentage();
        double burningPercent = simulation.getBurningPercentage();

        burntLabel.setText(String.format("Spalony las: %.1f%%", burntPercent));
        burningLabel.setText(String.format("Palący się las: %.1f%%", burningPercent));
    }
}