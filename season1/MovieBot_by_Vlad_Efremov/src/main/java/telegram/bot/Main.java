package telegram.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApiContextInitializer.init();


        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new MovieBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("telegram.bot.MovieBot successfully started!");

    }
}
