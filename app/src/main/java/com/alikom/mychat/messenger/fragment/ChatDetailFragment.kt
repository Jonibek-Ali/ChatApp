package com.alikom.mychat.messenger.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.alikom.mychat.R
import com.alikom.mychat.messenger.adapter.ChatDetailAdapter
import com.alikom.mychat.messenger.model.ChatDetailModel

class ChatDetailFragment : Fragment(R.layout.fragment_chat_detail),
    ChatDetailAdapter.ItemClickListener {

    val data = listOf(
        ChatDetailModel.FriendMessage(
            mesage = "Looking forward to the trip.",
            avatar = R.drawable.ic_bryin
        ),
        ChatDetailModel.MyMessage(
            "Same! Can’t wait."
        ),
        ChatDetailModel.FriendImageMessage(
            description = "Looking forward to the trip.",
            link = "https://stackoverflow.com/",
            avatar = R.drawable.ic_bryin,
            image = R.drawable.ic_conyon,
        ),
        ChatDetailModel.FriendMessage(
            mesage = "What do you think?",
            avatar = R.drawable.ic_bryin
        ),
        ChatDetailModel.MyMessage(
            "Oh yes this looks great!"
        ),
    )
    val chatDetailAdapter = ChatDetailAdapter(
        this@ChatDetailFragment
    ).apply {
        updateData(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appCompatActivity = requireActivity() as AppCompatActivity
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.title = "ToolBarFragment"

        view.findViewById<RecyclerView>(R.id.chat_detail_recyclerView).apply {

            adapter = chatDetailAdapter
        }

        setHasOptionsMenu(true)
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
                val searchItem = toolbar.menu.findItem(R.id.search)
                val searchView: SearchView = menu?.findItem(R.id.search)?.actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText.isNullOrEmpty()){
                            chatDetailAdapter.updateData(data)
                        }else{
                            val items = data.filter {
                                when (it) {
                                    is ChatDetailModel.FriendImageMessage -> it.description.lowercase()
                                        .contains(newText.lowercase())

                                    is ChatDetailModel.FriendMessage -> it.mesage.lowercase()
                                        .contains(newText.lowercase())

                                    is ChatDetailModel.MyMessage -> it.message.lowercase()
                                        .contains(newText.lowercase())
                                }
                            }
                            chatDetailAdapter.updateData(items)
                        }
                        return true
                    }
                })
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

    override fun myMessageClicked(item: ChatDetailModel.MyMessage, position: Int) {
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun friedMessageClicked(item: ChatDetailModel.FriendMessage, position: Int) {
        val profileScreenFragment = ProfileScreenFragment.newInstance(item.avatar)

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, profileScreenFragment)
            .addToBackStack(null)
            .commit()

    }

    override fun friedMessageClicked(item: ChatDetailModel.FriendImageMessage, position: Int) {
        val profileScreenFragment = ProfileScreenFragment.newInstance(item.avatar)

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, profileScreenFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun friedImageMessageClicked(item: ChatDetailModel.FriendImageMessage, position: Int) {
        val imageDetailFragment = ImageFullFragment.newInstance(item.image)

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, imageDetailFragment)
            .addToBackStack(null)
            .commit()
    }
}