package com.catnip.dialogexample.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.catnip.dialogexample.databinding.DialogInputNameBinding

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object DialogUtils {
    fun showInputNameDialog(
        context: Context,
        title: String,
        onButtonClick: (AlertDialog?, String) -> (Unit)
    ) {
        var dialog: AlertDialog? = null
        val dialogBinding =
            DialogInputNameBinding.inflate((context as AppCompatActivity).layoutInflater).apply {
                tvTitleDialog.text = title
                btnSubmitName.setOnClickListener {
                    onButtonClick(dialog, etName.text.toString())
                }
            }
        dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .create()
        dialog.show()

    }
}