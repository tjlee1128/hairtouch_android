package kr.co.hairtouch.hairtouch_android.model;

import java.util.List;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public class Shop {
    int id;
    String name;
    String phone;
    String start_time;
    String end_time;
    String holiday;
    String address;
    String main_image;
    ReviewTotal review;

    List<Designer> designers;
    List<Review> reviews;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getHoliday() {
        return holiday;
    }

    public String getAddress() {
        return address;
    }

    public String getMain_image() {
        return main_image;
    }

    public ReviewTotal getReview() {
        return review;
    }

    public List<Designer> getDesigners() {
        return designers;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
