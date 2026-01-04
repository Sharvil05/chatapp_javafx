package view;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;
import org.fxmisc.richtext.InlineCssTextArea;

import controller.AiApiController;
import controller.FormatController;
import controller.NotesController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Note;
import utilities.Snackbar;

public class SearchPage {

    Scene mychatapp_Ai_searchScene;
    Stage mychatapp_Ai_stage;
    private VBox mychatapp_Ai_chatBox = new VBox(15);
    ScrollPane mychatapp_Ai_scrollPane = null;
    String mychatapp_Ai_userName;

    private Parser mychatapp_Ai_markdownParser = Parser.builder().build();
    private TextContentRenderer mychatapp_Ai_renderer =
            TextContentRenderer.builder().build();

    AiApiController mychatapp_Ai_controller = new AiApiController();
    FormatController mychatapp_Ai_formatController = new FormatController();
    NotesController mychatapp_Ai_notesController = new NotesController();

    public void setScene(Scene mychatapp_Ai_searchScene) {
        this.mychatapp_Ai_searchScene = mychatapp_Ai_searchScene;
    }

    public void setMychatapp_Ai_stage(Stage mychatapp_Ai_stage) {
        this.mychatapp_Ai_stage = mychatapp_Ai_stage;
    }

    public void setMychatapp_Ai_userName(String mychatapp_Ai_userName) {
        this.mychatapp_Ai_userName = mychatapp_Ai_userName;
    }

    public Parent getView(Runnable mychatapp_Ai_back) {

        TextField mychatapp_Ai_inputField = new TextField();
        mychatapp_Ai_inputField.setPromptText("Ask something...");
        mychatapp_Ai_inputField.setStyle("-fx-background-radius:20;");
        mychatapp_Ai_inputField.setFont(Font.font("Segoe UI", 14));
        mychatapp_Ai_inputField.setPrefHeight(35);

        Button mychatapp_Ai_sendBtn = new Button("Send");
        mychatapp_Ai_sendBtn.setFont(Font.font("Segoe UI Semibold", 14));
        mychatapp_Ai_sendBtn.setStyle(
                "-fx-background-color:#0078D4;-fx-text-fill:white;-fx-background-radius:20;");
        mychatapp_Ai_sendBtn.setPrefHeight(35);

        mychatapp_Ai_sendBtn.setOnAction(e -> {
            String mychatapp_Ai_question = mychatapp_Ai_inputField.getText();
            if (!mychatapp_Ai_question.isBlank()) {
                addUserBubble(mychatapp_Ai_question);
                String mychatapp_Ai_response =
                        mychatapp_Ai_controller.callGeminiAPI(mychatapp_Ai_question);
                addBotBubble(mychatapp_Ai_question, mychatapp_Ai_response);
                mychatapp_Ai_inputField.clear();
            }
        });

        mychatapp_Ai_scrollPane = new ScrollPane(mychatapp_Ai_chatBox);
        mychatapp_Ai_scrollPane.setFitToWidth(true);
        mychatapp_Ai_scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mychatapp_Ai_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mychatapp_Ai_chatBox.setPadding(new Insets(10));
        VBox.setVgrow(mychatapp_Ai_scrollPane, Priority.ALWAYS);

        Label mychatapp_Ai_title = new Label("Search");
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

        HBox mychatapp_Ai_inputBar =
                new HBox(10, mychatapp_Ai_inputField, mychatapp_Ai_sendBtn);
        mychatapp_Ai_inputBar.setPadding(new Insets(10));

        VBox mychatapp_Ai_root =
                new VBox(10, mychatapp_Ai_titleBar, mychatapp_Ai_scrollPane, mychatapp_Ai_inputBar);
        mychatapp_Ai_root.setStyle("-fx-background-color:black;");

        Rectangle clip = new Rectangle(300, 650);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        mychatapp_Ai_root.setClip(clip);

        return mychatapp_Ai_root;
    }

    private void addUserBubble(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setStyle(
                "-fx-background-color:rgba(237,66,94,0.42);-fx-padding:10;"
                        + "-fx-background-radius:10;-fx-font-size:14px;-fx-text-fill:white");

        HBox container = new HBox(label);
        container.setAlignment(Pos.CENTER_RIGHT);
        mychatapp_Ai_chatBox.getChildren().add(container);
    }

    private void addBotBubble(String mychatapp_Ai_question, String mychatapp_Ai_markdown) {

        String mychatapp_Ai_plainText =
                mychatapp_Ai_renderer.render(
                        mychatapp_Ai_markdownParser.parse(mychatapp_Ai_markdown));

        InlineCssTextArea mychatapp_Ai_area = new InlineCssTextArea();
        mychatapp_Ai_area.replaceText(mychatapp_Ai_plainText);
        mychatapp_Ai_area.setWrapText(true);
        mychatapp_Ai_area.setEditable(false);

        mychatapp_Ai_area.setStyle(
                "-fx-background-color:rgba(34,17,129,0.42);"
                        + "-fx-font-family:'Segoe UI';-fx-font-size:14px;"
                        + "-fx-padding:10;-fx-background-radius:12;");

        mychatapp_Ai_formatController.formatAndDisplayAIResponse(
                mychatapp_Ai_area, mychatapp_Ai_plainText);

        HBox container = new HBox(mychatapp_Ai_area);
        Label mychatapp_Ai_add = new Label("Add to Notes");
        mychatapp_Ai_add.setStyle("-fx-font-size:10px;");

        VBox vb = new VBox(10, container, mychatapp_Ai_add);
        mychatapp_Ai_add.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Note note = new Note();
                note.setQuestion(mychatapp_Ai_question);
                note.setAnswer(mychatapp_Ai_plainText);
                note.setUserName(mychatapp_Ai_userName);
                mychatapp_Ai_notesController.addNote(note);
                Snackbar.show(mychatapp_Ai_stage, "Note Successfully Added");
                vb.getChildren().remove(1);
            }
        });

        mychatapp_Ai_chatBox.getChildren().add(vb);
        Platform.runLater(() -> mychatapp_Ai_scrollPane.setVvalue(1.0));
    }
}
