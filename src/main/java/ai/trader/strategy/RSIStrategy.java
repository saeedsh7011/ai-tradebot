package ai.trader.strategy;

import org.ta4j.core.BaseBar;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.DecimalNum;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class RSIStrategy {

    private final int rsiPeriod;
    private final int lowThreshold;
    private final int highThreshold;

    public enum Signal {
        BUY, SELL, HOLD
    }

    public RSIStrategy(int rsiPeriod, int lowThreshold, int highThreshold) {
        this.rsiPeriod = rsiPeriod;
        this.lowThreshold = lowThreshold;
        this.highThreshold = highThreshold;
    }

    public Signal evaluate(List<Double> closePrices) {
        if (closePrices.size() < rsiPeriod + 1) {
            return Signal.HOLD;
        }

        BarSeries series = new BaseBarSeries();
        for (int i = 0; i < closePrices.size(); i++) {
            double price = closePrices.get(i);
            Bar bar = new BaseBar(
    Duration.ofDays(1),
    ZonedDateTime.now().minusDays(closePrices.size() - i),
    closePrices.get(i), // open price
    closePrices.get(i), // high price
    closePrices.get(i), // low price
    closePrices.get(i), // close price
    1.0 // volume
);

        }

        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        RSIIndicator rsi = new RSIIndicator(closePrice, rsiPeriod);

        Num lastRsi = rsi.getValue(series.getEndIndex());
        double rsiValue = lastRsi.doubleValue();

        if (rsiValue < lowThreshold) return Signal.BUY;
        if (rsiValue > highThreshold) return Signal.SELL;
        return Signal.HOLD;
    }
}
