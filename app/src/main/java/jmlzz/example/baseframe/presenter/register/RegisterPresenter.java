package jmlzz.example.baseframe.presenter.register;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import jmlzz.example.baseframe.apiservice.ApiService;
import jmlzz.example.baseframe.bean.HomeTopicBean;
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
    //56417c9802f6410650cd0361db42ec5a
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

    public void register(String username, String password) {
        mApiService.register(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        LogUtils.d("lzz", "请求成功");
                        LogUtils.d("lzz", "成功 " + registerBean.toString());
                        ToastUtils.showShort("注册成功");
                        SPUtils.getInstance(Constant.SP_MARKET).put(Constant.USERID, registerBean.getUserInfo().getUserid());
                        String string = SPUtils.getInstance(Constant.SP_MARKET).getString(Constant.USERID);
                        LogUtils.d("lzz :", string);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d("lzz", "注册失败");
                    }
                });
    }


    public void login(String username, String password) {
        final Observable<RegisterBean> login = mApiService.login(username, password);
        mApiService.login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        LogUtils.d("lzz", "成功 " + registerBean.toString());
                        ToastUtils.showShort("登陆成功");
                        SPUtils.getInstance(Constant.SP_MARKET).put(Constant.USERID, registerBean.getUserInfo().getUserid());
                        String string = SPUtils.getInstance(Constant.SP_MARKET).getString(Constant.USERID);
                        LogUtils.d("lzz :", string);
                        mRegisterActivity.loginSuccess();
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

    /*flatmap 操作符实现*/
    public void loginAndRegister(final String userName, final String password) {
        Observable<RegisterBean> register = mApiService.register(userName, password);
        LogUtils.d("lzz","1,注册申请");
        register.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        LogUtils.d("lzz","2,注册成功");
                        // 1 注册
                        LogUtils.d("lzz","处理注册操作");
                    }
                }).observeOn(Schedulers.io())// 子线程去登陆操作
                .flatMap(new Function<RegisterBean, ObservableSource<RegisterBean>>() {
                    @Override
                    public ObservableSource<RegisterBean> apply(RegisterBean registerBean) throws Exception {
                        LogUtils.d("lzz","3,登陆请求");
                        //2 进行登陆操作
                        return mApiService.login(userName, password);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        LogUtils.d("lzz","4,登陆成功--流程结束");
                        // 登陆成功
                        ToastUtils.showShort("登陆成功");
                        SPUtils.getInstance(Constant.SP_MARKET).put(Constant.USERID, registerBean.getUserInfo().getUserid());
                        String string = SPUtils.getInstance(Constant.SP_MARKET).getString(Constant.USERID);
                        LogUtils.d("lzz :", string);
                        mRegisterActivity.loginSuccess();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d("lzz","4.1,登陆失败");
                    }
                });
    }


}
