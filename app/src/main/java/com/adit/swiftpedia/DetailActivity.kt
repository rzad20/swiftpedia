package com.adit.swiftpedia
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var albumName : TextView
    private lateinit var releaseDate : TextView
    private lateinit var description : TextView
    private lateinit var listSong : TextView
    private lateinit var albumImage : ImageView

    companion object {
        const val ALBUM_NAME = "album_name"
        const val RELEASE_DATE = "release_date"
        const val DESCRIPTION = "description"
        const val IMAGE_FILE = "image"
        const val SONG_LIST = "song_list"
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
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.action_share -> {
                shareAlbum()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail)

        albumImage = findViewById(R.id.imageView)
        albumName = findViewById(R.id.albumName)
        releaseDate = findViewById(R.id.albumRelease)
        description = findViewById(R.id.detailDescription)
        listSong = findViewById(R.id.detailSong)
        val albumNameText = intent.getStringExtra(ALBUM_NAME)
        val releaseDateText = intent.getStringExtra(RELEASE_DATE)
        val descriptionText = intent.getStringExtra(DESCRIPTION)
        val imageResource = intent.getIntExtra(IMAGE_FILE, 0)
        val listSongArray = intent.getStringArrayExtra(SONG_LIST)

        albumName.text = albumNameText
        releaseDate.text = releaseDateText
        description.text = descriptionText
        albumImage.setImageResource(imageResource)
        listSong.text = listSongArray?.joinToString("\n")
        val btnShare:Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)
    }
    private fun shareAlbum(){
        val albumName = albumName.text.toString()
        val release = releaseDate.text.toString()
        val desc = description.text.toString()
        val listSong = listSong.text.toString()

        val shareScript = "Album Name : $albumName\n" +
                "Release Date : $release\n" +
                "Album Description : $desc\n" +
                "List Song :  $listSong"
        // Buat intent untuk mengirim pesan ke WhatsApp
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, shareScript)
        intent.setPackage("com.whatsapp")
        startActivity(intent)
    }
}
