package com.carlospinan.maze3d.ui.maze.engine

import android.graphics.Canvas
import android.os.SystemClock

private const val FPS = 30L

class MazeThread(
    private val engine: MazeEngine
) : Thread() {

    private val holder = engine.mazeView.holder

    var running = false
    var pause = false

    override fun run() {
        var lastTimestamp = SystemClock.elapsedRealtime()
        var canvas: Canvas? = null

        while (running) {
            sleep(1000L / FPS)
            if (!pause) {
                canvas = holder.lockCanvas()
                synchronized(holder) {
                    val dt = (SystemClock.elapsedRealtime() - lastTimestamp).toFloat() / 1000F
                    engine.update(dt)
                    engine.mazeView.draw(canvas)
                }
                lastTimestamp = SystemClock.elapsedRealtime()
                holder.unlockCanvasAndPost(canvas)
            }
        }
    }

}