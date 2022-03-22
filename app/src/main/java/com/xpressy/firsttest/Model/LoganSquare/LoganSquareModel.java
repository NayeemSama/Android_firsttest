package com.xpressy.firsttest.Model.LoganSquare;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class LoganSquareModel{
    @JsonProperty("type")
    public String getType() {
        return this.type; }
    public void setType(String type) {
        this.type = type; }
    String type;
    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return this.metadata; }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata; }
    Metadata metadata;
    @JsonProperty("features")
    public ArrayList<Feature> getFeatures() {
        return this.features; }
    public void setFeatures(ArrayList<Feature> features) {
        this.features = features; }
    ArrayList<Feature> features;
    @JsonProperty("bbox")
    public ArrayList<Double> getBbox() {
        return this.bbox; }
    public void setBbox(ArrayList<Double> bbox) {
        this.bbox = bbox; }
    ArrayList<Double> bbox;
}


