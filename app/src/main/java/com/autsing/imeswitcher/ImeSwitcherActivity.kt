package com.autsing.imeswitcher

import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.topjohnwu.superuser.Shell

class ImeSwitcherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val r1 = Shell.cmd("settings get secure ${Settings.Secure.ENABLED_INPUT_METHODS}")
            .exec()
        val imes = r1.out.joinToString().split(":")
        val r2 = Shell.cmd("settings get secure ${Settings.Secure.DEFAULT_INPUT_METHOD}")
            .exec()
        val defaultIme = r2.out.joinToString()
        val nextImeI = imes.indexOf(defaultIme) + 1
        val nextIme = imes.getOrElse(nextImeI) { imes[0] }
        val r3 = Shell.cmd("settings put secure ${Settings.Secure.DEFAULT_INPUT_METHOD} $nextIme")
            .exec()
        val r4 = Shell.cmd("service call statusbar 2")
            .exec()

        if (r3.isSuccess and r4.isSuccess) {
            Toast.makeText(this, "Switched to: $defaultIme", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Switch failed: ${r3.out.joinToString()}", Toast.LENGTH_SHORT)
                .show()
        }

        finish()
    }
}
