package jmlzz.example.baseframe.presenter;

import dagger.Module;
import dagger.Provides;
import jmlzz.example.baseframe.ui.activity.RegisterActivity;

/**
 * Created by robert.luozizhao on 2018/2/28.
 */

@Module
public class RegisterActivityModel {

    //dagger2-1 建立presenter 需要什么的activity ,需要什么变量和构造函数
    private RegisterActivity registerActivity;

    public RegisterActivityModel(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    @Provides
    public RegisterActivity provideRegisterActivity(){
        return registerActivity;
    }

    //dagger2-2  model 最终目的建立的是注入的对象(这里是presenter)
    @Provides
    public RegisterPresenter provideRegister(RegisterActivity registerActivity){  // * registerActivity 有@provides注释的provideRegisterActivity方法提供
      return   new RegisterPresenter(registerActivity);
    }
}
