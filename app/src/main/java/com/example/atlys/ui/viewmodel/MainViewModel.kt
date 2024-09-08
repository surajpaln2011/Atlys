package com.example.atlys.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.atlys.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    var selectedMovie : Movie? = null
}