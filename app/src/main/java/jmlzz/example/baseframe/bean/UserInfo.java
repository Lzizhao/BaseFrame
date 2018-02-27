package jmlzz.example.baseframe.bean;

/**
 * Created by robert.luozizhao on 2018/2/27.
 */

public class UserInfo {
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid='" + userid + '\'' +
                '}';
    }
}
