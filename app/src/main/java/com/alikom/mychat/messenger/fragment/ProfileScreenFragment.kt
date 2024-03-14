package com.alikom.mychat.messenger.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.alikom.mychat.R

class ProfileScreenFragment : Fragment(R.layout.fragment_profile_screen) {

    companion object {
        private const val ARG_AVATAR_RES_ID = "avatarResId"

        fun newInstance(avatarResId: Int): ProfileScreenFragment {
            val fragment = ProfileScreenFragment()
            val args = Bundle()
            args.putInt(ARG_AVATAR_RES_ID, avatarResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val avatarResId = arguments?.getInt(ARG_AVATAR_RES_ID) ?: 0

        view.findViewById<ImageView>(R.id.avatarImageView).apply {
            setImageResource(avatarResId)
            setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }
}