package view;

import controller.UserController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPage extends Application {

    private Stage mychatapp_Ai_primaryStage;
    private Scene mychatapp_Ai_loginScene, mychatapp_Ai_homeScene;
    private UserController mychatapp_Ai_userController = new UserController();

    public void getLoginPage(Stage mychatapp_Ai_primaryStage) {
        this.mychatapp_Ai_primaryStage = mychatapp_Ai_primaryStage;
        initLoginScene();
    }

    private void initLoginScene() {

        ImageView mychatapp_Ai_logo = new ImageView("chatimg.jpeg");
        mychatapp_Ai_logo.setFitWidth(120);
        mychatapp_Ai_logo.setPreserveRatio(true);

        Label mychatapp_Ai_title = new Label("Login");
        mychatapp_Ai_title.setStyle(
                "-fx-font-size:25;-fx-font-weight:bold;-fx-pref-width:300;"
                        + "-fx-alignment:CENTER;-fx-text-fill:#FFFFFF");

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

        Label mychatapp_Ai_output = new Label();
        mychatapp_Ai_output.setStyle("-fx-text-fill:white;");

        Button mychatapp_Ai_loginButton = new Button("Login");
        mychatapp_Ai_loginButton.setStyle(
                "-fx-pref-width:70;-fx-min-height:30;-fx-background-radius:15;"
                        + "-fx-background-color:#2196F3;-fx-text-fill:#FFFFFF");

        Label mychatapp_Ai_signupButton = new Label("Signup");
        mychatapp_Ai_signupButton.setStyle("-fx-text-fill:white;");

        mychatapp_Ai_loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!mychatapp_Ai_userTextField.getText().isEmpty()
                        && !mychatapp_Ai_passField.getText().isEmpty()) {

                    if (mychatapp_Ai_userController.authenticateUser(
                            mychatapp_Ai_userTextField.getText(),
                            mychatapp_Ai_passField.getText())) {

                        initUserScene(mychatapp_Ai_userTextField.getText());
                        mychatapp_Ai_primaryStage.setScene(mychatapp_Ai_homeScene);
                        mychatapp_Ai_userTextField.clear();
                        mychatapp_Ai_passField.clear();
                    } else {
                        mychatapp_Ai_output.setText("Invalid Username or Password");
                    }
                } else {
                    mychatapp_Ai_output.setText("Please Enter Username and Password");
                }
            }
        });

        mychatapp_Ai_signupButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showSignupScene();
                mychatapp_Ai_userTextField.clear();
                mychatapp_Ai_passField.clear();
            }
        });

        mychatapp_Ai_userLabel.setStyle("-fx-text-fill:white;");
        mychatapp_Ai_passLabel.setStyle("-fx-text-fill:white;");

        VBox mychatapp_Ai_loginBox = new VBox(
                20,
                mychatapp_Ai_header,
                mychatapp_Ai_userLabel,
                mychatapp_Ai_userTextField,
                mychatapp_Ai_passLabel,
                mychatapp_Ai_passField,
                mychatapp_Ai_loginButton,
                mychatapp_Ai_signupButton,
                mychatapp_Ai_output
        );

        mychatapp_Ai_loginBox.setStyle(
                "-fx-alignment:CENTER;-fx-padding:30;-fx-background-color:rgba(0,0,0);");

        Rectangle clip = new Rectangle(300, 650);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        mychatapp_Ai_loginBox.setClip(clip);

        mychatapp_Ai_loginScene = new Scene(mychatapp_Ai_loginBox, 300, 650);
        mychatapp_Ai_loginScene.setFill(Color.TRANSPARENT);
    }

    private void initUserScene(String mychatapp_Ai_userName) {
        HomePage mychatapp_Ai_homePage = new HomePage();
        mychatapp_Ai_homePage.setStage(mychatapp_Ai_primaryStage);
        mychatapp_Ai_homePage.setUserName(mychatapp_Ai_userName);

        mychatapp_Ai_homeScene =
                new Scene(mychatapp_Ai_homePage.getView(this::handleLogout), 300, 650);

        mychatapp_Ai_homeScene.setFill(Color.TRANSPARENT);
        mychatapp_Ai_homePage.setScene(mychatapp_Ai_homeScene);
    }

    public Scene getLoginScene() {
        return mychatapp_Ai_loginScene;
    }

    private void showSignupScene() {
        SignupPage signupPage = new SignupPage();
        Scene signupScene =
                new Scene(signupPage.createSignupScene(this::handleBack), 300, 650);
        signupScene.setFill(Color.TRANSPARENT);
        mychatapp_Ai_primaryStage.setScene(signupScene);
    }

    private void handleLogout() {
        mychatapp_Ai_primaryStage.setScene(mychatapp_Ai_loginScene);
    }

    private void handleBack() {
        mychatapp_Ai_primaryStage.setScene(mychatapp_Ai_loginScene);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Class.forName("configuration.FirebaseInitialization");
        getLoginPage(stage);
        stage.setScene(getLoginScene());
        stage.setTitle("ChatApp");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
