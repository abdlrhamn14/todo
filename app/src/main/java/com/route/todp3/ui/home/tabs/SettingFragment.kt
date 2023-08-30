package com.route.todp3.ui.home.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.route.todp3.databinding.FragmentSettingBinding

class SettingFragment:Fragment() {
    lateinit var viewBinding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding= FragmentSettingBinding.inflate(inflater,container,false)
        return viewBinding.root

    }
}