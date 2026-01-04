package view;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class SignupPage {

    public UserController mychatapp_Ai_userController = new UserController();

    public Parent createSignupScene(Runnable mychatapp_Ai_backHandler) {

        ImageView mychatapp_Ai_logo = new ImageView("chatimg.jpeg");
        mychatapp_Ai_logo.setFitWidth(120);
        mychatapp_Ai_logo.setPreserveRatio(true);

        Label mychatapp_Ai_title = new Label("Sign up");
        mychatapp_Ai_title.setStyle(
                "-fx-font-size:25;-fx-font-weight:bold;-fx-text-fill:#FFFFFF");

        VBox mychatapp_Ai_header = new VBox(20, mychatapp_Ai_logo, mychatapp_Ai_title);
        mychatapp_Ai_header.setAlignment(Pos.CENTER);

        Label mychatapp_Ai_userLabel = new Label("Username:");
        TextField mychatapp_Ai_userTextField = new TextField();
        mychatapp_Ai_userTextField.setPromptText("Enter Username");
        mychatapp_Ai_userTextField.setStyle(
                "-fx-max-width:270;-fx-min-height:30;-fx-background-radius:15;");
        mychatapp_Ai_userTextField.setFocusTraversable(false);

        Label mychatapp_Ai_passLabel = new Label("Password:");
        PasswordField mychatapp_Ai_passField = new PasswordField();
        mychatapp_Ai_passField.setPromptText("Enter Password");
        mychatapp_Ai_passField.setStyle(
                "-fx-pref-width:270;-fx-min-height:30;-fx-background-radius:15;");
        mychatapp_Ai_passField.setFocusTraversable(false);

        Button mychatapp_Ai_signupButton = new Button("Signup");
        mychatapp_Ai_signupButton.setStyle(
                "-fx-pref-width:70;-fx-min-height:30;-fx-background-radius:15;"
                        + "-fx-background-color:#2196F3;-fx-text-fill:#FFFFFF");

        Label mychatapp_Ai_loginButton = new Label("Login");
        mychatapp_Ai_loginButton.setStyle("-fx-text-fill:white;");

        Label mychatapp_Ai_output = new Label();
        mychatapp_Ai_output.setStyle("-fx-text-fill:white;");

        mychatapp_Ai_userLabel.setStyle("-fx-text-fill:white;");
        mychatapp_Ai_passLabel.setStyle("-fx-text-fill:white;");

        mychatapp_Ai_signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!mychatapp_Ai_userTextField.getText().isEmpty()
                        && !mychatapp_Ai_passField.getText().isEmpty()) {

                    if (mychatapp_Ai_userController.handleSignup(
                            mychatapp_Ai_userTextField.getText(),
                            mychatapp_Ai_passField.getText())) {
                        mychatapp_Ai_output.setText("User Registered Successfully");
                    } else {
                        mychatapp_Ai_output.setText("User not Registered");
                    }
                } else {
                    mychatapp_Ai_output.setText("Please Enter Username and Password");
                }
            }
        });

        mychatapp_Ai_loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mychatapp_Ai_backHandler.run();
            }
        });

        VBox mychatapp_Ai_box = new VBox(
                20,
                mychatapp_Ai_header,
                mychatapp_Ai_userLabel,
                mychatapp_Ai_userTextField,
                mychatapp_Ai_passLabel,
                mychatapp_Ai_passField,
                mychatapp_Ai_signupButton,
                mychatapp_Ai_loginButton,
                mychatapp_Ai_output
        );

        mychatapp_Ai_box.setStyle(
                "-fx-alignment:CENTER;-fx-padding:30;-fx-background-color:rgba(0,0,0);");

        Rectangle clip = new Rectangle(300, 650);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        mychatapp_Ai_box.setClip(clip);

        return mychatapp_Ai_box;
    }
}
