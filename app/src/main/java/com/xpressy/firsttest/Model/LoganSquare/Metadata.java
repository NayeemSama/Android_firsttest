package com.xpressy.firsttest.Model.LoganSquare;

import com.fasterxml.jackson.annotation.JsonProperty;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class Metadata {
    @JsonProperty("generated")
    public long getGenerated() {
        return this.generated;
    }

    public void setGenerated(long generated) {
        this.generated = generated;
    }

    long generated;

    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url;

    @JsonProperty("title")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;

    @JsonProperty("status")
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    int status;

    @JsonProperty("api")
    public String getApi() {
        return this.api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    String api;

    @JsonProperty("count")
    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int count;
}
