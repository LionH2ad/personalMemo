package com.lionhead.personalMemo.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lionhead.personalMemo.ExerciseViewModel
import com.lionhead.personalMemo.R
import com.lionhead.personalMemo.data.Exercise
import com.lionhead.personalMemo.databinding.FragmentExerciseListBinding
import com.lionhead.personalMemo.ui.ExerciseDetailActivity
import com.lionhead.personalMemo.ui.ExerciseViewModelFactory
import com.lionhead.personalMemo.ui.adapter.ExerciseAdapter

class ExerciseListFragment : Fragment() {

    private lateinit var binding: FragmentExerciseListBinding
    private lateinit var viewModel: ExerciseViewModel
    private lateinit var adapter: ExerciseAdapter

    private var selectedVideoPath: String? = null

    companion object {
        private const val VIDEO_PICK_REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ExerciseViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[ExerciseViewModel::class.java]
        adapter = ExerciseAdapter(onDeleteClick = { exercise ->
            viewModel.deleteExercise(exercise.id)
        }, onItemClick = { exercise ->
            val intent = Intent(requireContext(), ExerciseDetailActivity::class.java).apply {
                putExtra("title", exercise.title)
                putExtra("description", exercise.description)
                putExtra("videoPath", exercise.videoPath)
                putExtra("weight", exercise.weight)
                putExtra("reps", exercise.reps)
                putExtra("sets", exercise.sets)
            }
            startActivity(intent)
        })

        val recyclerView: RecyclerView = binding.exerciseRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.allExercises.observe(viewLifecycleOwner) { exercises ->
            adapter.submitList(exercises)
        }

        binding.addExerciseButton.setOnClickListener {
            showAddExerciseDialog()
        }
    }

    private fun showAddExerciseDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_exercise, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("운동 추가")
            .setView(dialogView)
            .setNegativeButton("취소", null)
            .create()

        val selectVideoButton = dialogView.findViewById<Button>(R.id.selectVideoButton)
        selectVideoButton.setOnClickListener {
            selectVideoFromGallery()
        }

        dialogView.findViewById<Button>(R.id.saveExerciseButton).setOnClickListener {
            val title = dialogView.findViewById<EditText>(R.id.exerciseTitleEditText).text.toString()
            val description = dialogView.findViewById<EditText>(R.id.exerciseDescriptionEditText).text.toString()
            val weight = dialogView.findViewById<EditText>(R.id.exerciseWeightEditText).text.toString().toIntOrNull() ?: 0
            val reps = dialogView.findViewById<EditText>(R.id.exerciseRepsEditText).text.toString().toIntOrNull() ?: 0
            val sets = dialogView.findViewById<EditText>(R.id.exerciseSetsEditText).text.toString().toIntOrNull() ?: 0

            val exercise = Exercise(
                title = title,
                description = description,
                videoPath = selectedVideoPath,
                weight = weight,
                reps = reps,
                sets = sets
            )

            viewModel.addExercise(exercise)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun selectVideoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        intent.type = "video/*"
        startActivityForResult(intent, VIDEO_PICK_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VIDEO_PICK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            uri?.let {
                selectedVideoPath = getPathFromUri(it)
                Toast.makeText(requireContext(), "동영상 선택됨: $selectedVideoPath", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getPathFromUri(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        requireContext().contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                return cursor.getString(columnIndex)
            }
        }
        return null
    }

}