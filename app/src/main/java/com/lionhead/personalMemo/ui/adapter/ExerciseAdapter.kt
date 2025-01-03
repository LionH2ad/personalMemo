package com.lionhead.personalMemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lionhead.personalMemo.R
import com.lionhead.personalMemo.data.Exercise

class ExerciseAdapter(
    private val onDeleteClick: (Exercise) -> Unit,
    private val onItemClick: (Exercise) -> Unit
) : ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Exercise>() {
            override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise) = oldItem == newItem
        }
    }

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

        fun bind(exercise: Exercise) {
            titleTextView.text = exercise.title
            deleteButton.setOnClickListener { onDeleteClick(exercise) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = getItem(position)
        holder.bind(exercise)
        holder.itemView.setOnClickListener { onItemClick(exercise) }
    }
}
