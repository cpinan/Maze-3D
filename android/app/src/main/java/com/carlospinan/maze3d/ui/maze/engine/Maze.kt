package com.carlospinan.maze3d.ui.maze.engine

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private const val PLAYER_SIZE = 4F
private const val PLAYER_SPEED_FACTOR = 250F

data class Player(
    var x: Float = 16F,
    var y: Float = 10F,
    // The direction that the player is turning, either -1 for left or 1 for right
    var direction: Int = 0,
    var rotation: Float = 0F,
    // Is the playing moving forward (speed = 1) or backwards (speed = -1).
    var speed: Float = 0F,
    val moveSpeed: Float = 0.2F,
    val rotationSpeed: Float = 6F * (PI.toFloat() / 180F),
    val tileSize: Int
) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFF000000.toInt()
        strokeWidth = 1.0f
        style = Paint.Style.STROKE
        pathEffect = null
    }

    fun newPosition(dt: Float): PointF {
        val moveStep = speed * moveSpeed * PLAYER_SPEED_FACTOR
        rotation += direction * rotationSpeed

        val newX = x + cos(rotation) * moveStep * dt
        val newY = y + sin(rotation) * moveStep * dt

        return PointF(newX, newY)
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(
            x - PLAYER_SIZE / 2,
            y - PLAYER_SIZE / 2,
            x + PLAYER_SIZE / 2,
            y + PLAYER_SIZE / 2,
            paint
        )
        val path = Path()
        path.moveTo(x, y)
        path.lineTo(
            x + cos(rotation) * PLAYER_SIZE * 4,
            y + sin(rotation) * PLAYER_SIZE * 4
        )
        path.close()
        canvas.drawPath(path, paint)

    }

}