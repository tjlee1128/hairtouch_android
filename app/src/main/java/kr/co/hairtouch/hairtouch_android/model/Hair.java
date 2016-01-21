package kr.co.hairtouch.hairtouch_android.model;

import java.util.List;

/**
 * Created by leetaejun on 2016. 1. 21..
 */
public class Hair {
    int id;
    int designer_id;
    int mainregion_id;
    int subregion_id;
    int haircategorycode_id;
    int haircategory_id;
    String title;
    String subtitle;
    String detail;
    List<HairImage> images;

    public int getId() {
        return id;
    }

    public int getDesigner_id() {
        return designer_id;
    }

    public int getMainregion_id() {
        return mainregion_id;
    }

    public int getSubregion_id() {
        return subregion_id;
    }

    public int getHaircategorycode_id() {
        return haircategorycode_id;
    }

    public int getHaircategory_id() {
        return haircategory_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDetail() {
        return detail;
    }

    public List<HairImage> getImages() {
        return images;
    }
}
