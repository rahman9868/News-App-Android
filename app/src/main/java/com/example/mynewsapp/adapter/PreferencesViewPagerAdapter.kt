package com.example.mynewsapp.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.mynewsapp.R
import com.example.mynewsapp.entity.NewsHeadlines
import com.example.mynewsapp.view.DetailNewsActivity

class PreferencesViewPagerAdapter(var context: Context, var articleList: List<NewsHeadlines>): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return articleList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.item_pager_news, container, false)

        val image = view.findViewById<ImageView>(R.id.image_preference)
        val text = view.findViewById<TextView>(R.id.text_preferences)

        Glide.with(context)
            .load(articleList[position].urlToImage)
            .into(image)

        text.text = articleList[position].title

        view.setOnClickListener {
            showDetailNews(context, articleList[position])
        }

        container.addView(view)
        return view
    }

    private fun showDetailNews(context: Context,data: NewsHeadlines) {
        val intent = Intent(context, DetailNewsActivity::class.java)
        intent.putExtra(DetailNewsActivity.DETAIL_KEY,data)
        context.startActivity(intent)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


}