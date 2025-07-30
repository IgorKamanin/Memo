package com.example.memo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memo.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("binding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            textView1lv2.isEnabled = false
            textView1lv3.isEnabled = false
            cardViewLvl2.isEnabled = false
            cardViewLvl3.isEnabled = false
            textView1lvl.setOnClickListener {
                val startTime = System.currentTimeMillis()
                val fragment = GameFragment.newInstanceLevel1(startTime)
                requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack(GameFragment.NAME)
                    .replace(R.id.memo_fragment_container, fragment).commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "StartFragment"

        @JvmStatic
        fun newInstance() = StartFragment()
    }
}
