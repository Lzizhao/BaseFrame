package jmlzz.example.baseframe.apiservice;

import io.reactivex.Observable;
import jmlzz.example.baseframe.bean.RegisterBean;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by robert.luozizhao on 2018/2/27.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> register(@Field("username") String username,
                                      @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Observable<RegisterBean> login(@Field("username") String username,
                                @Field("password") String password);

}
