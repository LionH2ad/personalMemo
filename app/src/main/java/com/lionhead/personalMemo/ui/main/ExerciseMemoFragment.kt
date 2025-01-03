package com.lionhead.personalMemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lionhead.personalMemo.databinding.FragmentExerciseListBinding
import com.lionhead.personalMemo.databinding.FragmentExerciseMemoBinding

class ExerciseMemoFragment : Fragment() {

    private lateinit var binding: FragmentExerciseMemoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseMemoBinding.inflate(inflater)
        return binding.root
    }
}