package com.example.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.databinding.ListItemBinding
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.generated.callback.OnClickListener

class MyRecyclerViewAdapter(private val clickListener: (Subscriber) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    private val subscribers = ArrayList<Subscriber>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ListItemBinding>(
                layoutInflater,
                R.layout.list_item,
                parent,
                false
            )
        return MyViewHolder((binding))
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribers[position], clickListener)
    }

    fun setList(subscribersList: List<Subscriber>) {
        subscribers.clear()
        subscribers.addAll(subscribersList)
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        binding.textView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItem.setOnClickListener {
            clickListener(subscriber)
        }
    }
}