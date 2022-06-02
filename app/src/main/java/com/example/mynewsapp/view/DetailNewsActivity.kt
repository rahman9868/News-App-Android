package com.example.mynewsapp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mynewsapp.R
import com.example.mynewsapp.entity.NewsHeadlines
import com.example.mynewsapp.util.NewsUtil
import com.example.mynewsapp.util.RoomUtil
import kotlinx.android.synthetic.main.activity_detail_news.*

class DetailNewsActivity : AppCompatActivity() {
    var news : NewsHeadlines? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        getDetailNews()
        setDetailNews()
        handleShare()
        checkNewsIsBookmark()
        fab_news_full_bookmark_border.setOnClickListener { addBookmark() }
        news_full_fab_bookmark_filled.setOnClickListener { removeBookmark() }
    }

    override fun onResume() {
        checkNewsIsBookmark()
        super.onResume()
    }

    private fun checkNewsIsBookmark() {
        val newsLocal = RoomUtil.getBookmarkByUrl(this, news?.url ?:"")

        if(newsLocal.isNullOrEmpty()){
            fab_news_full_bookmark_border.visibility = View.VISIBLE
            news_full_fab_bookmark_filled.visibility = View.GONE
        }else{
            fab_news_full_bookmark_border.visibility = View.GONE
            news_full_fab_bookmark_filled.visibility = View.VISIBLE
        }
    }

    private fun handleShare() {
        news_full_fab_share.setOnClickListener {
            shareNews()
        }
        fullNews.setOnClickListener {
            showFullNews()
        }
    }

    private fun getDetailNews() {
        news = intent.getParcelableExtra(DETAIL_KEY)
    }

    private fun setDetailNews() {
        news_full_headline_text.text = news?.title
        news_full_description_text.text = news?.description
        news_full_content_text.text = news?.content
        news_full_date_text.text = NewsUtil.convertStringDateToOtherFormat(news?.publishedAt)

        Glide.with(this)
            .load(news?.urlToImage)
            .placeholder(R.drawable.index)
            .into(news_full_image)

        Glide.with(this)
            .load(news?.urlToImage)
            .placeholder(R.drawable.index)
            .into(bottomImage)

        Log.v("ADX","Data : "+ news?.url + " urlToImage "+news?.urlToImage)
    }

    private fun shareNews(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, "*${news?.title}*\n\n" +
                        "${news?.description}\n\n" +
                        "To read full news visit:\n${news?.url}\n\n"
            )
            type = "text/simple"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun addBookmark() {
        if(news == null) return
        Toast.makeText(this, getString(R.string.add_bookmark), Toast.LENGTH_SHORT).show()
        fab_news_full_bookmark_border.visibility = View.GONE
        news_full_fab_bookmark_filled.visibility = View.VISIBLE
        RoomUtil.insertBookmark(this,news!!.convertToBookmark())
    }

    private fun removeBookmark() {
        if(news == null) return
        Toast.makeText(this, getString(R.string.remove_bookmark), Toast.LENGTH_SHORT).show()
        fab_news_full_bookmark_border.visibility = View.VISIBLE
        news_full_fab_bookmark_filled.visibility = View.GONE
        RoomUtil.removeBookmark(this,news?.url ?:"")
    }

    private fun showFullNews(){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news?.url))
        startActivity(browserIntent)
    }

    companion object{
        val DETAIL_KEY = "Detail Key"
    }
}