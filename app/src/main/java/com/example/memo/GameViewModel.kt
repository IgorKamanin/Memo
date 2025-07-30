package com.example.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class GameViewModel : ViewModel() {
    private val cards = mutableListOf<ImageResources>().apply {
        add(ImageResources.MEDIATION)
        add(ImageResources.COOPERATE)
        add(ImageResources.COOPERATE)
        add(ImageResources.TRASPARENCY)
        add(ImageResources.TRASPARENCY)
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

    private val shuffledList = cards.shuffled()
    private var firstClickDone = false
    private lateinit var firstImage: ImageResources

    private val _imagesLD = MutableLiveData<List<ImageResources>>()
    val imagesLD: LiveData<List<ImageResources>>
        get() = _imagesLD

    private val _comparison = MutableLiveData<Boolean>()
    val comparison: LiveData<Boolean>
        get() = _comparison

    private val _guessed = MutableLiveData<Boolean>()
    val guessed: LiveData<Boolean>
        get() = _guessed

    private val _gameFinished = MutableLiveData<Unit>()
    val gameFinished: LiveData<Unit>
        get() = _gameFinished

    fun generateLevel() {
        firstClickDone = false
        _imagesLD.value = shuffledList.toList()
    }

    fun clickImage(imageResource: ImageResources) {
        if (!firstClickDone) {
            firstImage = imageResource
            firstClickDone = true
        } else {
            _comparison.value = true
            viewModelScope.launch {
                delay(1000)
                if (imageResource == firstImage) {
                    _guessed.value = true
                    updateList(imageResource)
                } else {
                    _guessed.value = false
                }
                firstClickDone = false
                _comparison.value = false
            }
        }
    }

    fun updateList(imageResource: ImageResources) {
        val oldList = _imagesLD.value
        val newList = mutableListOf<ImageResources>().apply {
            for (i in 0 until shuffledList.size) {
                add(oldList?.get(i) ?: ImageResources.DONE)
                if (oldList?.get(i) == imageResource) {
                    this[i] = ImageResources.DONE
                }
            }
        }
        var guessCount = 0
        for (i in newList) {
            if (i == ImageResources.DONE) {
                guessCount++
            }
        }
        if (guessCount == 24) {
            _gameFinished.value = Unit
        } else {
            _imagesLD.value = newList
        }
    }
}
