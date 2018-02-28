package jmlzz.example.baseframe.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmlzz.example.baseframe.R;
import jmlzz.example.baseframe.bean.HomeTopicBean;
import jmlzz.example.baseframe.presenter.home.DaggerHomeActivityComponent;
import jmlzz.example.baseframe.presenter.home.HomeActivityModel;
import jmlzz.example.baseframe.presenter.home.HomePresenter;
import jmlzz.example.baseframe.utils.GlideImageLoader;

/**
 * Created by robert.luozizhao on 2018/2/28.
 */

public class HomeActivity extends AppCompatActivity {

    @Inject
    HomePresenter mHomePresenter;
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        DaggerHomeActivityComponent.builder()
                .homeActivityModel(new HomeActivityModel(this))
                .build()
                .inject(this);

        initData();
    }

    private void initData() {
        mHomePresenter.getHomeBanner();
    }

    public void setBanner(String[] title,String[] picUrl) {
        List<String> strings = Arrays.asList(picUrl);

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader()) //图片加载器
                .setImages(Arrays.asList(picUrl))
                .setBannerTitles(Arrays.asList(title))
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .start();
    }

}
