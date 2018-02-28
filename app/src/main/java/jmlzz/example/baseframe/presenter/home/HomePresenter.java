package jmlzz.example.baseframe.presenter.home;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jmlzz.example.baseframe.apiservice.ApiService;
import jmlzz.example.baseframe.bean.HomeTopicBean;
import jmlzz.example.baseframe.common.Constant;
import jmlzz.example.baseframe.ui.activity.HomeActivity;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robert.luozizhao on 2018/2/28.
 */

public class HomePresenter {
    private HomeActivity mHomeActivity;
    private Retrofit mRetrofit;
    private ApiService mApiService;

    public HomePresenter(HomeActivity homeActivity) {
        this.mHomeActivity = homeActivity;

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }


    public void getHomeBanner(){
        mApiService.homeBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeTopicBean>() {
                    @Override
                    public void accept(HomeTopicBean homeTopicBean) throws Exception {
                        List<HomeTopicBean.HomeTopic> homeTopicList = homeTopicBean.getHomeTopic();
                        LogUtils.d("lzz",homeTopicBean.toString());
                        String[] title = new String[homeTopicList.size()];
                        String[] picUrl = new String[homeTopicList.size()];
                        for (int i = 0; i < homeTopicList.size(); i++) {
                            title[i] = homeTopicList.get(i).getTitle();
                            picUrl[i] = Constant.HOST+homeTopicList.get(i).getPic();
                        }
                        mHomeActivity.setBanner(title,picUrl);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d("lzz",throwable.toString());
                    }
                });
    }


}
