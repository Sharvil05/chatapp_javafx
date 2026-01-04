// package controller;

// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import org.json.JSONObject;
// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.auth.FirebaseAuthException;
// import com.google.firebase.auth.UserRecord;
// import javafx.scene.control.Alert;

// public class UserController {

//     public boolean authenticateUser(
//             String mychatapp_Ai_userName,
//             String mychatapp_Ai_password) {

//         try {
//             String mychatapp_Ai_apiKey =
//                     "AIzaSyCFHXsc1ceq4VrtL9WUYWzft8oTZu8wn4Q";

//             URL mychatapp_Ai_url = new URL(
//                     "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
//                             + mychatapp_Ai_apiKey);

//             HttpURLConnection mychatapp_Ai_conn =
//                     (HttpURLConnection) mychatapp_Ai_url.openConnection();

//             mychatapp_Ai_conn.setRequestMethod("POST");
//             mychatapp_Ai_conn.setRequestProperty(
//                     "Content-Type", "application/json; charset=UTF-8");
//             mychatapp_Ai_conn.setDoOutput(true);

//             JSONObject mychatapp_Ai_jsonRequest = new JSONObject();
//             mychatapp_Ai_jsonRequest.put("email", mychatapp_Ai_userName);
//             mychatapp_Ai_jsonRequest.put("password", mychatapp_Ai_password);
//             mychatapp_Ai_jsonRequest.put("returnSecureToken", true);

//             try (OutputStream mychatapp_Ai_os =
//                          mychatapp_Ai_conn.getOutputStream()) {

//                 byte[] input = mychatapp_Ai_jsonRequest
//                         .toString()
//                         .getBytes(StandardCharsets.UTF_8);

//                 mychatapp_Ai_os.write(input, 0, input.length);
//             }

//             if (mychatapp_Ai_conn.getResponseCode() == 200) {
//                 return true;
//             } else {
//                 showAlert("Error", "Failed to log in user.");
//                 return false;
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//             showAlert("Error", "Failed to log in user: " + e.getMessage());
//             return false;
//         }
//     }

//     public boolean handleSignup(
//             String mychatapp_Ai_userName,
//             String mychatapp_Ai_password) {

//         if (mychatapp_Ai_userName.isEmpty() || mychatapp_Ai_password.isEmpty()) {
//             showAlert("Error", "UserName and password cannot be empty.");
//             return false;
//         }

//         try {
//             UserRecord.CreateRequest request =
//                     new UserRecord.CreateRequest()
//                             .setEmail(mychatapp_Ai_userName)
//                             .setPassword(mychatapp_Ai_password)
//                             .setDisabled(false);

//             UserRecord userRecord =
//                     FirebaseAuth.getInstance().createUser(request);

//             showAlert("Success", "User created successfully.");
//             return true;

//         } catch (FirebaseAuthException e) {
//             e.printStackTrace();
//             showAlert("Error", "Failed to create user: " + e.getMessage());
//             return false;
//         }
//     }

//     private void showAlert(String title, String message) {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }
// }


package controller;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import javafx.scene.control.Alert;

public class UserController {

    public boolean authenticateUser(String userName, String password) {
        try {
            String apiKey = "YOUR_FIREBASE_WEB_API_KEY";

            URL url = new URL(
                    "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                            + apiKey);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("email", userName);
            json.put("password", password);
            json.put("returnSecureToken", true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
            }

            return conn.getResponseCode() == 200;

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
            return false;
        }
    }

    public boolean handleSignup(String userName, String password) {
        try {
            UserRecord.CreateRequest request =
                    new UserRecord.CreateRequest()
                            .setEmail(userName)
                            .setPassword(password);

            FirebaseAuth.getInstance().createUser(request);
            showAlert("Success", "User registered successfully");
            return true;

        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
            return false;
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
