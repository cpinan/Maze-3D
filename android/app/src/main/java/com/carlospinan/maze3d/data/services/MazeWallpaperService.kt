package com.carlospinan.maze3d.data.services

import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder

class MazeWallpaperService : WallpaperService() {

    private val context = MazeWallpaperService@ this

    override fun onCreateEngine(): Engine {
        return MazeGLEngine()
    }

    private inner class MazeGLEngine : Engine() {

        override fun onCreate(surfaceHolder: SurfaceHolder) {
            super.onCreate(surfaceHolder)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
        }

        override fun onDestroy() {
            super.onDestroy()
        }

    }

}