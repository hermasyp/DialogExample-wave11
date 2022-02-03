package com.catnip.dialogexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.catnip.dialogexample.databinding.ActivityMainBinding
import com.catnip.dialogexample.utils.DialogUtils

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnShowSimpleDialog.setOnClickListener {
            showSimpleAlertDialog()
        }
        binding.btnShowDialogWithButton.setOnClickListener {
            showAlertDialogWithButton()
        }
        binding.btnShowDialogWithCombobox.setOnClickListener {
            showAlertDialogWithComboBox()
        }
        binding.btnShowCustomAlertDialog.setOnClickListener {
            showCustomAlertDialog("Welcome to the Game !")
        }
        binding.btnShowDialogFragment.setOnClickListener {
            InputNameDialogFragment("Welcome to the Game !") { dialog, value ->
                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }.show(supportFragmentManager,null)
        }
    }

    private fun showCustomAlertDialog(title: String) {
/*        var dialog: AlertDialog? = null
        val dialogBinding = DialogInputNameBinding.inflate(layoutInflater).apply {
            tvTitleDialog.text = title
            btnSubmitName.setOnClickListener {
                Toast.makeText(this@MainActivity, etName.text.toString(), Toast.LENGTH_SHORT).show()
                dialog?.dismiss()
            }
        }
        dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()
        dialog.show()*/

        DialogUtils.showInputNameDialog(this, title) { dialog, value ->
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }

    }


    private fun showSimpleAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Hello this is a Title")
            .setMessage("Hello this is a Message")
            .show()
    }

    private fun showAlertDialogWithButton() {
        AlertDialog.Builder(this)
            .setTitle("New Version Available !")
            .setMessage("Do You want to download the new Version ?")
            .setCancelable(false)
            .setPositiveButton("Download New Version") { _, _ ->
                Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Nope") { _, _ ->
                Toast.makeText(this, "Ignoring New Version", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Remind Me Later") { _, _ ->
                Toast.makeText(this, "Will remind you Tomorrow", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun showAlertDialogWithComboBox() {
        val selectedItem = arrayListOf<Int>()
        AlertDialog.Builder(this)
            .setTitle("Favorite Motherboard Vendor")
            .setMultiChoiceItems(
                R.array.motherboard_list, booleanArrayOf(false, false, false, false, false)
            ) { _, item, isChecked ->
                if (isChecked) {
                    selectedItem.add(item)
                } else {
                    selectedItem.remove(item)
                }
            }
            .setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this, selectedItem.toString(), Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
            }
            .show()
    }


}