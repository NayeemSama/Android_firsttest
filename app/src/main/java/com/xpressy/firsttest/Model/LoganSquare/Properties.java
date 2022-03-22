package com.xpressy.firsttest.Model.LoganSquare;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {
    @JsonProperty("mag")
    public double getMag() {
        return this.mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    double mag;

    @JsonProperty("place")
    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    String place;

    @JsonProperty("time")
    public Object getTime() {
        return this.time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    Object time;

    @JsonProperty("updated")
    public Object getUpdated() {
        return this.updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    Object updated;

    @JsonProperty("tz")
    public Object getTz() {
        return this.tz;
    }

    public void setTz(Object tz) {
        this.tz = tz;
    }

    Object tz;

    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    String url;

    @JsonProperty("detail")
    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    String detail;

    @JsonProperty("felt")
    public Object getFelt() {
        return this.felt;
    }

    public void setFelt(Object felt) {
        this.felt = felt;
    }

    Object felt;

    @JsonProperty("cdi")
    public Object getCdi() {
        return this.cdi;
    }

    public void setCdi(Object cdi) {
        this.cdi = cdi;
    }

    Object cdi;

    @JsonProperty("mmi")
    public Object getMmi() {
        return this.mmi;
    }

    public void setMmi(Object mmi) {
        this.mmi = mmi;
    }

    Object mmi;

    @JsonProperty("alert")
    public Object getAlert() {
        return this.alert;
    }

    public void setAlert(Object alert) {
        this.alert = alert;
    }

    Object alert;

    @JsonProperty("status")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    @JsonProperty("tsunami")
    public int getTsunami() {
        return this.tsunami;
    }

    public void setTsunami(int tsunami) {
        this.tsunami = tsunami;
    }

    int tsunami;

    @JsonProperty("sig")
    public int getSig() {
        return this.sig;
    }

    public void setSig(int sig) {
        this.sig = sig;
    }

    int sig;

    @JsonProperty("net")
    public String getNet() {
        return this.net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    String net;

    @JsonProperty("code")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    String code;

    @JsonProperty("ids")
    public String getIds() {
        return this.ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    String ids;

    @JsonProperty("sources")
    public String getSources() {
        return this.sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    String sources;

    @JsonProperty("types")
    public String getTypes() {
        return this.types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    String types;

    @JsonProperty("nst")
    public int getNst() {
        return this.nst;
    }

    public void setNst(int nst) {
        this.nst = nst;
    }

    int nst;

    @JsonProperty("dmin")
    public double getDmin() {
        return this.dmin;
    }

    public void setDmin(double dmin) {
        this.dmin = dmin;
    }

    double dmin;

    @JsonProperty("rms")
    public double getRms() {
        return this.rms;
    }

    public void setRms(double rms) {
        this.rms = rms;
    }

    double rms;

    @JsonProperty("gap")
    public double getGap() {
        return this.gap;
    }

    public void setGap(double gap) {
        this.gap = gap;
    }

    double gap;

    @JsonProperty("magType")
    public String getMagType() {
        return this.magType;
    }

    public void setMagType(String magType) {
        this.magType = magType;
    }

    String magType;

    @JsonProperty("type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    @JsonProperty("title")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title;
}
