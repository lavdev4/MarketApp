package com.example.marketapp.presentation.screens

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs

class ErrorDialog : DialogFragment() {
    private val args: ErrorDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(args.title)
            .setMessage(args.message)
            .setPositiveButton("OK", null)
            .create()
    }
}