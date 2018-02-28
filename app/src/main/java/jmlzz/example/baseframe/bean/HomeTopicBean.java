package jmlzz.example.baseframe.bean;

import java.util.List;

/**
 * Created by robert.luozizhao on 2018/2/28.
 */

public class HomeTopicBean extends BaseBean{

    private List<HomeTopic> homeTopic;


    public List<HomeTopic> getHomeTopic() {
        return homeTopic;
    }

    public void setHomeTopic(List<HomeTopic> homeTopic) {
        this.homeTopic = homeTopic;
    }

    public static class HomeTopic{
        private int id;
        private String title;
        private String pic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        @Override
        public String toString() {
            return "HomeTopic{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", pic='" + pic + '\'' +
                    '}';
        }
    }

}
