package jmlzz.example.baseframe.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import jmlzz.example.baseframe.apiservice.ApiService;
import jmlzz.example.baseframe.bean.RegisterBean;
import jmlzz.example.baseframe.ui.activity.RegisterActivity;
import jmlzz.example.baseframe.common.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by robert.luozizhao on 2018/2/28.
 */

public class RegisterPresenter {

    private RegisterActivity mRegisterActivity;

    private Retrofit mRetrofit;
    private ApiService mApiService;


    public RegisterPresenter(RegisterActivity mRegisterActivity) {
        this.mRegisterActivity = mRegisterActivity;

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public void register(String username,String password){
        mApiService.register(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        LogUtils.d("lzz","请求成功");
                        LogUtils.d("lzz", "成功 " + registerBean.toString());
                        ToastUtils.showShort("注册成功");
                        SPUtils.getInstance(Constant.SP_MARKET).put(Constant.USERID,registerBean.getUserInfo().getUserid());
                        String string = SPUtils.getInstance(Constant.SP_MARKET).getString(Constant.USERID);
                        LogUtils.d("lzz :",string);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d("lzz","注册失败");
                    }
                });
    }


    public void login(String username,String password){
        final Observable<RegisterBean> login = mApiService.login(username, password);
        mApiService.login(username,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        LogUtils.d("lzz", "成功 " + registerBean.toString());
                        ToastUtils.showShort("登陆成功");
                        SPUtils.getInstance(Constant.SP_MARKET).put(Constant.USERID,registerBean.getUserInfo().getUserid());
                        String string = SPUtils.getInstance(Constant.SP_MARKET).getString(Constant.USERID);
                        LogUtils.d("lzz :",string);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d("lzz", "登陆失败 ");
                    }
                });

      /*  login.enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                if (response.isSuccessful()) {
                    RegisterBean registerBean = response.body();
                    LogUtils.d("lzz", "成功 " + registerBean.toString());
                    ToastUtils.showShort("登陆成功");
                    SPUtils.getInstance(Constant.SP_MARKET).put(Constant.USERID,registerBean.getUserInfo().getUserid());
                    String string = SPUtils.getInstance(Constant.SP_MARKET).getString(Constant.USERID);
                    LogUtils.d("lzz :",string);
                }
            }

            @Override
            public void onFailure(Call<RegisterBean> call, Throwable t) {
                LogUtils.d("lzz", "失败 " + call.toString());
            }
        });*/
    }


}
