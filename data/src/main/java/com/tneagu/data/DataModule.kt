package com.tneagu.data

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.tneagu.data.repository.auth.AuthRepo
import com.tneagu.data.repository.auth.AuthRepoImpl
import com.tneagu.data.repository.notes.NotesRepo
import com.tneagu.data.repository.notes.NotesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthRepo(firebaseAuth: FirebaseAuth): AuthRepo =
        AuthRepoImpl(firebaseAuth = firebaseAuth)

    @Provides
    fun provideNotesRepo(fireStore: FirebaseFirestore): NotesRepo =
        NotesRepoImpl(fireStore = fireStore)
}