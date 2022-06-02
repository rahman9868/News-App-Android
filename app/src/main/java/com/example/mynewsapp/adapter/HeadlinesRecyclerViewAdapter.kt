package com.example.mynewsapp.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.example.mynewsapp.util.NewsUtil
import com.example.mynewsapp.view.DetailNewsActivity

class HeadlinesRecyclerViewAdapter() : RecyclerView.Adapter<HeadlinesRecyclerViewAdapter.HeadlinesViewHolder>(){

    val mData = mutableListOf<NewsHeadlines>()

    fun setData(data : List<NewsHeadlines>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_top_headlines, parent, false)
        return HeadlinesViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {

        holder.bind(mData[position])
    }

    class HeadlinesViewHolder(itemView: View) : ViewHolder(itemView) {

        val image : ImageView = itemView.findViewById(R.id.image_top_headlines)
        val text : TextView = itemView.findViewById(R.id.title_top_headlines)
        val date : TextView = itemView.findViewById(R.id.date_top_headlines)
        val item : View = itemView

        fun bind(data: NewsHeadlines){
            with(itemView){

                when {
                    data.title != null -> text.text = data.title
                    data.description != null -> text.text = data.description
                    data.content != null ->text.text = data.content
                }
                date.text = NewsUtil.convertStringDateToOtherFormat(data.publishedAt)
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
            intent.putExtra(DetailNewsActivity.DETAIL_KEY,data)
            context.startActivity(intent)
        }

    }

}

