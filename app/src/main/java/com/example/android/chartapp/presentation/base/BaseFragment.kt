package com.example.android.chartapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    fun back() {
        findNavController().popBackStack()
    }

    fun backTo(destinationId: Int) {
        findNavController().popBackStack(destinationId, true)
    }

    fun navigateTo(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}