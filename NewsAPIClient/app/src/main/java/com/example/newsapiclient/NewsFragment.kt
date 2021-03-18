package com.example.newsapiclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiclient.databinding.FragmentNewsBinding
import com.example.newsapiclient.presentation.adapter.NewsAdapter
import com.example.newsapiclient.presentation.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private var country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner, { response ->
            when (response) {
                is com.example.newsapiclient.data.util.Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                    }
                }
                is com.example.newsapiclient.data.util.Resource.Loading -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "error:$it", Toast.LENGTH_LONG).show()
                    }
                }
                is com.example.newsapiclient.data.util.Resource.Error -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.adapter = newsAdapter
        fragmentNewsBinding.rvNews.layoutManager = LinearLayoutManager(activity)
    }

    private fun showProgressBar() {
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }
}