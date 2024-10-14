package com.project.WeatherBot.weatherclasses;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    @JsonProperty("id")
    int id;
    @JsonProperty("main")
    String main;
    @JsonProperty("description")
    String description;
    @JsonProperty("icon")
    String icon;
}
