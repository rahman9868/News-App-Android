package com.example.mynewsapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.mynewsapp.R
import com.example.mynewsapp.entity.NewsHeadlines
import com.example.mynewsapp.view.DetailNewsActivity

class TopStoriesHomeRecyclerViewAdapter() : RecyclerView.Adapter<TopStoriesHomeRecyclerViewAdapter.TopStoriesHomeViewHolder>(){

    val mData = mutableListOf<NewsHeadlines>()

    fun setData(data : List<NewsHeadlines>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesHomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_round_top_headlines, parent, false)
        return TopStoriesHomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.v("ADX","Mdata size ${mData.size}")
        return mData.size
    }

    override fun onBindViewHolder(holder: TopStoriesHomeViewHolder, position: Int) {

        holder.bind(mData[position])

    }

    class TopStoriesHomeViewHolder(itemView: View) : ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.image_view_top_headlines_round)
        val text : TextView = itemView.findViewById(R.id.text_view_top_headlines_round)

        fun bind(data: NewsHeadlines) {
            with(itemView){
                when {
                    data.name != null -> text.text = data.name
                    data.author != null -> text.text = data.author
                    data.id != null -> text.text = data.id
                    else -> text.text = data.title
                    }

                Glide.with(context)
                    .load(data.urlToImage)
                    .placeholder(R.drawable.index)
                    .into(image)

                this.setOnClickListener {
                    showDetailNews(context,data)
                }
                }

            }

        private fun showDetailNews(context: Context,data: NewsHeadlines) {
            val intent = Intent(context, DetailNewsActivity::class.java)
            intent.putExtra(DetailNewsActivity.DETAIL_KEY, data)
            context.startActivity(intent)
        }
    }

    }

