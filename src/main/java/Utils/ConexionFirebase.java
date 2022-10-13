///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Utils;
//
//import com.google.api.core.ApiFuture;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.DocumentReference;
//import com.google.cloud.firestore.Firestore;
//import com.google.cloud.firestore.WriteResult;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.cloud.FirestoreClient;
//import com.google.firebase.database.DatabaseReference;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
///**
// *
// * @author Personal
// */
//public class ConexionFirebase {
//
//    private DatabaseReference dRef;
//
//    public void conectar() throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
//
//        InputStream serviceAccount = new FileInputStream("helppeoplesi-firebase.json");
//        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(credentials)
//                .build();
//        FirebaseApp.initializeApp(options);
//
//        Firestore db = FirestoreClient.getFirestore();
//        
//        DocumentReference docRef = db.collection("users").document("photos");
//// Add document data  with id "alovelace" using a hashmap
//        Map<String, Object> data = new HashMap<>();
//        data.put("IdUser", "Ada");
//        data.put("NameUser", "Lovelace");
//        data.put("urlImage", 1815);
////asynchronously write data
//        ApiFuture<WriteResult> result = docRef.set(data);
//// ...
//// result.get() blocks on response
//        System.out.println("Update time : " + result.get().getUpdateTime());
//
//    }
//}
