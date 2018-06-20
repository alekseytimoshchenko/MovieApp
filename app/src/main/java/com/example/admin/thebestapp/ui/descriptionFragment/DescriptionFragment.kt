package com.example.admin.thebestapp.ui.descriptionFragment

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.ui.MovieActivity
import com.example.admin.thebestapp.utils.Constants
import com.example.admin.thebestapp.utils.ui.binding.DataBindingAdapter
import kotlinx.android.synthetic.main.frag_description.*

class DescriptionFragment: Fragment()
{
    companion object
    {
        var frag_tag: String = ""
            private set
            get() = DescriptionFragment::class.java.simpleName.toString()
        
        fun newInstance(iItem: MovieObject?): DescriptionFragment //
                = DescriptionFragment() //
                .apply {
                    val args = Bundle()
                    iItem?.let { args.putParcelable(Constants.MOVIE_OBJ, it) }
                    arguments = args
                }
    }
    
    private var isPortrait: Boolean = false
    
    private var movieObject: MovieObject? = null
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        return inflater.inflate(R.layout.frag_description, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        
        val bundle = arguments
        movieObject = bundle?.getParcelable(Constants.MOVIE_OBJ)
        setUi()
    }
    
    override fun onDestroyView()
    {
        super.onDestroyView()
        (activity as? MovieActivity)?.showBackButton(false)
        (activity as? MovieActivity)?.setTitleText(getString(R.string.pop_movies))
    }
    
    fun setMovie(iItem: MovieObject)
    {
        movieObject = iItem
        setUi()
    }
    
    private fun setUi()
    {
        movieObject?.let {
            tv_frag_description_title?.text = it.title
            iv_frag_description_poster?.let {
                DataBindingAdapter.setImageUri(iv_frag_description_poster, "https://image.tmdb.org/t/p/w500/${movieObject!!.poster_path}")
            }
            tv_frag_description_date?.text = it.release_date
            tv_frag_description_rate?.text = it.vote_average.toString()
            tv_frag_description_description?.text = it.overview_content
        }
    }
}
