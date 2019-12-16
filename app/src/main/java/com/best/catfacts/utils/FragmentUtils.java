package com.best.catfacts.utils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class FragmentUtils {

    public static void openFragmentDialogWithTag(FragmentManager fragmentManager, DialogFragment dialogFragment, String fragmentTag) {
        DialogFragment dialog = dialogFragment;
        dialog.show(fragmentManager, fragmentTag);
    }

    public static void openFragmentDialog(FragmentManager fragmentManager, DialogFragment dialogFragment) {
        final String FRAGMENT_TAG = dialogFragment.getClass().getName();
        DialogFragment dialog = dialogFragment;
        dialog.show(fragmentManager, FRAGMENT_TAG);
    }



}
