package com.example.task11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task11.data.Image
import com.example.task11.databinding.FragmentImageDetailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ImageDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageDetailsFragment : Fragment() {
    private lateinit var binding: FragmentImageDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageId = arguments?.getInt(ARG_IMAGE_ID, 0) ?: 0

        val image = Image.getImageData().find { it.id == imageId }

        image?.let {
            binding.apply {
                ivImage.setImageResource(image.image)
                tvImageDescription.text = image.description
                tvImageTitle.text = image.title
            }
        }
    }

    companion object {
        private const val ARG_IMAGE_ID = "arg_image_id"

        fun newInstance(imageId: Int): ImageDetailsFragment {
            val fragment = ImageDetailsFragment()
            val args = Bundle()
            args.putInt(ARG_IMAGE_ID, imageId)
            fragment.arguments = args
            return fragment
        }
    }
}