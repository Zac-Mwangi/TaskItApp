package com.example.taskit.Extras;

public class commentsModel {
    String date_posted,feedback_text;
    double rate;
    String feedback_by;

    public commentsModel(String date_posted, String feedback_text, double rate, String feedback_by) {
        this.date_posted = date_posted;
        this.feedback_text = feedback_text;
        this.rate = rate;
        this.feedback_by = feedback_by;
    }

    public String getDate_posted() {
        return date_posted;
    }

    public String getFeedback_text() {
        return feedback_text;
    }

    public double getRate() {
        return rate;
    }

    public String getFeedback_by() {
        return feedback_by;
    }
}
