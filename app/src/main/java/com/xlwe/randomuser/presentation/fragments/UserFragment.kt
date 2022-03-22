package com.xlwe.randomuser.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.xlwe.randomuser.R
import com.xlwe.randomuser.databinding.FragmentUserBinding
import com.xlwe.randomuser.domain.result.NetworkResult
import com.xlwe.randomuser.domain.result.Status
import com.xlwe.randomuser.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

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
    }

    private fun observeViewModel() {
        mainViewModel.user.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("attadag", "${it.user}")
                }
                is NetworkResult.Error -> {
                    if (it.status == Status.NO_CONNECTION)
                        Snackbar.make(binding.root, R.string.no_connection, Snackbar.LENGTH_SHORT).show()
                    else
                        Snackbar.make(binding.root, R.string.service_unavailable, Snackbar.LENGTH_SHORT).show()
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