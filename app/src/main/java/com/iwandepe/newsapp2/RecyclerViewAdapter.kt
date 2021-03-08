package com.iwandepe.newsapp2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewAdapter (
    private val dataSet: MutableList<Article>,
    private val rowLayout: Int
):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val content: TextView
        val author: TextView
        val image: ImageView
        val rowItem: ConstraintLayout

        init {
            println("ini ViewHolder")
            title = view.findViewById(R.id.title)
            content = view.findViewById(R.id.content)
            author = view.findViewById(R.id.author)
            image = view.findViewById(R.id.image)
            rowItem = view.findViewById(R.id.row_item)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(rowLayout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.title.text = dataSet[position].title
        viewHolder.content.text = dataSet[position].content
        viewHolder.author.text = dataSet[position].author
        Picasso.get()
            .load(dataSet[position].urlToImage)
            .resize(350, 350)
            .centerCrop()
            .into(viewHolder.image)
        viewHolder.rowItem.setOnClickListener {v: View ->
            val url = dataSet[position].url
            val action = NewsFragmentDirections.actionNewsFragmentToWebViewFragment(url)
            v.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = dataSet.size

}