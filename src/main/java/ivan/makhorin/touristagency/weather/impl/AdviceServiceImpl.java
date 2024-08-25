package ivan.makhorin.touristagency.weather.impl;

import ivan.makhorin.touristagency.weather.AdviceService;
import ivan.makhorin.touristagency.weather.model.WeatherData;
import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Service;

@Service
public class AdviceServiceImpl implements AdviceService {
    @Override
    public String getAdvice(WeatherData weatherData) {
        var adviceMessage = "Advice.";
        if (isCold(weatherData)) {
            adviceMessage = adviceMessage.concat(" It’s cold, put on warm clothes.");
        }
        if (isHot(weatherData)) {
            adviceMessage = adviceMessage.concat(" It’s hot, put on some light clothes.");
        }
        if (isWindy(weatherData)) {
            adviceMessage = adviceMessage.concat(" There is wind, better stay in the hotel.");
        }
        if (isRainy(weatherData)) {
            adviceMessage = adviceMessage.concat(" High chance of rain, take your umbrella with you.");
        }
        if (isSnowy(weatherData)) {
            adviceMessage = adviceMessage.concat(" Snow is possible.");
        }
        return adviceMessage;
    }

    private boolean isCold(WeatherData weatherData) {
        return Range.of(Double.MIN_VALUE, 5D).contains(weatherData.getCurrent().getFeelslikeC());
    }

    private boolean isHot(WeatherData weatherData) {
        return Range.of(30D, Double.MAX_VALUE).contains(weatherData.getCurrent().getFeelslikeC());
    }

    private boolean isWindy(WeatherData weatherData) {
        return Range.of(39D, Double.MAX_VALUE).contains(weatherData.getCurrent().getWindKph());
    }

    private boolean isRainy(WeatherData weatherData) {
        return weatherData.getCurrent().getCondition().getText().toLowerCase().contains("rain");
    }

    private boolean isSnowy(WeatherData weatherData) {
        return weatherData.getCurrent().getCondition().getText().toLowerCase().contains("snow");
    }}
