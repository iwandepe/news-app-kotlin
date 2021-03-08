package com.iwandepe.newsapp2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iwandepe.newsapp2.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    var dataSet: MutableList<Article> = mutableListOf()
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNewsBinding> (inflater, R.layout.fragment_news, container, false)

        recyclerViewAdapter = RecyclerViewAdapter(dataSet, R.layout.recyclerview_row_item)
        recyclerView = binding.recyclerView
        recyclerView.adapter = recyclerViewAdapter

        val businessServices = DataServices.create()
        val call = businessServices.getArticlesList()
        println("Hello news fragtment, going to call")
        call.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                println("On Failure")
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                println("On Response")
                if (response.body() != null) {
                    var data: NewsResponse = response.body()!!
                    dataSet.addAll(data!!.articles)
                    println(dataSet)
                    if (dataSet != null) {
                        println("Dataset isnt null")
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
                else {
                    println("response body null")
                }
            }
        })

        return binding.root
    }
}