package com.example.itunes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RepoListAdapter(private var repoList: ArrayList<Results>)
    :RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(repoList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val trackName: TextView = itemView.findViewById(R.id.track_name)
        private val artistName: TextView = itemView.findViewById(R.id.artist_name)
        private val genre: TextView = itemView.findViewById(R.id.genre)

        private val imageViewCover: ImageView = itemView.findViewById(R.id.image_view_cover)
        fun bindRepo(item: Results) {
            artistName.text = item.artistName + "\n"
//            textView.append(item.collectionArtistViewUrl + "\n")
//            textView.append(item.kind + "\n")
//            textView.append(item.collectionCensoredName+"\n")
            genre.text = item.primaryGenreName + "\n"
            //textView.append(item.country+"\n")
            trackName.text = item.trackName + "\n"

            Glide.with(itemView)  //2
                .load(item.artworkUrl100) //3
                .into(imageViewCover) //8
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }
}
