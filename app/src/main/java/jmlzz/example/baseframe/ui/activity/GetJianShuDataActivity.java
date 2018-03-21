package jmlzz.example.baseframe.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import jmlzz.example.baseframe.R;

/**
 * Created by robert.luozizhao on 2018/3/13.
 */

public class GetJianShuDataActivity extends AppCompatActivity {


    @BindView(R.id.tv_data)
    TextView tvData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_jianshu_data);
        ButterKnife.bind(this);

        final StringBuffer sb = new StringBuffer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://www.jianshu.com/").timeout(10000).get();
                    Elements noteList = document.select("ul.note-list");
                    Elements li = noteList.select("li");
                    for (Element element : li) {
                        String text = element.select("a.title").text();
                        if (text!=null) {
                            sb.append(text+"\n");
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showShort(sb.toString());
                            tvData.setText(sb.toString());
                        }
                    });

                } catch (IOException e) {

                }
            }
        }).start();




    }
}
