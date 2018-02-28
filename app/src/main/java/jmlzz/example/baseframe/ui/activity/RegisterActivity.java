package jmlzz.example.baseframe.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jmlzz.example.baseframe.R;
import jmlzz.example.baseframe.presenter.DaggerRegisterActivityComponent;
import jmlzz.example.baseframe.presenter.RegisterActivityModel;
import jmlzz.example.baseframe.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_accout)
    EditText etAccout;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Inject
    RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerRegisterActivityComponent.builder().registerActivityModel(new RegisterActivityModel(this)).build().inject(this);
    }


    @OnClick({R.id.et_accout, R.id.et_password, R.id.btn_register,R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_accout:
                break;
            case R.id.et_password:
                break;
            case R.id.btn_register:
                String username = etAccout.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("input error");
                    return;
                }

//                mRegisterPresenter = new RegisterPresenter(this);

                mRegisterPresenter.register(username,password);
                break;
            case R.id.btn_login:
                String username1 = etAccout.getText().toString().trim();
                String password1 = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username1) || TextUtils.isEmpty(password1)) {
                    ToastUtils.showShort("input error");
                    return;
                }
                mRegisterPresenter.login(username1,password1);
                break;
        }
    }

}
