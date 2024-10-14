package com.project.WeatherBot.weatherclasses;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MMain {
    @JsonProperty("temp")
    public float temp;
    @JsonProperty("feels_like")
    float feels_like;
    @JsonProperty("temp_min")
    float temp_min;
    @JsonProperty("temp_max")
    float temp_max;
    @JsonProperty("pressure")
    float pressure;
    @JsonProperty("humidity")
    float humidity;
    @JsonProperty("sea_level")
    float sea_level;
    @JsonProperty("grnd_level")
    float grnd_level;

}
