package com.lionhead.personalMemo.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.lionhead.personalMemo.R

class ExerciseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val videoPath = intent.getStringExtra("videoPath")
        val weight = intent.getIntExtra("weight", 0)
        val reps = intent.getIntExtra("reps", 0)
        val sets = intent.getIntExtra("sets", 0)

        findViewById<TextView>(R.id.detailTitleTextView).text = title
        findViewById<TextView>(R.id.detailDescriptionTextView).text = description
        findViewById<TextView>(R.id.detailStatsTextView).text = "무게: ${weight}kg | 반복: ${reps}회 | 세트: ${sets}세트"

        val videoView = findViewById<VideoView>(R.id.videoView)
        videoPath?.let {
            videoView.setVideoPath(it)
            videoView.start()
        }
    }
}
