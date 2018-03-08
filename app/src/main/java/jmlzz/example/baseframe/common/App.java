package jmlzz.example.baseframe.common;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;


/**
 * Created by robert.luozizhao on 2018/2/27.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        MultiDex.install(this);

    }
}
