package com.carlospinan.maze3d.ui.maze.engine

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

data class Player(
    var x: Float = 16F,
    var y: Float = 10F,
    // The direction that the player is turning, either -1 for left or 1 for right
    var direction: Int = 0,
    var rotation: Float = 0F,
    // Is the playing moving forward (speed = 1) or backwards (speed = -1).
    var speed: Float = 0F,
    val moveSpeed: Float = 0.2F,
    val rotationSpeed: Float = 6F * (PI.toFloat() * 180F)
) {

    fun move() {
        val moveStep = speed * moveSpeed
        rotation += direction * rotationSpeed

        val newX = x + cos(rotation) * moveSpeed
        val newY = y + sin(rotation) * moveSpeed

        x = newX
        y = newY
    }

}