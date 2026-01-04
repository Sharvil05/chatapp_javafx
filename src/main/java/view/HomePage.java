package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage {

    Stage mychatapp_Ai_primaryStage;
    String mychatapp_Ai_userName;
    Scene mychatapp_Ai_homePageScene, mychatapp_Ai_searchPageScene, mychatapp_Ai_notesPageScene;

    public void setScene(Scene mychatapp_Ai_homePageScene) {
        this.mychatapp_Ai_homePageScene = mychatapp_Ai_homePageScene;
    }

    public void setStage(Stage mychatapp_Ai_stage) {
        this.mychatapp_Ai_primaryStage = mychatapp_Ai_stage;
    }

    public void setUserName(String mychatapp_Ai_userName) {
        this.mychatapp_Ai_userName = mychatapp_Ai_userName;
    }

    public Parent getView(Runnable mychatapp_Ai_logout) {

        Circle mychatapp_Ai_search_circle = new Circle(70, Color.WHITE);
        Circle mychatapp_Ai_notes_circle = new Circle(70, Color.WHITE);

        Text mychatapp_Ai_label_search = new Text("Search");
        mychatapp_Ai_label_search.setFill(Color.BLACK);
        mychatapp_Ai_label_search.setFont(Font.font(20));

        Text mychatapp_Ai_label_note = new Text("Notes");
        mychatapp_Ai_label_note.setFill(Color.BLACK);
        mychatapp_Ai_label_note.setFont(Font.font(20));

        StackPane mychatapp_Ai_stack_search_circle =
                new StackPane(mychatapp_Ai_search_circle, mychatapp_Ai_label_search);

        mychatapp_Ai_stack_search_circle.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                openSearchPage();
            }
        });

        StackPane mychatapp_Ai_stack_notes_circle =
                new StackPane(mychatapp_Ai_notes_circle, mychatapp_Ai_label_note);

        mychatapp_Ai_stack_notes_circle.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                openNotesPage();
            }
        });

        VBox mychatapp_Ai_centerBox =
                new VBox(60, mychatapp_Ai_stack_search_circle, mychatapp_Ai_stack_notes_circle);
        mychatapp_Ai_centerBox.setAlignment(Pos.CENTER);

        Label mychatapp_Ai_title = new Label("Chat App");
        mychatapp_Ai_title.setStyle("-fx-text-fill:white;");

        Button mychatapp_Ai_backButton = new Button("Back");
        mychatapp_Ai_backButton.setStyle(
                "-fx-background-color:rgb(25,73,109);-fx-text-fill:white;"
                        + "-fx-background-radius:20;-fx-font-size:15px;");

        mychatapp_Ai_backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mychatapp_Ai_logout.run();
            }
        });

        HBox mychatapp_Ai_titleBar =
                new HBox(50, mychatapp_Ai_backButton, mychatapp_Ai_title);
        mychatapp_Ai_titleBar.setPadding(new Insets(10));
        mychatapp_Ai_titleBar.setAlignment(Pos.CENTER_LEFT);
        mychatapp_Ai_titleBar.setStyle("-fx-background-color:rgb(38,38,41);");

        VBox mychatapp_Ai_root =
                new VBox(120, mychatapp_Ai_titleBar, mychatapp_Ai_centerBox);
        mychatapp_Ai_root.setStyle(
                "-fx-background-color:black;-fx-alignment:top center;"
                        + "-fx-font-size:20px;-fx-font-family:'Segoe UI';");

        Rectangle clip = new Rectangle(300, 650);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        mychatapp_Ai_root.setClip(clip);

        return mychatapp_Ai_root;
    }

    public void openSearchPage() {
        SearchPage mychatapp_Ai_searchPage = new SearchPage();
        mychatapp_Ai_searchPage.setMychatapp_Ai_userName(mychatapp_Ai_userName);
        mychatapp_Ai_searchPage.setMychatapp_Ai_stage(mychatapp_Ai_primaryStage);

        mychatapp_Ai_searchPageScene =
                new Scene(mychatapp_Ai_searchPage.getView(this::backToHomePage), 300, 650);
        mychatapp_Ai_searchPageScene.setFill(Color.TRANSPARENT);
        mychatapp_Ai_searchPage.setScene(mychatapp_Ai_searchPageScene);
        mychatapp_Ai_primaryStage.setScene(mychatapp_Ai_searchPageScene);
    }

    public void openNotesPage() {
        NotesPage mychatapp_Ai_notesPage = new NotesPage();
        mychatapp_Ai_notesPage.setMychatapp_Ai_userName(mychatapp_Ai_userName);

        mychatapp_Ai_notesPageScene =
                new Scene(mychatapp_Ai_notesPage.getView(this::backToHomePage), 300, 650);
        mychatapp_Ai_notesPageScene.setFill(Color.TRANSPARENT);
        mychatapp_Ai_notesPage.setScene(mychatapp_Ai_notesPageScene);
        mychatapp_Ai_primaryStage.setScene(mychatapp_Ai_notesPageScene);
    }

    public void backToHomePage() {
        mychatapp_Ai_primaryStage.setScene(mychatapp_Ai_homePageScene);
    }
}
