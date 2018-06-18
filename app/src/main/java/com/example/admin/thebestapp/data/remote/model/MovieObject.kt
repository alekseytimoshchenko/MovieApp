package com.example.admin.thebestapp.data.remote.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movieobject")
data class MovieObject( //
        @SerializedName("key_id") @PrimaryKey @Expose @ColumnInfo(name = "id") var id: Int, //
        @SerializedName("vote_count") @Expose @ColumnInfo(name = "vote_count") var vote_count: Int = 0, //
        @SerializedName("id") @Expose @ColumnInfo(name = "movie_id") var movie_id: Long = 0, //
        @SerializedName("vote_average") @Expose @ColumnInfo(name = "vote_average") var vote_average: Double = 0.toDouble(), //
        @SerializedName("title") @Expose @ColumnInfo(name = "title") var title: String = "", //
        @SerializedName("poster_path") @Expose @ColumnInfo(name = "poster_path") var poster_path: String = "", //
        @SerializedName("overview") @Expose @ColumnInfo(name = "overview_content") var overview_content: String = "", //
        @SerializedName("release_date") @Expose @ColumnInfo(name = "release_date") var release_date: String = ""): Parcelable
{
    @Ignore constructor(parcel: Parcel): this( //
            parcel.readInt(), //
            parcel.readInt(), //
            parcel.readLong(), //
            parcel.readDouble(), //
            parcel.readString(), //
            parcel.readString(), //
            parcel.readString(), //
            parcel.readString() //
    )
    
    @Ignore
    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(id)
        parcel.writeInt(vote_count)
        parcel.writeLong(movie_id)
        parcel.writeDouble(vote_average)
        parcel.writeString(title)
        parcel.writeString(poster_path)
        parcel.writeString(overview_content)
        parcel.writeString(release_date)
    }
    
    @Ignore
    override fun describeContents(): Int = 0
    
    companion object CREATOR: Parcelable.Creator<MovieObject>
    {
        override fun createFromParcel(parcel: Parcel): MovieObject = MovieObject(parcel)
        
        override fun newArray(size: Int): Array<MovieObject?> = arrayOfNulls(size)
    }
}