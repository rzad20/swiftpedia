package com.adit.swiftpedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAlbums : RecyclerView
    private val list = ArrayList<Album>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvAlbums = findViewById(R.id.rv_swift)
        rvAlbums.setHasFixedSize(true)
        list.addAll(getListAlbums())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this,AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getListAlbums(): ArrayList<Album> {
        val albumName = resources.getStringArray(R.array.album_names)
        val releaseDate = resources.getStringArray(R.array.release_date)
        val dataDescription = resources.getStringArray(R.array.short_description)
        val dataPhoto = resources.obtainTypedArray(R.array.album_photo)
        val listAlbums = ArrayList<Album>()
        for (i in albumName.indices) {
            val album = Album(albumName[i], releaseDate[i], dataDescription[i], dataPhoto.getResourceId(i,-1))
            listAlbums.add(album)
        }
        return listAlbums
    }

    private fun showRecyclerList(){
        rvAlbums.layoutManager = LinearLayoutManager(this)
        val albumAdapter = AlbumAdapter(list)
        rvAlbums.adapter = albumAdapter

        albumAdapter.setOnItemClickCallback(object: AlbumAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Album) {
                showSelectedAlbum(data)
            }
        })
    }
    private fun showSelectedAlbum(album : Album) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.ALBUM_NAME, album.albumName)
            putExtra(DetailActivity.RELEASE_DATE, album.releaseDate)
            putExtra(DetailActivity.DESCRIPTION, album.description)
            putExtra(DetailActivity.IMAGE_FILE, album.photo)

            val songListId = when (album.albumName) {
                getString(R.string.debut_album) -> R.array.debut_album_song
                getString(R.string.fearless_album) -> R.array.fearless_album_song
                getString(R.string.speak_now_album) -> R.array.speak_now_song
                getString(R.string.red_album) -> R.array.red_song
                getString(R.string.the1989_album) -> R.array.the1989_song
                getString(R.string.reputation_album) -> R.array.reputation_song
                getString(R.string.lover_album) -> R.array.lover_song
                getString(R.string.folklore_album) -> R.array.folklore_song
                getString(R.string.evermore_album) -> R.array.evermore_song
                getString(R.string.midnight_album) -> R.array.midnight_song
                getString(R.string.ttpd_album) -> R.array.ttpd_song
                else -> R.array.empty_song_array
            }
            val songs = resources.getStringArray(songListId)
            putExtra(DetailActivity.SONG_LIST, songs)
        }
        startActivity(intent)
    }
}