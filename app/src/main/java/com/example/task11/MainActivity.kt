package com.example.task11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.task11.adapter.ImageAdapter
import com.example.task11.data.Image
import com.example.task11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        adapterSetup()
    }

    private fun adapterSetup() {
        adapter = ImageAdapter()
        adapter.setOnImageClickListener { image ->
            // Open ImageDetailFragment
            val detailFragment = ImageDetailsFragment.newInstance(image.id)
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, detailFragment)
                .addToBackStack(null)
                .commit()
        }
        adapter.differ.submitList(Image.getImageData())
        binding.rvImages.adapter = adapter
    }
}