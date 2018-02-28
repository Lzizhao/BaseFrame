package jmlzz.example.baseframe.presenter.home;

import javax.inject.Inject;

import dagger.Component;
import jmlzz.example.baseframe.ui.activity.HomeActivity;

/**
 * Created by robert.luozizhao on 2018/2/28.
 */

@Component(modules = HomeActivityModel.class)
public interface HomeActivityComponent {
    void inject(HomeActivity homeActivity);
}
