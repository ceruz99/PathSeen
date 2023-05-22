package com.example.pathseen.data

import com.example.pathseen.model.User
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserRepository {
    private var  auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    suspend fun signUpUser(email: String, password: String): ResourceRemote<String?> {
        return try {
            val result= auth.createUserWithEmailAndPassword(email,password).await()
            ResourceRemote.Success(data=result?.user?.uid)
        } catch (e: FirebaseAuthException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch(e:FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

    suspend fun signInUser(email: String, password: String): ResourceRemote<String?> {
        return try {
            val result= auth.signInWithEmailAndPassword(email,password).await()
            ResourceRemote.Success(data=result?.user?.uid)
        } catch (e: FirebaseAuthException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch(e:FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun createUser(user: User): ResourceRemote<String?> {
        return try {
            user.uid?.let { db.collection("users").document(it).set(user).await() }
            ResourceRemote.Success(data=user.uid)
        } catch (e: FirebaseAuthException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch(e:FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}