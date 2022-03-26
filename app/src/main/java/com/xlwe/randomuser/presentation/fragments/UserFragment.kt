package com.xlwe.randomuser.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.xlwe.randomuser.R
import com.xlwe.randomuser.databinding.FragmentUserBinding
import com.xlwe.randomuser.domain.result.NetworkResult
import com.xlwe.randomuser.domain.result.Status
import com.xlwe.randomuser.presentation.Constants
import com.xlwe.randomuser.presentation.OnClick
import com.xlwe.randomuser.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var onClick: OnClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClick)
            onClick = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setClickListener()

        binding.swipeRefresh.setOnRefreshListener {
            mainViewModel.update()
        }
    }

    private fun setClickListener() {
        binding.phoneTV.setOnClickListener {
            onClick.clickPhone(binding.phoneTV.text.toString())
        }

        binding.coordinates.setOnClickListener {
            onClick.clickLocation(latitude, longitude)
        }
    }

    private var latitude = ""
    private var longitude = ""

    private fun observeViewModel() {
        mainViewModel.networkLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.VISIBLE
        }

        mainViewModel.picture.observe(viewLifecycleOwner) {
            hide()

            Glide.with(binding.picture)
                .load(it)
                .transform(CenterCrop(), RoundedCorners(Constants.RADIUS))
                .into(binding.picture)
        }

        mainViewModel.fullName.observe(viewLifecycleOwner) {
            binding.name.text = it
        }

        mainViewModel.dob.observe(viewLifecycleOwner) {
            binding.date.text = it
        }

        mainViewModel.phone.observe(viewLifecycleOwner) {
            binding.phone.visibility = View.VISIBLE
            binding.phoneTV.text = it
        }

        mainViewModel.country.observe(viewLifecycleOwner) {
            binding.location.visibility = View.VISIBLE
            binding.country.text = it
        }

        mainViewModel.city.observe(viewLifecycleOwner) {
            binding.city.text = it
        }

        mainViewModel.state.observe(viewLifecycleOwner) {
            binding.state.text = it
        }

        mainViewModel.coordinates.observe(viewLifecycleOwner) {
            binding.coordinates.text = it
        }

        mainViewModel.noConnection.observe(viewLifecycleOwner) {
            if (it == Constants.NO_CONNECTION) {
                hide()
                Snackbar.make(binding.root, R.string.no_connection, Snackbar.LENGTH_SHORT).show()
                mainViewModel.setConnectionError()
            }
        }

        mainViewModel.serviceUnavailable.observe(viewLifecycleOwner) {
            if (it == Constants.SERVICE_UNAVAILABLE) {
                hide()
                Snackbar.make(binding.root, R.string.service_unavailable, Snackbar.LENGTH_SHORT)
                    .show()
                mainViewModel.setServiceUnavailableError()
            }
        }
    }

    private fun hide() {
        binding.swipeRefresh.isRefreshing = false
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = UserFragment()
    }
}