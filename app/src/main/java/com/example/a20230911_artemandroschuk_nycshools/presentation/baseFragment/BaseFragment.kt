package com.example.a20230911_artemandroschuk_nycshools.presentation.baseFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.a20230911_artemandroschuk_nycshools.presentation.utils.hideKeyBoard

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.hideKeyBoard()
        setupClicks()
    }

    open fun setupClicks() {}
}