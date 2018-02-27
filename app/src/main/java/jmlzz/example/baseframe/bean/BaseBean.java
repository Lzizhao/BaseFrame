package jmlzz.example.baseframe.bean;

/**
 * Created by robert.luozizhao on 2018/2/27.
 */

public class BaseBean {
    private String response;
    private int error_code;
    private String error;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "response='" + response + '\'' +
                ", error_code=" + error_code +
                ", error='" + error + '\'' +
                '}';
    }
}
