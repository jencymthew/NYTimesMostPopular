package com.nytimestest.Data;

/**
 * Created by Jency Mathew on 27-09-2018.
 */

public class Rvdata {
        public String title;
        public String byline;
        public String source;
        public String published_date;

    public Rvdata(String title,String byline, String source,String published_date){
        this.title = title;
        this.byline = byline;
        this.source = source;
        this.published_date=published_date;


    }
    public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getByline() {
            return byline;
        }
        public void setByline(String byline) {
            this.byline = byline;
        }
        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    public String getPublished_date() {
        return published_date;
    }
    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }
    }

