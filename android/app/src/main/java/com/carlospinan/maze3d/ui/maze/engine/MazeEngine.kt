package com.carlospinan.maze3d.ui.maze.engine

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import androidx.core.math.MathUtils
import kotlin.math.*

private const val TILE_SIZE = 8
private const val TILE_WALL_COLOR = 0xFFFF0000.toInt()
private const val TILE_EMPTY_COLOR = 0xFF00FF00.toInt()

class MazeEngine(
    val mazeView: MazeView
) : View.OnTouchListener {

    private val maze = arrayOf(
        intArrayOf(
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            3,
            0,
            3,
            0,
            0,
            1,
            1,
            1,
            2,
            1,
            1,
            1,
            1,
            1,
            2,
            1,
            1,
            1,
            2,
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            3,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            3,
            1,
            1,
            1,
            1,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            3,
            0,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            3,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            2
        ),
        intArrayOf(
            1,
            0,
            0,
            3,
            0,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            3,
            1,
            1,
            1,
            1,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            2
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            3,
            1,
            1,
            1,
            1,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            3,
            3,
            3,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            4,
            0,
            0,
            4,
            2,
            0,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            0,
            2,
            4,
            4,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            4,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            4,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            4,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            4,
            3,
            3,
            4,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            2,
            4,
            3,
            3,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1
        ),
        intArrayOf(
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1
        )
    )

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mapHeight = maze.size
    private val mapWidth = maze[0].size

    private val screenWidth = mapWidth * TILE_SIZE
    private val screenHeight = mapHeight * TILE_SIZE

    private val stripWidth = 4F
    private val fov = 60F * Math.PI.toFloat() / 180F
    private val fovHalf = fov / 2F
    private val numRays = ceil(screenWidth / stripWidth).toInt()
    private val viewDistance = (screenWidth / 2F) / tan(fovHalf)
    private val twoPi = Math.PI.toFloat() * 2F

    private val player: Player =
        Player(x = 16F * TILE_SIZE, y = 10F * TILE_SIZE, tileSize = TILE_SIZE)

    private fun drawMiniMap(canvas: Canvas) {
        for (y in 0 until mapHeight) {
            for (x in 0 until mapWidth) {
                val tile = maze[y][x]
                val rect = Rect(
                    x * TILE_SIZE,
                    y * TILE_SIZE,
                    x * TILE_SIZE + TILE_SIZE,
                    y * TILE_SIZE + TILE_SIZE
                )

                if (tile > 0) {
                    paint.color = TILE_WALL_COLOR
                } else {
                    paint.color = TILE_EMPTY_COLOR
                }
                canvas.drawRect(rect, paint)
            }
        }

        player.draw(canvas)

    }

    fun draw(canvas: Canvas) {

        canvas.save()

        canvas.drawColor(0xFFAAAAAA.toInt())

        val scaleX = canvas.width.toFloat() / screenWidth.toFloat()
        val scaleY = canvas.height.toFloat() / screenHeight.toFloat()
        val scale =
            (canvas.height.toFloat() / canvas.width.toFloat()) * (min(scaleX, scaleY)) * 0.5F

        canvas.scale(scaleX, scaleX)
        // canvas.scale(scaleX, scaleY)

        drawMiniMap(canvas)

        castRays(canvas)

        canvas.restore()
    }

    fun update(dt: Float) {
        val newPosition = player.newPosition(dt)
        if (isSafe(newPosition.x, newPosition.y)) {
            player.x = newPosition.x
            player.y = newPosition.y
        }
    }

    private fun isSafe(x: Float, y: Float): Boolean {
        val tile = positionToTile(x, y)
        return tile.x in 0 until mapWidth &&
                tile.y in 0 until mapHeight &&
                maze[tile.y][tile.x] == 0
    }

    private fun normalizeTouch(x: Float, y: Float): PointF {
        return PointF(x / mapWidth, y / mapHeight)
    }

    private fun tileTouch(x: Float, y: Float): Point {
        val normalized = normalizeTouch(x, y)
        return Point(
            MathUtils.clamp(normalized.x.toInt(), 0, mapWidth),
            MathUtils.clamp(normalized.y.toInt(), 0, mapHeight)
        )
    }

    private fun positionToTile(x: Float, y: Float): Point {
        val tileX = floor(x / TILE_SIZE).toInt()
        val tileY = floor(y / TILE_SIZE).toInt()
        return Point(
            MathUtils.clamp(tileX, 0, mapWidth),
            MathUtils.clamp(tileY, 0, mapHeight)
        )
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let { motionEvent ->
            val x = motionEvent.x
            val y = motionEvent.y
            val normalized = normalizeTouch(x, y)
            val touchedTile = tileTouch(x, y)
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    when {
                        touchedTile.x <= mapWidth * 0.2f -> {
                            player.direction = -1
                        }
                        touchedTile.x >= mapWidth * 0.8f -> {
                            player.direction = 1
                        }
                        else -> {
                            player.speed = 1F
                        }
                    }

                }
                MotionEvent.ACTION_UP -> {
                    player.speed = 0F
                    player.direction = 0
                }
                else -> {

                }
            }
        }
        return true
    }

    // Ray Casting
    private fun castRays(canvas: Canvas) {
        for ((stripIndex, i) in (0 until numRays).withIndex()) {
            val rayScreenPosition = (-numRays * 0.5F + i) * stripWidth
            val rayViewDistance =
                sqrt(rayScreenPosition * rayScreenPosition + viewDistance * viewDistance)
            val rayAngle = asin(rayScreenPosition / rayViewDistance)
            castSingleRay(
                canvas,
                player.rotation + rayAngle,
                stripIndex
            )
        }
    }

    private fun castSingleRay(canvas: Canvas, rayAngle: Float, stripIndex: Int) {
        var rayAngle = rayAngle % twoPi
        if (rayAngle < 0) {
            rayAngle += twoPi
        }
    }

}