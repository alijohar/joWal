package com.aj.jowal

import android.content.Context
import android.content.Intent
import com.aj.jowal.ui.model.BankName


class Helper {
    companion object {

        fun shareText(context: Context, subject: String?, body: String?) {
            val txtIntent = Intent(Intent.ACTION_SEND)
            txtIntent.type = "text/plain"
            txtIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            txtIntent.putExtra(Intent.EXTRA_TEXT, body)
            context.startActivity(Intent.createChooser(txtIntent, "Share"))
        }

    }

}