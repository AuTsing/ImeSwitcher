package com.autsing.imeswitcher

import android.app.Activity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.topjohnwu.superuser.Shell

class ImeSwitcherActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listImes = "ime list -s"
        val imes = Shell.cmd(listImes).exec().out
        val getCurrentIme = "settings get secure ${Settings.Secure.DEFAULT_INPUT_METHOD}"
        val currentIme = Shell.cmd(getCurrentIme).exec().out.first()
        val nextIme = imes.getOrNull(imes.indexOf(currentIme) + 1) ?: imes.first()
        val setIme = "ime set $nextIme"
        val success = Shell.cmd(setIme).exec().isSuccess

        if (success) {
            Toast.makeText(this, "Switched to: $nextIme", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Switch failed", Toast.LENGTH_SHORT).show()
        }

        val hideQs = "service call statusbar 2"
        Shell.cmd(hideQs).exec()

        val restImes = imes.filter { it != nextIme }
        for (ime in restImes) {
            val packageName = ime.substringBefore("/")
            Shell.cmd("am force-stop $packageName").exec()
        }

        finish()
    }
}
