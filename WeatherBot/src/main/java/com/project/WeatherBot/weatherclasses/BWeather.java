package com.project.WeatherBot.weatherclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BWeather {
        @JsonProperty("coord")
        Coord coord;
        @JsonProperty("weather")
        Weather[] weather;
        @JsonProperty("base")
        String base=null;
        @JsonProperty("main")
        public MMain main;
        @JsonProperty("visibility")
        int visibility;
        @JsonProperty("wind")
        Wind wind;
        @JsonProperty("rain")
        Rain rain;
        @JsonProperty("clouds")
        Clouds clouds;
        @JsonProperty("dt")
        int dt;
        @JsonProperty("sys")
        Sys sys;
        @JsonProperty("timezone")
        int timezone;
        @JsonProperty("id")
        int id;
        @JsonProperty("name")
        String name;
        @JsonProperty("cod")
        int cod;

}
