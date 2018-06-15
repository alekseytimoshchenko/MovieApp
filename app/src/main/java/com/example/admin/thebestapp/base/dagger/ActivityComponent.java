package com.example.admin.thebestapp.base.dagger;

public interface ActivityComponent<A> {
    void inject(A activity);
}
