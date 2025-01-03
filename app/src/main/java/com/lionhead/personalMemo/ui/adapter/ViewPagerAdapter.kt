package com.lionhead.personalMemo.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lionhead.personalMemo.ui.main.ExerciseExplainFragment
import com.lionhead.personalMemo.ui.main.ExerciseListFragment
import com.lionhead.personalMemo.ui.main.ExerciseMemoFragment

class ViewPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ExerciseListFragment()
        1 -> ExerciseExplainFragment()
        2 -> ExerciseMemoFragment()
        else -> ExerciseListFragment()
    }
}