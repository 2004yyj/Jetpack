package com.example.dgsw.firebaseExam

import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDatabase {

    var fireStore : FirebaseFirestore = FirebaseFirestore.getInstance()

    private fun addData(collectionPath: String, person : Person) {
        fireStore.collection(collectionPath).add(person)
        // .add만 사용하면 document path의 이름이 랜덤하게 정해짐
    }

    private fun setData(collectionPath: String, documentPath : String, person : Person) {
        fireStore.collection(collectionPath).document(documentPath).set(person)
        // .document(documentPath).set(value)를 사용하면 document path의 이름을 직접 정할 수 있음
    }

    private fun updateDataKeyValue(collectionPath: String, documentPath : String, fieldPath: String, value: String) {
        val map: Map<String, String> = mapOf(
                fieldPath to value
        )
        fireStore.collection(collectionPath).document(documentPath).update(map)
        //key-value를 이용하여 데이터를 업데이트할 수 있다.
    }

    private fun updateDataFieldValue(collectionPath: String, documentPath : String, fieldPath: String, value: String) {
        fireStore.collection(collectionPath).document(documentPath).update(fieldPath, value)
        // .document(documentPath).update(fieldPath, value)를 사용하면 documentPath 의 fieldPath 값을 업데이트할 수 있음
    }

    private fun updateDataKeyValue(collectionPath: String, documentPath: String, value: Person) {
        val map : Map<String, String> = mapOf(
                "name" to value.name,
                "phoneNum" to value.phoneNum
        )//key-value를 이용하여 데이터를 업데이트할 수 있다.
        fireStore.collection(collectionPath).document(documentPath).update(map)
    }

    private fun updateDataFieldValue(collectionPath: String, documentPath : String, value: Person) {
        fireStore.collection(collectionPath).document(documentPath)
                .update("name", value.name, "phoneNum", value.phoneNum)
        // .document(documentPath).update(fieldPath, value)를 사용하면 documentPath 의 fieldPath 값을 업데이트할 수 있음
    }

    private fun deleteData(collectionPath: String, documentPath : String) {
        fireStore.collection(collectionPath).document(documentPath).delete()
        // .delete()를 사용하면 documentPath 를 지울 수 있음
    }

    private fun readData() {
        fireStore.collection("users").addSnapshotListener { querySnapshot, _ ->
            for (snapshot in querySnapshot!!.documents) {
                val item = snapshot.toObject(Person::class.java)
                println(item.toString())
            }
        }
    }

}