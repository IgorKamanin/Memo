package com.example.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.memo.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    val binding by lazy {
        FragmentGameBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: GameViewModel




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
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        viewModel.generateLevel()
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

        viewModel.imagesLD.observe(viewLifecycleOwner){
//            Toast.makeText(requireContext(), it[0].toString(), Toast.LENGTH_LONG).show()

            for (i in 0 until images.size) {
                images[i].setImageResource(getResourceId(it[i]))
            }
        }


        for (image in images) {
            image.setOnClickListener {
                image.setImageResource(R.drawable.done)
            }
        }
    }

    private fun getResourceId(s: ImageResources):Int{
        return when(s){
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
        }

    }

    companion object {
        fun newInstance() = GameFragment()
    }

}




