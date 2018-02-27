package jmlzz.example.baseframe.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jmlzz.example.baseframe.R;
import jmlzz.example.baseframe.apiservice.ApiService;
import jmlzz.example.baseframe.bean.RegisterBean;
import jmlzz.example.baseframe.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.et_accout)
    EditText etAccout;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);

    }


    @OnClick({R.id.et_accout, R.id.et_password, R.id.btn_register,R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_accout:
                break;
            case R.id.et_password:
                break;
            case R.id.btn_register:
                if (TextUtils.isEmpty(etAccout.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
                    ToastUtils.showShort("input error");
                    return;
                }
                final Call<RegisterBean> register = mApiService.register(etAccout.getText().toString(), etPassword.getText().toString());
                register.enqueue(new Callback<RegisterBean>() {
                    @Override
                    public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                        if (response.isSuccessful()) {
                            RegisterBean registerBean = response.body();
                            LogUtils.d("lzz", "成功 " + registerBean.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterBean> call, Throwable t) {
                        LogUtils.d("lzz", "失败 " + call.toString());
                    }
                });
                break;
            case R.id.btn_login:


                break;
        }
    }

}
