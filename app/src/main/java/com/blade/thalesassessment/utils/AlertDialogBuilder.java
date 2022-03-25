package com.blade.thalesassessment.utils;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AlertDialogBuilder {

    public static void showErrorDialog(Context context, String message) {
        new MaterialAlertDialogBuilder(context)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }

}
