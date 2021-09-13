package com.welopes.evaluation.ui.fragment

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun setTitle(title: String) {
        val supportActionBar = (activity as AppCompatActivity).supportActionBar
        supportActionBar?.title = title
    }

    fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }
}