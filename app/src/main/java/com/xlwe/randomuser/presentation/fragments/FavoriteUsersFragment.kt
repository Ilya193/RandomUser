package com.xlwe.randomuser.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.xlwe.randomuser.databinding.FragmentFavoriteUsersBinding
import com.xlwe.randomuser.domain.models.Result
import com.xlwe.randomuser.domain.models.User
import com.xlwe.randomuser.domain.result.NetworkResult
import com.xlwe.randomuser.presentation.Constants
import com.xlwe.randomuser.presentation.OnClick
import com.xlwe.randomuser.presentation.adapters.UsersAdapter
import com.xlwe.randomuser.presentation.viewmodels.MainViewModel

class FavoriteUsersFragment : Fragment(), OnClick {
    private var _binding: FragmentFavoriteUsersBinding? = null
    private val binding: FragmentFavoriteUsersBinding
        get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var usersAdapter: UsersAdapter
    private val mainListUser = mutableListOf<Result>()

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
        _binding = FragmentFavoriteUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.userDB.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    mainListUser.clear()

                    result.result!!.results.forEach {
                        mainListUser.add(it)
                        Log.d("attadag", "${it.name.first}")
                    }

                    usersAdapter.submitList(mainListUser.toList())
                }
                is NetworkResult.Error -> {}
            }
        }
    }

    private fun initRecyclerView() {
        usersAdapter = UsersAdapter(this)
        binding.recyclerView.adapter = usersAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun netInstance() = FavoriteUsersFragment()
    }

    override fun clickPhone(phone: String) {
        onClick.clickPhone(phone)
    }

    override fun clickLocation(latitude: String, longitude: String) {
        onClick.clickLocation(latitude, longitude)
    }
}