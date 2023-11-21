package com.example.task11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
        searchViewSetup()
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
        adapter.submitList(Image.getImageData())
        binding.rvImages.adapter = adapter
    }

    private fun searchViewSetup() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Called when the user submits the query
                d("onQueryTextSubmit", query ?: "empty")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Called when the text in the search view changes
                // You can use 'newText' to filter your data and update the adapter
                d("onQueryTextChange", newText ?: "empty")
                adapter.filter(newText.orEmpty())
                return true
            }
        })
    }
}