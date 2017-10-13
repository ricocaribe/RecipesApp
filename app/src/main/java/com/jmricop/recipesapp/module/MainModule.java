package com.jmricop.recipesapp.module;


import com.jmricop.recipesapp.interactor.MainViewInteractor;
import com.jmricop.recipesapp.presenter.MainViewPresenter;
import com.jmricop.recipesapp.view.activities.MainActivity;

import dagger.Module;
import dagger.Provides;


@Module(injects = {MainActivity.class})
public class MainModule {

    @Provides
    MainViewInteractor.MainPresenter provideMainViewPresenter(){
        return new MainViewPresenter();
    }
}
