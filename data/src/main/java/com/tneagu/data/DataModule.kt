package com.tneagu.data

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.tneagu.auth.login.domain.repositories.AuthRepo
import com.tneagu.data.repository.auth.AuthRepoImpl
import com.tneagu.domain.repositories.NotesRepo
import com.tneagu.data.repository.notes.NotesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthRepo(firebaseAuth: FirebaseAuth): AuthRepo =
        AuthRepoImpl(firebaseAuth = firebaseAuth)

    @Provides
    fun provideNotesRepo(fireStore: FirebaseFirestore, firebaseAuth: FirebaseAuth): NotesRepo =
        NotesRepoImpl(fireStore = fireStore, auth = firebaseAuth)
}