package com.example.admin.thebestapp.ui.movie.movieFragment.mvp;

import com.example.admin.thebestapp.base.mvp.MvpPresenter;
import com.example.admin.thebestapp.base.mvp.MvpView;

public interface MovieContract
{
	interface View extends MvpView
	{
		void setContent();
	}
	
	interface Presenter extends MvpPresenter<View>
	{
		void onItemClick();
	}
}
