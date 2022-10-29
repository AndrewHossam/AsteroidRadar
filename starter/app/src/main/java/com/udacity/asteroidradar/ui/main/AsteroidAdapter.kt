package com.udacity.asteroidradar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidItemBinding
import com.udacity.asteroidradar.domain.Asteroid

class AsteroidAdapter(
    private val onItemClicked: (Asteroid) -> Unit,
) : ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = AsteroidItemBinding.inflate(layoutInflater, parent, false)
        return AsteroidViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener { onItemClicked.invoke(current) }
        holder.bind(current)
    }

    inner class AsteroidViewHolder(private val itemBinding: AsteroidItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(asteroid: Asteroid) {
            itemBinding.asteroid = asteroid
            itemBinding.executePendingBindings()
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Asteroid>() {
            override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean =
                oldItem == newItem

        }
    }
}