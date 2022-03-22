package com.xpressy.firsttest.Model.Jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "country",
        "logo",
        "slogan",
        "head_quaters",
        "website",
        "established"
})
public class AirLine {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("logo")
    private String logo;
    @JsonProperty("slogan")
    private String slogan;
    @JsonProperty("head_quaters")
    private String headQuaters;
    @JsonProperty("website")
    private String website;
    @JsonProperty("established")
    private String established;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("logo")
    public String getLogo() {
        return logo;
    }

    @JsonProperty("logo")
    public void setLogo(String logo) {
        this.logo = logo;
    }

    @JsonProperty("slogan")
    public String getSlogan() {
        return slogan;
    }

    @JsonProperty("slogan")
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @JsonProperty("head_quaters")
    public String getHeadQuaters() {
        return headQuaters;
    }

    @JsonProperty("head_quaters")
    public void setHeadQuaters(String headQuaters) {
        this.headQuaters = headQuaters;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("established")
    public String getEstablished() {
        return established;
    }

    @JsonProperty("established")
    public void setEstablished(String established) {
        this.established = established;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
