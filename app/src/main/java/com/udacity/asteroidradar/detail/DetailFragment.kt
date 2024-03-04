package com.udacity.asteroidradar.detail

import androidx.appcompat.app.AlertDialog
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.basecontent.BaseFragment
import com.udacity.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun getViewBinding() = R.layout.fragment_detail

    override fun initData() {
        mFragmentBinding.lifecycleOwner = this
        val asteroid = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid
        mFragmentBinding.asteroid = asteroid
    }

    override fun initViews() {

    }

    override fun initActions() {
        mFragmentBinding.helpButton.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }
    }

    override fun initObserver() {

    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
