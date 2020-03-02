package com.carlospinan.maze3d.ui.maze

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlospinan.maze3d.ui.maze.engine.MazeView

class MazeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MazeView(this))
    }

}