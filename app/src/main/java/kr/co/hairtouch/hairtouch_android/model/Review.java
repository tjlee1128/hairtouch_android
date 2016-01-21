package kr.co.hairtouch.hairtouch_android.model;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public class Review {
    int id;
    ReviewMember member;
    String created_at;
    String detail;
    String image;
    double grade;

    public int getId() {
        return id;
    }

    public ReviewMember getMember() {
        return member;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDetail() {
        return detail;
    }

    public String getImage() {
        return image;
    }

    public double getGrade() {
        return grade;
    }
}
