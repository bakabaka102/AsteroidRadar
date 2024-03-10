package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding
import com.udacity.asteroidradar.db.AsteroidEntity

class AsteroidAdapter : /*RecyclerView.Adapter*/
    ListAdapter<AsteroidEntity, AsteroidAdapter.AsteroidHolder>(AsteroidDiffCallBack()) {

    var onItemClick: ((AsteroidEntity) -> Unit)? = null

    inner class AsteroidHolder(
        private val item: ItemAsteroidBinding
    ) : RecyclerView.ViewHolder(item.root) {

        fun bindDataToView(asteroid: AsteroidEntity) {
            item.asteroid = asteroid
            item.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidHolder {
        val withDataBinding = ItemAsteroidBinding.inflate(LayoutInflater.from(parent.context))
        return AsteroidHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AsteroidHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(asteroid)
        }
        holder.bindDataToView(asteroid)
    }
}

class AsteroidDiffCallBack : DiffUtil.ItemCallback<AsteroidEntity>() {
    override fun areItemsTheSame(oldItem: AsteroidEntity, newItem: AsteroidEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AsteroidEntity, newItem: AsteroidEntity): Boolean {
        return oldItem == newItem
    }

}