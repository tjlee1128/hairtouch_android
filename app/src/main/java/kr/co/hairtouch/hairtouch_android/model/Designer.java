package kr.co.hairtouch.hairtouch_android.model;

import java.util.List;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public class Designer {
    int id;
    String name;
    String phone;
    String start_time;
    String end_time;
    int reservation_type;
    String main_image;
    String image;

    ReviewTotal review;
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

    public int getReservation_type() {
        return reservation_type;
    }

    public String getMain_image() {
        return main_image;
    }

    public String getImage() {
        return image;
    }

    public ReviewTotal getReview() {
        return review;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
