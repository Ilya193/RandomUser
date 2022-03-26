package com.xlwe.randomuser.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.xlwe.randomuser.databinding.UserItemBinding
import com.xlwe.randomuser.domain.models.Result
import com.xlwe.randomuser.presentation.Constants
import com.xlwe.randomuser.presentation.OnClick

class UsersAdapter(
    private val onClick: OnClick
) : ListAdapter<Result, UsersAdapter.UsersViewHolder>(wordsComparator) {

    class UsersViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = getItem(position)

        Log.d("SIZE", "$position \n $currentList")

        with(holder) {
            user.apply {

                Glide.with(binding.picture)
                    .load(picture.large)
                    .transform(CenterCrop(), RoundedCorners(Constants.RADIUS))
                    .into(binding.picture)

                val fullName =
                    name.title + " " + name.first + " " + name.last

                binding.name.text = fullName

                val date = dob.date.substring(
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
                binding.phoneTV.text = phone

                binding.phoneTV.setOnClickListener {
                    onClick.clickPhone(binding.phoneTV.text.toString())
                }

                binding.location.visibility = View.VISIBLE
                binding.country.text = location.country
                binding.city.text = location.city
                binding.state.text = location.state

                binding.coordinates.text =
                    location.coordinates.latitude + " " + location.coordinates.longitude

                binding.coordinates.setOnClickListener {
                    onClick.clickLocation(
                        location.coordinates.latitude,
                        location.coordinates.longitude
                    )
                }
            }
        }
    }

    companion object {
        private val wordsComparator =
            object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}