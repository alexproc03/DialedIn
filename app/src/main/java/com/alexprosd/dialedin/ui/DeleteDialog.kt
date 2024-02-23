package com.alexprosd.dialedin.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.alexprosd.dialedin.entrydata.Entry
import com.alexprosd.dialedin.entrydata.EntryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteDialog(private val entry: Entry) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Do you want to delete Entry?")
            .setPositiveButton("Yes") { dialog, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    val entryDao = EntryDatabase.getDatabase(requireContext()).EntryDao()
                    entryDao.deleteEntry(entry)
                    Log.d(TAG, "Entry deleted successfully")
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                }
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        return builder.create()
    }

}
