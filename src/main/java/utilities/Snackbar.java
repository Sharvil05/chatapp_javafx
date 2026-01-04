package utilities;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Snackbar {

    public static void show(Stage mychatapp_Ai_stage, String mychatapp_Ai_message) {

        Label mychatapp_Ai_label = new Label(mychatapp_Ai_message);
        mychatapp_Ai_label.setStyle(
                "-fx-background-color:#323232;-fx-text-fill:white;"
                        + "-fx-padding:10 20 10 20;-fx-background-radius:20;");
        mychatapp_Ai_label.setOpacity(0);

        Popup mychatapp_Ai_popup = new Popup();
        mychatapp_Ai_popup.getContent().add(mychatapp_Ai_label);

        double x = mychatapp_Ai_stage.getX()
                + (mychatapp_Ai_stage.getWidth() / 2) - 100;
        double y = mychatapp_Ai_stage.getY()
                + mychatapp_Ai_stage.getHeight() - 100;

        mychatapp_Ai_popup.show(mychatapp_Ai_stage, x, y);

        FadeTransition fadeIn =
                new FadeTransition(Duration.millis(300), mychatapp_Ai_label);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        PauseTransition wait = new PauseTransition(Duration.seconds(2));

        FadeTransition fadeOut =
                new FadeTransition(Duration.millis(300), mychatapp_Ai_label);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> mychatapp_Ai_popup.hide());

        SequentialTransition sequence =
                new SequentialTransition(fadeIn, wait, fadeOut);
        sequence.play();
    }
}
