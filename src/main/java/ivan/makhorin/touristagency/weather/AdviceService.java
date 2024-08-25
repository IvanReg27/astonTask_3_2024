package ivan.makhorin.touristagency.weather;

import ivan.makhorin.touristagency.weather.model.WeatherData;

public interface AdviceService {
    String getAdvice(WeatherData weatherData);
}
