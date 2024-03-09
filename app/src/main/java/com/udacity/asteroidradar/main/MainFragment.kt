package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.basecontent.BaseFragment
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mainViewModel: MainViewModel by viewModels()

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
        mFragmentBinding.lifecycleOwner = this
    }

    override fun initViews() {
        mFragmentBinding.viewModel = mainViewModel
    }

    override fun initActions() {

    }

    override fun initObserver() {

    }

    override fun getViewBinding() = R.layout.fragment_main


}
