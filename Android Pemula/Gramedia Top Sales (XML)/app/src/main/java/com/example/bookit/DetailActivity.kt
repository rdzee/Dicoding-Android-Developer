package com.example.bookit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivPhoto = findViewById<ImageView>(R.id.iv_book)
        val tvTitle = findViewById<TextView>(R.id.tv_title)
        val tvAuthor = findViewById<TextView>(R.id.tv_author_value)
        val tvPublisher = findViewById<TextView>(R.id.tv_publisher_value)
        val tvPrice = findViewById<TextView>(R.id.tv_price_value)
        val tvPage = findViewById<TextView>(R.id.tv_page_value)
        val tvCategory = findViewById<TextView>(R.id.tv_category_value)
        val tvRank = findViewById<TextView>(R.id.tv_rank_value)
        val tvSold = findViewById<TextView>(R.id.tv_sold_value)
        val tvDescription = findViewById<TextView>(R.id.tv_description_value)

        @Suppress("DEPRECATION")
        val data = intent.getParcelableExtra<Book>("EXTRA_DATA")
        Log.d("Detail", data?.title.toString())

        if (data != null) {
            ivPhoto.setImageResource(data.photo)
            tvTitle.text = "${data.title}"
            tvAuthor.text = "${data.author}"
            tvPublisher.text = "${data.publisher}"
            tvPrice.text = "${data.price}"
            tvPage.text = "${data.page}"
            tvCategory.text = "${data.category}"
            tvRank.text = "${data.rank}"
            tvSold.text = "${data.sold}"
            tvDescription.text = "${data.description}"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hey, let's check the top Gramedia Book Sales")
                type = "text/plain"
            }

            val share = Intent.createChooser(shareIntent, null)
            startActivity(share)
        }

        return super.onOptionsItemSelected(item)
    }
}