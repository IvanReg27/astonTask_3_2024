package ivan.makhorin.touristagency.weather;

import ivan.makhorin.touristagency.weather.model.WeatherData;

public interface WeatherService {
    WeatherData getWeatherData(String latitude, String longitude);
}
