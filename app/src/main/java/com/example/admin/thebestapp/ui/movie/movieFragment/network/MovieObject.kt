package com.example.admin.thebestapp.ui.movie.movieFragment.network

import android.os.Parcel
import android.os.Parcelable
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.DiffInterface
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieObject(@SerializedName("vote_count") @Expose var vote_count: Int = 0, //
                       @SerializedName("id") @Expose var movie_id: Long = 0, //
                       @SerializedName("vote_average") @Expose var vote_average: Double = 0.toDouble(), //
                       @SerializedName("title") @Expose var title: String = "", //
                       @SerializedName("poster_path") @Expose var poster_path: String = "", //
                       @SerializedName("overview") @Expose var overview_content: String = "", //
                       @SerializedName("release_date") @Expose var release_date: String = ""): DiffInterface, Parcelable
{
    constructor(parcel: Parcel): //
            this( //
                    parcel.readInt(), //
                    parcel.readLong(), //
                    parcel.readDouble(), //
                    parcel.readString(), //
                    parcel.readString(), //
                    parcel.readString(), //
                    parcel.readString() //
            )
    
    override fun getId(): Long
    {
        return movie_id
    }
    
    override fun getName(): String
    {
        return title
    }
    
    override fun getOverview(): String
    {
        return overview_content
    }
    
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(vote_count)
        parcel.writeLong(movie_id)
        parcel.writeDouble(vote_average)
        parcel.writeString(title)
        parcel.writeString(poster_path)
        parcel.writeString(overview_content)
        parcel.writeString(release_date)
    }
    
    override fun describeContents(): Int
    {
        return 0
    }
    
    companion object CREATOR: Parcelable.Creator<MovieObject>
    {
        override fun createFromParcel(parcel: Parcel): MovieObject
        {
            return MovieObject(parcel)
        }
        
        override fun newArray(size: Int): Array<MovieObject?>
        {
            return arrayOfNulls(size)
        }
    }
}