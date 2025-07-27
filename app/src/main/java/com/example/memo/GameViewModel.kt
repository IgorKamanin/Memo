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

    private lateinit var firstImage: ImageResources

    private val _imagesLD = MutableLiveData<List<ImageResources>>()
    val imagesLD: LiveData<List<ImageResources>>
        get() = _imagesLD

    private val _firstClickDone = MutableLiveData<Boolean>()
    val firstClickDone: LiveData<Boolean>
        get() = _firstClickDone

    private val _guessed = MutableLiveData<Boolean>()
    val guessed: LiveData<Boolean>
        get() = _guessed




    fun generateLevel(){
        _firstClickDone.value = false
        _imagesLD.value = cards.shuffled()
    }

    fun clickImage(imageResources: ImageResources) {
        if (firstClickDone.value == false) {
            firstImage = imageResources
            _firstClickDone.value = true
        } else {
            if (imageResources == firstImage) {
                _guessed.value = true

            } else {
                _guessed.value = false
            }
            _firstClickDone.value = false
        }

    }
}