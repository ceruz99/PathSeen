package com.example.pathseen.data

import com.example.pathseen.model.GameFS
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class GamesRepository {
    private var db = Firebase.firestore
    private var auth : FirebaseAuth = Firebase.auth

    suspend fun saveGame(gameFS: GameFS): ResourceRemote<String?> {
        return try {
            val uid = auth.currentUser?.uid
            val path=uid?.let { db.collection("users").document(it).collection("games")}
            val documentBook=path?.document()
            gameFS.id = documentBook?.id
            gameFS.id?.let { path?.document(it)?.set(gameFS)?.await() }
            ResourceRemote.Success(data = gameFS.id)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch(e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun loadGames(): ResourceRemote<QuerySnapshot?> {
        return try {
            val docRef = auth.uid?.let { db.collection("users").document(it).collection("games")}
            val result=docRef?.get()?.await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseAuthException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch(e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}