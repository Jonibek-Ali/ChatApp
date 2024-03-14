package com.alikom.mychat.messenger.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.alikom.mychat.R

class ImageFullFragment : Fragment(R.layout.fragment_image_detail) {

    companion object {
        private const val ARG_IMAGE_RES_ID = "imageResId"

        fun newInstance(imageResId: Int): ImageFullFragment {
            val fragment = ImageFullFragment()
            val args = Bundle()
            args.putInt(ARG_IMAGE_RES_ID, imageResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageResId = arguments?.getInt(ARG_IMAGE_RES_ID) ?: 0

        view.findViewById<ImageView>(R.id.imageView).apply {
            setImageResource(imageResId)
            setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }
}