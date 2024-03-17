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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.basecontent.BaseFragment
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.db.AsteroidDataType
import com.udacity.asteroidradar.models.convertToAsteroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mainViewModel: MainViewModel by viewModels()
    private val asteroidAdapter: AsteroidAdapter = AsteroidAdapter()

    private val menuProvider = object : MenuProvider {

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.main_overflow_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                R.id.nextWeekAsteroids -> {
                    mainViewModel.onChangeAsteroidDataType(AsteroidDataType.WEEK)
                    Toast.makeText(requireContext(), "Next week", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.todayAsteroids -> {
                    Toast.makeText(requireContext(), "Today", Toast.LENGTH_SHORT).show()
                    mainViewModel.onChangeAsteroidDataType(AsteroidDataType.TODAY)
                    true
                }

                R.id.savedAsteroids -> {
                    mainViewModel.onChangeAsteroidDataType(AsteroidDataType.ALL_TIME)
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
        val layoutManager = GridLayoutManager(requireContext(), 1)
        //val decorationItem = DividerItemDecoration(requireContext(), layoutManager.orientation)
        val decorationItem = DividerItem(R.color.color_ffff, 2)
        mFragmentBinding.asteroidRecycler.layoutManager = layoutManager
        mFragmentBinding.asteroidRecycler.addItemDecoration(decorationItem)
        mFragmentBinding.asteroidRecycler.adapter = asteroidAdapter
    }

    override fun initActions() {
        asteroidAdapter.onItemClick = {
            Toast.makeText(requireContext(), it.codeName, Toast.LENGTH_SHORT).show()
            findNavController().navigate(MainFragmentDirections.actionShowDetail(it.convertToAsteroid()))
        }
        mFragmentBinding.swipeMain.setOnRefreshListener {
            mainViewModel.loadData()
            CoroutineScope(Dispatchers.Main).launch {
                mFragmentBinding.swipeMain.isRefreshing = false
            }
        }

    }

    override fun initObserver() {
        mainViewModel.asteroidList.observe(viewLifecycleOwner) {
            asteroidAdapter.submitList(it)
        }
    }

    override fun getViewBinding() = R.layout.fragment_main


}
