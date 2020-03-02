package com.carlospinan.maze3d.ui.maze.engine

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.View

private const val TILE_SIZE = 8
private const val TILE_WALL_COLOR = 0xFFFF0000.toInt()
private const val TILE_EMPTY_COLOR = 0xFF00FF00.toInt()

class MazeEngine(
    val mazeView: MazeView
) : View.OnTouchListener {

    private val maze = arrayOf(
        intArrayOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1),
        intArrayOf(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,2,0,0,0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,2,0,0,0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,2,0,0,0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,2,0,0,0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
        intArrayOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private fun drawMiniMap(canvas: Canvas) {
        canvas.drawColor(0xFFAAAAAA.toInt())

        val mapHeight = maze.size
        val mapWidth = maze[0].size

        val screenWidth = mapWidth * TILE_SIZE
        val screenHeight = mapHeight * TILE_SIZE

        val scaleX = canvas.width.toFloat() / screenWidth.toFloat()
        val scaleY = canvas.height.toFloat() / screenHeight.toFloat()

        canvas.scale(scaleX, scaleY)

        for(y in 0 until mapHeight) {
            for(x in 0 until mapWidth) {
                val tile = maze[y][x]
                val rect = Rect(
                    x * TILE_SIZE,
                    y * TILE_SIZE,
                    x * TILE_SIZE + TILE_SIZE,
                    y * TILE_SIZE + TILE_SIZE
                )

                if(tile > 0) {
                    paint.color = TILE_WALL_COLOR
                } else {
                    paint.color = TILE_EMPTY_COLOR
                }
                canvas.drawRect(rect, paint)
            }
        }

    }

    fun draw(canvas: Canvas) {
        drawMiniMap(canvas)
    }

    fun update(dt: Float) {

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let {
                motionEvent ->
            val x = motionEvent.x
            val y = motionEvent.y
            Log.d("ONTOUCH", "$x ; $y -- $motionEvent")
        }
        return true
    }

}