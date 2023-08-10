package com.example.carmarketsuftit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carmarketsuftit.databinding.ItemCarBinding
import com.example.carmarketsuftit.model.Car

class CarsAdapter : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    private lateinit var binding: ItemCarBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(car.image).centerCrop().into(binding.ivCarImage)
            binding.tvCarName.text = car.name
            binding.tvCarYear.text = car.creationYear.toString()
            binding.tvPrice.text = car.price.toString()
            binding.tvEngineCapacity.text = car.price.toString()
            binding.addedTime.text = car.publicationDate
            setOnClickListener {
                onItemClickListener?.let { it(car) }
            }
        }
    }

    private var onItemClickListener: ((Car) -> Unit)? = null

    fun setOnItemClickListener(listener: (Car) -> Unit) {
        onItemClickListener = listener
    }
}