package com.example.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.memo.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    val binding by lazy {
        FragmentGameBinding.inflate(layoutInflater)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
//        inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = mutableListOf<ImageView>().apply {
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

        for (image in images) {
            image.setOnClickListener {
                image.setImageResource(R.drawable.done)
            }
        }
    }

    companion object {
        fun newInstance() = GameFragment()
    }

}




