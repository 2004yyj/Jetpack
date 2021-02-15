package com.example.dgsw.firebaseExam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fireStore = FirebaseFirestore.getInstance()

        fireStore.collection("users").addSnapshotListener { querySnapshot, _ ->
            for (snapshot in querySnapshot!!.documents) {
                val item = snapshot.toObject(Person::class.java)
                println(item.toString())
            }
        }
    }
}