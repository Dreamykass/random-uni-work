package com.dkass.weatherapp;

public interface WeatherApiService {
    @Headers("x-api-key: " + AppConstants.WEATHER_API_KEY)
    @GET("data/2.5/forecast")
    Call<WeatherModel> requestWeather(@Query("lat") String lat, @Query("lon") String lon, @Query("units") String units, @Query("cnt") String count);
}
