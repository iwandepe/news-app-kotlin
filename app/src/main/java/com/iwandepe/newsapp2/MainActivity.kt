package com.iwandepe.newsapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var dataSet: MutableList<Article> = mutableListOf()
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewAdapter = RecyclerViewAdapter(dataSet, R.layout.recyclerview_row_item)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = recyclerViewAdapter

        val businessServices = DataServices.create()
        val call = businessServices.getArticlesList()
        call.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                println("On Failure")
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                println("On Response")
                if (response.body() != null) {
                    var data: NewsResponse = response.body()!!
                    dataSet.addAll(data!!.articles)
                    if (dataSet != null) {
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
                else {
                    println("response body null")
                }
            }
        })
    }
}