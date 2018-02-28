package jmlzz.example.baseframe.presenter.register;

import dagger.Component;
import jmlzz.example.baseframe.ui.activity.RegisterActivity;

/**
 * Created by robert.luozizhao on 2018/2/28.
 */

@Component(modules = RegisterActivityModel.class)
public interface RegisterActivityComponent {
    void inject(RegisterActivity registerActivity);
}
