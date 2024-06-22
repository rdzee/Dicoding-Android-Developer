package com.example.bookit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvBook: RecyclerView
    private val list = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Thread.sleep(500)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvBook = findViewById(R.id.rv_book)
        rvBook.setHasFixedSize(true)
        list.addAll(getListBook())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.profile_id) {
            val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(aboutIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListBook(): ArrayList<Book> {
        val dataTile = resources.getStringArray(R.array.item_title)
        val dataDescription = resources.getStringArray(R.array.item_description)
        val dataAuthor = resources.getStringArray(R.array.item_author)
        val dataPrice = resources.getStringArray(R.array.item_price)
        val dataCategory = resources.getStringArray(R.array.item_category)
        val dataPublisher = resources.getStringArray(R.array.item_publisher)
        val dataRank = resources.getStringArray(R.array.item_rank)
        val dataSold = resources.getStringArray(R.array.item_sold)
        val dataPage = resources.getStringArray(R.array.item_page)
        val dataPhoto = resources.obtainTypedArray(R.array.item_photo)

        val listBook = ArrayList<Book>()
        for (i in dataTile.indices) {
            val book = Book(
                dataPhoto.getResourceId(i, -1),
                dataTile[i],
                dataAuthor[i],
                dataDescription[i],
                dataPrice[i],
                dataCategory[i],
                dataPublisher[i],
                dataRank[i],
                dataSold[i],
                dataPage[i]
            )
            listBook.add(book)
        }

        return listBook
    }

    private fun showRecyclerList() {
        rvBook.layoutManager = LinearLayoutManager(this)
        val listBookAdapter = ListBookAdapter(list)
        rvBook.adapter = listBookAdapter

        listBookAdapter.setOnItemClickCallback(object : ListBookAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("EXTRA_DATA", data)
                startActivity(intentToDetail)
            }
        })
    }
}