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
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val content: TextView
        val author: TextView
        val image: ImageView
        val rowItem: ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View.
            println("ini ViewHolder")
            title = view.findViewById(R.id.title)
            content = view.findViewById(R.id.content)
            author = view.findViewById(R.id.author)
            image = view.findViewById(R.id.image)
            rowItem = view.findViewById(R.id.row_item)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(rowLayout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
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

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}