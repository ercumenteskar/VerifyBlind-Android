package com.verifyblind.mobile

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val locale = Locale("tr")
        Locale.setDefault(locale)
        val config = newBase.resources.configuration
        val newConfig = Configuration(config)
        newConfig.setLocale(locale)
        val context = newBase.createConfigurationContext(newConfig)
        super.attachBaseContext(context)
    }

    fun showMessage(title: String, message: String, onDismiss: (() -> Unit)? = null) {
        runOnUiThread {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(title)

                .setMessage(message)
                .setPositiveButton("Tamam") { _, _ ->
                    onDismiss?.invoke()
                }
                .setCancelable(false)
                .show()
        }
    }
}
