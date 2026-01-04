package view;

import java.util.List;

import controller.FormatController;
import controller.NotesController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Note;

public class NotesPage {

    private VBox mychatapp_Ai_chatBox = new VBox(15);
    ScrollPane mychatapp_Ai_scrollPane = null;
    Scene mychatapp_Ai_notesScene;
    String mychatapp_Ai_userName;

    FormatController mychatapp_Ai_formatController = new FormatController();
    NotesController mychatapp_Ai_notesController = new NotesController();

    public void setScene(Scene mychatapp_Ai_notesScene) {
        this.mychatapp_Ai_notesScene = mychatapp_Ai_notesScene;
    }

    public void setMychatapp_Ai_userName(String mychatapp_Ai_userName) {
        this.mychatapp_Ai_userName = mychatapp_Ai_userName;
    }

    public Parent getView(Runnable mychatapp_Ai_back) {

        mychatapp_Ai_scrollPane = new ScrollPane(mychatapp_Ai_chatBox);
        mychatapp_Ai_scrollPane.setFitToWidth(true);
        mychatapp_Ai_scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mychatapp_Ai_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mychatapp_Ai_chatBox.setPadding(new Insets(10));
        mychatapp_Ai_chatBox.setBackground(
                new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        List<Note> mychatapp_Ai_notes =
                mychatapp_Ai_notesController.getAllNotesForUser(mychatapp_Ai_userName);

        mychatapp_Ai_notes.forEach(note -> {
            mychatapp_Ai_chatBox.getChildren()
                    .add(createCard(note.getQuestion(), note.getAnswer()));
        });

        Label mychatapp_Ai_title = new Label("Notes");
        mychatapp_Ai_title.setStyle("-fx-text-fill:white;");

        Button mychatapp_Ai_backButton = new Button("Back");
        mychatapp_Ai_backButton.setStyle(
                "-fx-background-color:rgb(25,73,109);-fx-text-fill:white;"
                        + "-fx-background-radius:20;-fx-font-size:15px;");

        mychatapp_Ai_backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mychatapp_Ai_back.run();
            }
        });

        HBox mychatapp_Ai_titleBar =
                new HBox(50, mychatapp_Ai_backButton, mychatapp_Ai_title);
        mychatapp_Ai_titleBar.setPadding(new Insets(10));
        mychatapp_Ai_titleBar.setAlignment(Pos.CENTER_LEFT);
        mychatapp_Ai_titleBar.setStyle("-fx-background-color:rgb(38,38,41);");

        VBox mychatapp_Ai_root =
                new VBox(10, mychatapp_Ai_titleBar, mychatapp_Ai_scrollPane);
        mychatapp_Ai_root.setStyle("-fx-background-color:black;");

        VBox.setVgrow(mychatapp_Ai_scrollPane, Priority.ALWAYS);

        Rectangle clip = new Rectangle(300, 650);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        mychatapp_Ai_root.setClip(clip);

        return mychatapp_Ai_root;
    }

    private Pane createCard(String mychatapp_Ai_titleText, String mychatapp_Ai_contentText) {

        VBox mychatapp_Ai_card = new VBox();
        mychatapp_Ai_card.setPadding(new Insets(10));
        mychatapp_Ai_card.setSpacing(10);
        mychatapp_Ai_card.setStyle(
                "-fx-background-color:rgba(34,17,129,0.42);-fx-background-radius:8;");

        Label mychatapp_Ai_titleLabel = new Label(mychatapp_Ai_titleText);
        mychatapp_Ai_titleLabel.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");

        Label mychatapp_Ai_contentLabel = new Label(mychatapp_Ai_contentText);
        mychatapp_Ai_contentLabel.setWrapText(true);
        mychatapp_Ai_contentLabel.setVisible(false);
        mychatapp_Ai_contentLabel.setManaged(false);

        mychatapp_Ai_card.getChildren().addAll(
                mychatapp_Ai_titleLabel,
                mychatapp_Ai_contentLabel
        );

        mychatapp_Ai_card.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (mychatapp_Ai_contentLabel.isVisible()) {
                collapse(mychatapp_Ai_contentLabel);
            } else {
                expand(mychatapp_Ai_contentLabel);
            }
        });

        return mychatapp_Ai_card;
    }

    private void expand(Label mychatapp_Ai_content) {
        mychatapp_Ai_content.setVisible(true);
        mychatapp_Ai_content.setManaged(true);
        mychatapp_Ai_content.setOpacity(0);

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(mychatapp_Ai_content.opacityProperty(), 0)),
                new KeyFrame(Duration.millis(250),
                        new KeyValue(mychatapp_Ai_content.opacityProperty(), 1))
        );
        fadeIn.play();
    }

    private void collapse(Label mychatapp_Ai_content) {
        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(mychatapp_Ai_content.opacityProperty(), 1)),
                new KeyFrame(Duration.millis(250),
                        new KeyValue(mychatapp_Ai_content.opacityProperty(), 0))
        );

        fadeOut.setOnFinished(e -> {
            mychatapp_Ai_content.setVisible(false);
            mychatapp_Ai_content.setManaged(false);
        });
        fadeOut.play();
    }
}
