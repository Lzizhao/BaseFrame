package jmlzz.example.baseframe.bean;

import retrofit2.http.POST;

/**
 * Created by robert.luozizhao on 2018/2/27.
 */

public class RegisterBean extends BaseBean {

    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    public String toString() {
        return "RegisterBean{" +
                "userInfo=" + userInfo +
                '}'+
                ",error="+getError() +
                ",response="+getResponse()+
                ",error_code="+getError_code();
    }
}
