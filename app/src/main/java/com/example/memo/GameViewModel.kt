package com.example.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private val cards = mutableListOf<ImageResources>().apply {
        add(ImageResources.MEDIATION)
        add(ImageResources.COOPERATE)
        add(ImageResources.COOPERATE)
        add(ImageResources.COOPERATE)
        add(ImageResources.COOPERATE)
        add(ImageResources.RESPONSIBILITY)
        add(ImageResources.RESPONSIBILITY)
        add(ImageResources.VOLUNTARINESS)
        add(ImageResources.VOLUNTARINESS)
        add(ImageResources.SHUTTLE)
        add(ImageResources.SHUTTLE)
        add(ImageResources.EMOTIONS)
        add(ImageResources.EMOTIONS)
        add(ImageResources.INTERESTS)
        add(ImageResources.INTERESTS)
        add(ImageResources.RECONCILIATION)
        add(ImageResources.RECONCILIATION)
        add(ImageResources.CONFLICT)
        add(ImageResources.CONFLICT)
        add(ImageResources.CAUCUS)
        add(ImageResources.CAUCUS)
        add(ImageResources.NEUTRALITY)
        add(ImageResources.NEUTRALITY)
        add(ImageResources.CONFIDENTIALITY)
        add(ImageResources.CONFIDENTIALITY)
    }
    private val _imagesLD = MutableLiveData<List<ImageResources>>()
    val imagesLD: LiveData<List<ImageResources>>
        get() = _imagesLD


    fun generateLevel(){
        _imagesLD.value = cards.shuffled()
    }
}