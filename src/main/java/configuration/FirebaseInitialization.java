// // package configuration;

// // import java.io.FileInputStream;
// // import java.io.IOException;
// // import java.util.logging.Level;
// // import java.util.logging.Logger;

// // import com.google.auth.oauth2.GoogleCredentials;
// // import com.google.cloud.firestore.Firestore;
// // import com.google.firebase.FirebaseApp;
// // import com.google.firebase.FirebaseOptions;
// // import com.google.firebase.auth.FirebaseAuth;
// // import com.google.firebase.cloud.FirestoreClient;

// // public class FirebaseInitialization {

// //     private static final Logger LOGGER =
// //             Logger.getLogger(FirebaseInitialization.class.getName());

// //     private static FirebaseApp firebaseApp;

// //     static {
// //         init();
// //     }

// //     public static void init() {
// //         if (firebaseApp == null) {
// //             try {
// //                 FileInputStream serviceAccount =
// //                         new FileInputStream("src/main/resources/ai-chat-app.json");

// //                 FirebaseOptions options = FirebaseOptions.builder()
// //                         .setCredentials(GoogleCredentials.fromStream(serviceAccount))
// //                         .setProjectId("chatapp-javafx")
// //                         .build();

// //                 firebaseApp = FirebaseApp.initializeApp(options);
// //             } catch (IOException e) {
// //                 LOGGER.log(Level.SEVERE, e.getMessage(), e);
// //             }
// //         }
// //     }

// //     public static FirebaseAuth getFirebaseAuth() {
// //         return FirebaseAuth.getInstance(firebaseApp);
// //     }

// //     public static Firestore getFireStore() {
// //         return FirestoreClient.getFirestore(firebaseApp);
// //     }
// // }

// package configuration;

// import java.io.FileInputStream;
// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;

// public class FirebaseInitialization {

//     private static boolean initialized = false;

//     public static void init() {
//         if (initialized) {
//             return;
//         }

//         try {
//             FileInputStream serviceAccount =
//                     new FileInputStream("src/main/resources/ai-chat-app.json");

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .build();

//             FirebaseApp.initializeApp(options);
//             initialized = true;

//             System.out.println("Firebase initialized successfully");

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }

package configuration;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInitialization {

    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;

        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/ai-chat-app.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;

            System.out.println("Firebase initialized successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
