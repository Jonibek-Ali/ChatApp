package com.alikom.mychat.messenger

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.alikom.mychat.R

class ToolBarFragment: Fragment(R.layout.fragment_toolbar) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appCompatActivity = requireActivity() as AppCompatActivity
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.title = "ToolBarFragment"
        setHasOptionsMenu(true)
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return  when (menuItem.itemId) {

                    android.R.id.home -> {
                        requireActivity().onBackPressed()
                        true
                    }

                    R.id.call -> {
                        Toast.makeText(requireContext(), "Call", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.search -> {
                        Toast.makeText(requireContext(), "Search", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.settings -> {
                        Toast.makeText(requireContext(), "Settings", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.STARTED)
    }
}