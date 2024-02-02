package com.autsing.imeswitcher

import android.content.Intent
import android.service.quicksettings.TileService

class ImeSwitcherTileService : TileService() {
    override fun onClick() {
        val intent = Intent(this, ImeSwitcherActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
