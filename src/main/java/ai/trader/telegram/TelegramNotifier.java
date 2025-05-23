package ai.trader.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramNotifier extends TelegramLongPollingBot {
    private static String BOT_TOKEN;
    private static String CHAT_ID;
    private static String BOT_USERNAME;

    private TelegramNotifier(String token, String username, String chatId) {
        BOT_TOKEN = token;
        BOT_USERNAME = username;
        CHAT_ID = chatId;
    }

    public static TelegramNotifier createAndStart(String token, String username, String chatId) {
        TelegramNotifier bot = new TelegramNotifier(token, username, chatId);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return bot;
    }

    public void send(String text) {
        SendMessage message = new SendMessage();
        message.setChatId(CHAT_ID);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        // پاسخ‌گویی لازم نیست
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
