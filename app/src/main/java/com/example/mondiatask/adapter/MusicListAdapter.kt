package com.example.mondiatask.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.mondiatask.R
import com.example.mondiatask.model.MusicModel
import com.example.mondiatask.view.MusicDetailActiovity
import java.net.URL


class MusicListAdapter(var musics: List<MusicModel?>?, var context: Context) : BaseAdapter(){




    override fun getCount(): Int {
        return  musics!!.size
    }

    override fun getItem(musicIndex: Int): Any {
       return musics!![musicIndex]!!
    }

    override fun getItemId(musicIndex: Int): Long {
      return musicIndex.toLong()!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
         val inflater: LayoutInflater
                = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.music_item, parent, false)
        val musicContainer: ViewGroup= rowView.findViewById(R.id.container)
        val musicImageView: ImageView = rowView.findViewById(R.id.img)
        val musicTitle: TextView = rowView.findViewById(R.id.music_title)
        val musicType: TextView = rowView.findViewById(R.id.music_type)
        val musicArtist: TextView = rowView.findViewById(R.id.music_artist)
        val loadingProgressBar: ProgressBar = rowView.findViewById(R.id.loading_img)
        musicTitle.text = "title is : "+musics!![position]?.musicTitle
        musicType.text = "type is a : "+ musics!![position]?.musicType
        musicArtist.text = "artist is : " +musics!![position]?.musicArtistName


        DownloadImageTask(musicImageView ,loadingProgressBar ).execute("https:"+musics!![position]?.musicImg)
        musicContainer.setOnClickListener(View.OnClickListener {
            // go to music detail
            var intent = Intent(context , MusicDetailActiovity::class.java)
            intent.putExtra("object" , musics!![position])
            context.startActivity(intent)
        })
        return rowView
    }



    //async task class to download image in imageview
    public class DownloadImageTask(var bmImage: ImageView , var progressBar : ProgressBar) :
        AsyncTask<String?, Void?, Bitmap?>() {


        override fun onPreExecute() {
            progressBar.visibility = View.VISIBLE
            bmImage.visibility = View.INVISIBLE
        }
        override fun onPostExecute(result: Bitmap?) {
            bmImage.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
            bmImage.setImageBitmap(result)
        }

        override fun doInBackground(vararg p0: String?): Bitmap? {
            val urldisplay = p0[0]
            var mIcon11: Bitmap? = null
            try {
                val `in` = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message!!)
                e.printStackTrace()
            }
            return mIcon11
        }
    }


}

