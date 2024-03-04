package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.basecontent.BaseFragment
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val menuProvider = object : MenuProvider {

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.main_overflow_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                R.id.nextWeekAsteroids -> {
                    Toast.makeText(requireContext(), "Next week", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.todayAsteroids -> {
                    Toast.makeText(requireContext(), "Today", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.savedAsteroids -> {
                    Toast.makeText(requireContext(), "Save", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false

            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun initData() {

    }

    override fun initViews() {
        mFragmentBinding.apply {
            this.lifecycleOwner = this@MainFragment
            this.viewModel = this@MainFragment.viewModel
        }
        mFragmentBinding.lifecycleOwner = this

        mFragmentBinding.viewModel = viewModel
    }

    override fun initActions() {

    }

    override fun initObserver() {

    }

    override fun getViewBinding() = R.layout.fragment_main


}
