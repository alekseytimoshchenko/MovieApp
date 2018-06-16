package com.example.admin.thebestapp.base.mvp

abstract class PresenterBase<T: MvpView>: MvpPresenter<T>
{
    var view: T? = null
        private set
    
    protected val isViewAttached: Boolean
        get() = view != null
    
    override fun attachView(mvpView: T)
    {
        view = mvpView
    }
    
    override fun detachView()
    {
        view = null
    }
    
    override fun destroy()
    {
    
    }
}
