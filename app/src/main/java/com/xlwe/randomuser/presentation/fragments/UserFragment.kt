package com.xlwe.randomuser.presentation.fragments

import android.content.Context
import android.os.Bundle
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
        mainViewModel.user.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false

            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE

                    it.result?.apply {
                        val res = results[Constants.INDEX]

                        Glide.with(binding.picture)
                            .load(res.picture.large)
                            .transform(CenterCrop(), RoundedCorners(Constants.RADIUS))
                            .into(binding.picture)

                        val fullName =
                            res.name.title + " " + res.name.first + " " + res.name.last

                        binding.name.text = fullName

                        val date = res.dob.date.substring(
                            Constants.START_SUBSTRING,
                            Constants.END_SUBSTRING
                        ).replace("-", ".").split(".").asReversed()

                        var fullDate = ""

                        for (d in date.indices) {
                            if (d != date.size - Constants.GET_INDEX_LAST_ELEMENT)
                                fullDate += date[d] + "."
                            else
                                fullDate += date[d]
                        }

                        binding.date.text = fullDate

                        binding.phone.visibility = View.VISIBLE
                        binding.phoneTV.text = res.phone

                        binding.location.visibility = View.VISIBLE
                        binding.country.text = res.location.country
                        binding.city.text = res.location.city
                        binding.state.text = res.location.state

                        latitude = res.location.coordinates.latitude
                        longitude = res.location.coordinates.longitude
                        binding.coordinates.text = latitude + " " + longitude
                    }
                }
                is NetworkResult.Error -> {
                    if (it.status == Status.NO_CONNECTION)
                        Snackbar.make(binding.root, R.string.no_connection, Snackbar.LENGTH_SHORT)
                            .show()
                    else
                        Snackbar.make(
                            binding.root,
                            R.string.service_unavailable,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = UserFragment()
    }
}