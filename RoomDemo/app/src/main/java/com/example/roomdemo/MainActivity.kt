package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberDatabase
import com.example.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    subscriberViewModel.message.observe(this, Observer {
        it.getContentIfNotHandled()?.let {
            Log.e("mytag t",it)

        }
    }
    )
    }

    private fun displaySubscribersList(){
        subscriberViewModel.subscriber.observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun initRecyclerView(){
        binding.recycler.layoutManager  =LinearLayoutManager(this)
        adapter= MyRecyclerViewAdapter({ selectedItem:Subscriber->listItemClicked(selectedItem)})
        binding.recycler.adapter = adapter

        displaySubscribersList()
    }

    private fun listItemClicked(subscriber: Subscriber){
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}