package com.example.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.memo.databinding.FragmentGameBinding

private const val GAME_MODE = "Game_mode"
private const val START_TIME = "start time"

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private lateinit var viewModel: GameViewModel
    private val imagesList = mutableListOf<ImageResources>()
    private var gameMode: String? = null
    private var startTime: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            gameMode = it.getString(GAME_MODE)
            startTime = it.getLong(START_TIME)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        viewModel.generateLevel()
        val imageButtons = getImagesforButtons()
        for (button in imageButtons) {
            button.setImageResource(R.drawable.cardback)
        }
        observeViewModel(imageButtons)
        for (i in 0 until imageButtons.size) {
            imageButtons[i].setOnClickListener {
                imageButtons[i].apply {
                    isEnabled = false
                    setImageResource(getResourceId(imagesList[i]))
                }
                viewModel.clickImage(imagesList[i])
            }
        }
    }

    private fun getImagesforButtons() =
        mutableListOf<ImageView>().apply {
            add(binding.image0)
            add(binding.image1)
            add(binding.image2)
            add(binding.image3)
            add(binding.image4)
            add(binding.image5)
            add(binding.image6)
            add(binding.image7)
            add(binding.image8)
            add(binding.image9)
            add(binding.image10)
            add(binding.image11)
            add(binding.image12)
            add(binding.image13)
            add(binding.image14)
            add(binding.image15)
            add(binding.image16)
            add(binding.image17)
            add(binding.image18)
            add(binding.image19)
            add(binding.image20)
            add(binding.image21)
            add(binding.image22)
            add(binding.image23)
            add(binding.image24)
        }

    private fun getResourceId(s: ImageResources): Int {
        return when (s) {
            ImageResources.MEDIATION -> R.drawable.mediation0
            ImageResources.COOPERATE -> R.drawable.cooperate1
            ImageResources.TRASPARENCY -> R.drawable.trasparency2
            ImageResources.RESPONSIBILITY -> R.drawable.responsibility3
            ImageResources.VOLUNTARINESS -> R.drawable.voluntariness4
            ImageResources.SHUTTLE -> R.drawable.shuttle5
            ImageResources.EMOTIONS -> R.drawable.emotions6
            ImageResources.INTERESTS -> R.drawable.interests7
            ImageResources.RECONCILIATION -> R.drawable.reconciliation8
            ImageResources.CONFLICT -> R.drawable.conflict9
            ImageResources.CAUCUS -> R.drawable.caucus10
            ImageResources.NEUTRALITY -> R.drawable.neutrality11
            ImageResources.CONFIDENTIALITY -> R.drawable.confidentiality12
            ImageResources.DONE -> R.drawable.done
        }
    }

    private fun observeViewModel(imageButtons: MutableList<ImageView>) {
        viewModel.imagesLD.observe(viewLifecycleOwner) {
            imagesList.clear()
            for (image in it) {
                imagesList.add(image)
            }
            for (i in 0 until imageButtons.size) {
                if (imagesList[i] == ImageResources.DONE) {
                    imageButtons[i].setImageResource(R.drawable.done)
                } else {
                    imageButtons[i].setImageResource(R.drawable.cardback)
                }
            }
        }
        viewModel.guessed.observe(viewLifecycleOwner) {
            if (it) {
                for (i in 0 until imageButtons.size) {
                    if (imagesList[i] == ImageResources.DONE) {
                        imageButtons[i].isEnabled = false
                    } else {
                        imageButtons[i].isEnabled = true
                    }
                }
            } else {
                for (i in 0 until imageButtons.size) {
                    if (imagesList[i] == ImageResources.DONE) {
                        imageButtons[i].setImageResource(R.drawable.done)
                        imageButtons[i].isEnabled = false
                    } else {
                        imageButtons[i].setImageResource(R.drawable.cardback)
                        imageButtons[i].isEnabled = true
                    }
                }
            }
        }
        viewModel.comparison.observe(viewLifecycleOwner) { it ->
            if (it) {
                imageButtons.forEach { imageButton -> imageButton.isEnabled = false }
            }
        }

        viewModel.gameFinished.observe(viewLifecycleOwner) {
            val finishTime = System.currentTimeMillis()
            val gameTime = (finishTime - startTime) / 1000
            val fragment = FinalFragment.newInstance(gameTime)
            requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.memo_fragment_container, fragment)
                    .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val GAME_LEVEL_1 = "game level 1"
        private const val GAME_LEVEL_2 = "game level 2"
        private const val GAME_LEVEL_3 = "game level 3"
        const val NAME = "GameFragment"

        @JvmStatic
        fun newInstanceLevel1(startTime: Long) = GameFragment().apply {
            arguments = Bundle().apply {
                putString(
                    GAME_MODE, GAME_LEVEL_1
                )
                putLong(START_TIME, startTime)
            }
        }

        @JvmStatic
        fun newInstanceLevel2(startTime: Long) = GameFragment().apply {
            arguments = Bundle().apply {
                putString(
                    GAME_MODE, GAME_LEVEL_2
                )
                putLong(START_TIME, startTime)
            }
        }

        @JvmStatic
        fun newInstanceLevel3(startTime: Long) = GameFragment().apply {
            arguments = Bundle().apply {
                putString(GAME_MODE, GAME_LEVEL_3)
                putLong(START_TIME, startTime)
            }
        }
    }
}







