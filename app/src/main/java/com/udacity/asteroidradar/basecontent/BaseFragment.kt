package com.udacity.asteroidradar.basecontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding> : Fragment(), IBaseFragment {


    private var _mFragmentBinding: VB? = null
    val mFragmentBinding: VB get() = _mFragmentBinding!!

    abstract fun getViewBinding(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _mFragmentBinding = DataBindingUtil.inflate(inflater, getViewBinding(), container, false)
        initData()
        initViews()
        initActions()
        initObserver()
        return _mFragmentBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mFragmentBinding = null
    }

}