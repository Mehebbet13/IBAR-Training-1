package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

//        getRequestWithPathParameters()
        getRequestWithQueryParameters()
        uploadAlbum()
    }
    private fun getRequestWithQueryParameters(){
        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList!=null){
                while (albumList.hasNext()){
                    val albumItem = albumList.next()
                    Log.e("mytag",albumItem.title)
                    val result = " " + "Album : ${albumItem.title}"
                    text_view.text = result

                }
            }
        })
    }

    private fun getRequestWithPathParameters(){
        val pathResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Log.e("mytag title",title.toString())
        })

    }
    private fun uploadAlbum(){
        val album = AlbumItem(0,"title",3)
        val postResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }
        postResponse.observe(this, Observer {
            val receivedAlbums = it.body()
            Log.e("mytag title",receivedAlbums?.title.toString())

        })
    }
}