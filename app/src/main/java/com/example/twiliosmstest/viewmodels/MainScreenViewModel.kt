package com.example.twiliosmstest.viewmodels

import androidx.lifecycle.ViewModel
import com.example.twiliosmstest.database.MessagesDatabase
import com.example.twiliosmstest.database.MessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MessagesRepository
) : ViewModel() {

}