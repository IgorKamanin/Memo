package com.example.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memo.databinding.FragmentFinalBinding

private const val ARG_GAME_TIME = "Game time"

class FinalFragment : Fragment() {
    private var game_time: Long = -1
    private var _binding: FragmentFinalBinding? = null
    private val binding: FragmentFinalBinding
        get() = _binding ?: throw RuntimeException("binding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            game_time = it.getLong(ARG_GAME_TIME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFinalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        val congratsText = getString(R.string.congrats)+game_time.toString()+getString (generateText(game_time))
        binding.TextViewResult.text = congratsText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generateText(gameTime: Long): Int {
        return when {
            gameTime == 1L -> R.string.final_1
            gameTime % 10L == 1L && gameTime % 100L != 11L -> R.string.final_1
            gameTime % 10 in 2..4 && (gameTime % 100 < 10 || gameTime % 100 >= 20) -> R.string.final_2
            else -> R.string.final_3
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(GameTime: Long) = FinalFragment().apply {
            arguments = Bundle().apply { putLong(ARG_GAME_TIME, GameTime) }
        }
    }
}
