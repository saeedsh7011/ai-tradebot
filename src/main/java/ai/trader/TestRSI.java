package ai.trader;

import ai.trader.telegram.TelegramNotifier;

public class TestRSI {
    public static void main(String[] args) {
        TelegramNotifier notifier = TelegramNotifier.createAndStart(
            "7458165338:AAFg0MUdoa-sGGKZfoil3PirVRQ1xfO6DQI", // توکن
            "scalp3andicatorbot",                             // یوزرنیم
            "-1002465510421"                                  // Chat ID
        );

        notifier.send("✅ تست پیام از بات به کانال تلگرام");
    }
}
