package com.codingtest.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.DialogFragment
import com.codingtest.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            DialogFragment.STYLE_NO_TITLE,
            R.style.AppTheme_BaseBottomSheetDialog
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            makeFullScreen(this)
        }

    private fun makeFullScreen(dialog: Dialog) {
        dialog.setOnShowListener { showedDialog ->
            val bottomSheetDialog = showedDialog as BottomSheetDialog
            val bottomSheet = bottomSheetDialog
                .findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            bottomSheet?.apply {
                updateLayoutParams { height = ViewGroup.LayoutParams.MATCH_PARENT }
                setBackgroundResource(R.color.colorTransparent)
            }

            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetDialog.behavior.peekHeight = resources.displayMetrics.heightPixels

            bottomSheetDialog.window?.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
            )
        }
    }
}