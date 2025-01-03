package com.lionhead.personalMemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lionhead.personalMemo.databinding.FragmentExerciseExplainBinding
import com.lionhead.personalMemo.databinding.FragmentExerciseListBinding

class ExerciseExplainFragment : Fragment() {

    private lateinit var binding: FragmentExerciseExplainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseExplainBinding.inflate(inflater)
        return binding.root
    }
}