package com.carlospinan.maze3d.ui.maze.engine

import android.content.Context
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView

class MazeView @JvmOverloads constructor(
    context: Context
) : SurfaceView(context), SurfaceHolder.Callback {

    private val engine: MazeEngine = MazeEngine(this)
    private val thread: MazeThread = MazeThread(engine)

    init {
        holder.addCallback(this)
        setOnTouchListener(engine)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.pause = true
        thread.running = false
        thread.join()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.pause = false
        thread.running = true
        thread.start()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.save()

        engine.draw(canvas)

        canvas.restore()
    }

}