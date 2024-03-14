package com.alikom.mychat.messenger.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.alikom.mychat.R
import com.alikom.mychat.messenger.model.ChatDetailModel

class ChatDetailAdapter(
    val items: List<ChatDetailModel>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<BaseViewHolder<ChatDetailModel>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ChatDetailModel> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = when (viewType) {
            MY_MESSAGE -> MyMessageViewHolder(
                layoutInflater.inflate(
                    R.layout.chat_detail_my_message,
                    parent,
                    false
                )
            )

            FRIEND_IMAGE_MESSAGE -> FriedImageMessageViewHolder(
                layoutInflater.inflate(
                    R.layout.chat_detail_friend_image_message,
                    parent,
                    false
                )
            )

            else -> FriedMessageViewHolder(
                layoutInflater.inflate(
                    R.layout.chat_detail_friend_message,
                    parent,
                    false
                )
            )
        }
        viewHolder.listener(listener)
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<ChatDetailModel>, position: Int) =
        holder.bind(items[position])

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ChatDetailModel.MyMessage -> MY_MESSAGE
            is ChatDetailModel.FriendImageMessage -> FRIEND_IMAGE_MESSAGE
            is ChatDetailModel.FriendMessage -> FRIEND_MESSAGE
        }
    }

    private companion object {


        const val MY_MESSAGE = 0
        const val FRIEND_MESSAGE = 1
        const val FRIEND_IMAGE_MESSAGE = 2

    }

    interface ItemClickListener {

        fun myMessageClicked(item: ChatDetailModel.MyMessage, position: Int)

        fun friedMessageClicked(item: ChatDetailModel.FriendMessage, position: Int)

        fun friedMessageClicked(item: ChatDetailModel.FriendImageMessage, position: Int)

        fun friedImageMessageClicked(item: ChatDetailModel.FriendImageMessage, position: Int)

    }

}

abstract class BaseViewHolder<T>(v: View) : RecyclerView.ViewHolder(v) {

    protected var listener: ChatDetailAdapter.ItemClickListener? = null

    fun listener(listener: ChatDetailAdapter.ItemClickListener) {
        this.listener = listener
    }

    abstract fun bind(item: T)

    fun <T : View> findViewById(@IdRes id: Int): T = itemView.findViewById(id)

}

class MyMessageViewHolder(v: View) : BaseViewHolder<ChatDetailModel>(v) {

    private val textView: TextView = findViewById(R.id.textView)
    private val myMessageCardView: CardView = findViewById(R.id.myMessageCardView)

    override fun bind(item: ChatDetailModel) {
        if (item is ChatDetailModel.MyMessage) {
            textView.text = item.message
            myMessageCardView.setOnClickListener {
                listener?.myMessageClicked(item, adapterPosition)
            }
        }
    }
}

class FriedMessageViewHolder(v: View) : BaseViewHolder<ChatDetailModel>(v) {

    private val textView: TextView = findViewById(R.id.textView)
    private val avatar: ImageView = findViewById(R.id.imageView)
    private val friedCardView: CardView = findViewById(R.id.friedCardView)

    override fun bind(item: ChatDetailModel) {
        if (item is ChatDetailModel.FriendMessage) {
            textView.text = item.mesage
            avatar.setImageResource(item.avatar)
            avatar.setOnClickListener {
                listener?.friedMessageClicked(item, adapterPosition)
            }
            friedCardView.setOnClickListener {
                listener?.friedMessageClicked(item, adapterPosition)
            }
        }
    }
}

class FriedImageMessageViewHolder(v: View) : BaseViewHolder<ChatDetailModel>(v) {

    private val description: TextView = findViewById(R.id.descriptionTexView)
    private val linearLayout: LinearLayout = findViewById(R.id.linearLayout)
    private val avatar: ImageView = findViewById(R.id.avatarImageView)
    private val imageView: ImageView = findViewById(R.id.imageView)
    private val link: TextView = findViewById(R.id.linkTextView)

    init {
        link.setOnClickListener {
            // Открываем ссылку в браузере
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link.text.toString()))
            v.context.startActivity(intent)
        }
    }
    override fun bind(item: ChatDetailModel) {
        if (item is ChatDetailModel.FriendImageMessage) {
            avatar.setImageResource(item.avatar)
            avatar.setOnClickListener {
                listener?.friedMessageClicked(item, adapterPosition)
            }

            imageView.setImageResource(item.image)
            description.text = item.description
            link.text = item.link


            imageView.setOnClickListener {
                listener?.friedImageMessageClicked(item, adapterPosition)
            }


        }
    }

}