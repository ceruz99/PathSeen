package com.example.pathseen.data

import com.example.pathseen.model.Book
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class BooksRepository {
    private var db = Firebase.firestore
    private var auth : FirebaseAuth = Firebase.auth

    suspend fun saveBook(book: Book): ResourceRemote<String?> {
        return try {
            val uid = auth.currentUser?.uid
            val path=uid?.let { db.collection("users").document(it).collection("books")}
            val documentBook=path?.document()
            book.id = documentBook?.id
            book.id?.let { path?.document(it)?.set(book)?.await() }
            ResourceRemote.Success(data = book.id)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch(e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

}