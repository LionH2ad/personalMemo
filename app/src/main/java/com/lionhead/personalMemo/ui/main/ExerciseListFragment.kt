package com.lionhead.personalMemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lionhead.personalMemo.databinding.FragmentExerciseListBinding

class ExerciseListFragment : Fragment() {

    private lateinit var binding: FragmentExerciseListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseListBinding.inflate(inflater)
        return binding.root
    }
}