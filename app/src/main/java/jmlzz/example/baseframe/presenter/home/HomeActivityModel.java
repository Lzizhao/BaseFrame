package jmlzz.example.baseframe.presenter.home;

import dagger.Module;
import dagger.Provides;
import jmlzz.example.baseframe.ui.activity.HomeActivity;

/**
 * Created by robert.luozizhao on 2018/2/28.
 */

@Module
public class HomeActivityModel {

    private HomeActivity homeActivity;

    public HomeActivityModel(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    public HomeActivity provideHomeActivity(){
        return homeActivity;
    }

    @Provides
    public HomePresenter provideHomePresenter(HomeActivity homeActivity){
        return new HomePresenter(homeActivity);
    }
}
