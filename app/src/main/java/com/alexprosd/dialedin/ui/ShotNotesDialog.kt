package com.alexprosd.dialedin.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.dialedin.R

class NotesDialogFragment(private val notes: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_notes, null)
        val notesTextView = view.findViewById<TextView>(R.id.notesTextView)
        notesTextView.text = notes
        builder.setView(view)
            .setTitle("Notes")
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
        return builder.create()
    }
}

