package com.project.WeatherBot.weatherclasses;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Sys
{   @JsonProperty("type")
    int type;
    @JsonProperty("id")
    int id;
    @JsonProperty("country")
    String country;
    @JsonProperty("sunrise")
    int sunrise;
    @JsonProperty("sunset")
    int sunset;
}
