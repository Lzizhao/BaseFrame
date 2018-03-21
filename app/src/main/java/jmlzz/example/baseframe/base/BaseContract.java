package jmlzz.example.baseframe.base;

/**
 * Created by robert.luozizhao on 2018/3/21.
 */

public class BaseContract {

    interface BasePresenter<T extends BaseView>{
        void attachView(T t);
        void detachView();
    }

    interface BaseView{
        void showLoading();
        void hideLoading();
        void showSuccess();
        void showFaild();
        //显示当前网络不可用
        void showNoNet();
    }
}
