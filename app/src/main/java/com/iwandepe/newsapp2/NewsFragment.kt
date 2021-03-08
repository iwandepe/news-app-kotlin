package com.iwandepe.newsapp2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.iwandepe.newsapp2.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    var dataSet: MutableList<Article> = mutableListOf()
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var newsCategory: String
    val args: NewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNewsBinding> (inflater, R.layout.fragment_news, container, false)

        recyclerViewAdapter = RecyclerViewAdapter(dataSet, R.layout.recyclerview_row_item)
        recyclerView = binding.recyclerView
        recyclerView.adapter = recyclerViewAdapter

        newsCategory = args.category
        var progressBar = binding.progressBarNews

        val businessServices = DataServices.create()
        if (isOnline(requireContext())) {
            val call = businessServices.getArticlesList(newsCategory)
            println("Hello news fragtment, going to call")
            call.enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    println("On Failure")
                }

                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    progressBar.visibility = View.GONE
                    println("On Response")
                    if (response.body() != null) {
                        var data: NewsResponse = response.body()!!
                        dataSet.addAll(data!!.articles)
                        println(dataSet)
                        if (dataSet != null) {
                            println("Dataset isnt null")
                            recyclerViewAdapter.notifyDataSetChanged()
                        }
                    } else {
                        progressBar.visibility = View.GONE
                        println("response body null")
                    }
                }
            })
        } else {
            progressBar.visibility = View.GONE
            Toast.makeText(activity, "Tidak ada koneksi internet!", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
}