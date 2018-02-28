package jmlzz.example.baseframe.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import jmlzz.example.baseframe.apiservice.ApiService;
import jmlzz.example.baseframe.bean.RegisterBean;
import jmlzz.example.baseframe.ui.activity.RegisterActivity;
import jmlzz.example.baseframe.common.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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
    }

    public void register(String username,String password){


        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);


        final Call<RegisterBean> register = mApiService.register(username, password);
        register.enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                if (response.isSuccessful()) {
                    RegisterBean registerBean = response.body();
                    LogUtils.d("lzz", "成功 " + registerBean.toString());
                    ToastUtils.showShort("注册成功");
                    SPUtils.getInstance(Constant.SP_MARKET).put(Constant.USERID,registerBean.getUserInfo().getUserid());
                    String string = SPUtils.getInstance(Constant.SP_MARKET).getString(Constant.USERID);
                    LogUtils.d("lzz :",string);
                }
            }

            @Override
            public void onFailure(Call<RegisterBean> call, Throwable t) {
                LogUtils.d("lzz", "失败 " + call.toString());
            }
        });

    }
}
