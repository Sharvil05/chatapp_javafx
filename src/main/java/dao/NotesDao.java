// package dao;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.concurrent.ExecutionException;
// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.*;

// import configuration.FirebaseInitialization;
// import model.Note;

// public class NotesDao {

//     public static Firestore mychatapp_Ai_db;

//     static {
//         mychatapp_Ai_db = FirebaseInitialization.getFireStore();
//     }

//     public void addData(String mychatapp_Ai_collection,
//                         String mychatapp_Ai_document,
//                         Note mychatapp_Ai_data)
//             throws InterruptedException, ExecutionException {

//         DocumentReference mychatapp_Ai_docRef =
//                 mychatapp_Ai_db.collection(mychatapp_Ai_collection)
//                         .document(mychatapp_Ai_document);

//         ApiFuture<WriteResult> mychatapp_Ai_result =
//                 mychatapp_Ai_docRef.set(mychatapp_Ai_data);

//         mychatapp_Ai_result.get();
//     }

//     public List<Note> getDataList(String mychatapp_Ai_collection,
//                                   String mychatapp_Ai_userName)
//             throws ExecutionException, InterruptedException {

//         Query query = mychatapp_Ai_db.collection(mychatapp_Ai_collection)
//                 .whereEqualTo("userName", mychatapp_Ai_userName);

//         ApiFuture<QuerySnapshot> mychatapp_Ai_future = query.get();
//         QuerySnapshot mychatapp_Ai_snapshot = mychatapp_Ai_future.get();

//         List<Note> mychatapp_Ai_notes = new ArrayList<>();
//         for (QueryDocumentSnapshot doc : mychatapp_Ai_snapshot.getDocuments()) {
//             mychatapp_Ai_notes.add(doc.toObject(Note.class));
//         }
//         return mychatapp_Ai_notes;
//     }
// }

package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.google.firebase.cloud.FirestoreClient;
import model.Note;

public class NotesDao {

    private static Firestore mychatapp_Ai_db = FirestoreClient.getFirestore();

    public void addData(String mychatapp_Ai_collection,
                        String mychatapp_Ai_document,
                        Note mychatapp_Ai_data)
            throws InterruptedException, ExecutionException {

        DocumentReference mychatapp_Ai_docRef =
                mychatapp_Ai_db.collection(mychatapp_Ai_collection)
                        .document(mychatapp_Ai_document);

        ApiFuture<WriteResult> mychatapp_Ai_result =
                mychatapp_Ai_docRef.set(mychatapp_Ai_data);

        mychatapp_Ai_result.get();
    }

    public List<Note> getDataList(String mychatapp_Ai_collection,
                                  String mychatapp_Ai_userName)
            throws ExecutionException, InterruptedException {

        Query query = mychatapp_Ai_db.collection(mychatapp_Ai_collection)
                .whereEqualTo("userName", mychatapp_Ai_userName);

        ApiFuture<QuerySnapshot> mychatapp_Ai_future = query.get();
        QuerySnapshot mychatapp_Ai_snapshot = mychatapp_Ai_future.get();

        List<Note> mychatapp_Ai_notes = new ArrayList<>();
        for (QueryDocumentSnapshot doc : mychatapp_Ai_snapshot.getDocuments()) {
            mychatapp_Ai_notes.add(doc.toObject(Note.class));
        }
        return mychatapp_Ai_notes;
    }
}
