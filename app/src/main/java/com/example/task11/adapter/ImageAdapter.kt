package com.example.task11.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task11.data.Image
import com.example.task11.databinding.ImageItemBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var onImageClickListener: ((Image) -> Unit)? = null
//
//    fun filter(query: String) {
//        val filteredList = differ.currentList.filter {
//            it.title.contains(query, true) || it.description.contains(query, true)
//        }
//        differ.submitList(filteredList)
//    }

    fun setOnImageClickListener(listener: (Image) -> Unit) {
        onImageClickListener = listener
    }

    inner class ImageViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.imageView.setImageResource(image.image)
            binding.imageView.setOnClickListener {
                onImageClickListener?.invoke(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)
}