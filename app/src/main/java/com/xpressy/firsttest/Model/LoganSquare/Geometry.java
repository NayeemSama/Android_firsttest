package com.xpressy.firsttest.Model.LoganSquare;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Geometry{
    @JsonProperty("type")
    public String getType() {
        return this.type; }
    public void setType(String type) {
        this.type = type; }
    String type;
    @JsonProperty("coordinates")
    public ArrayList<Double> getCoordinates() {
        return this.coordinates; }
    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates; }
    ArrayList<Double> coordinates;
}
