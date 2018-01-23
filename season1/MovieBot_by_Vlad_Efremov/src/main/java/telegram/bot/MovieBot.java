package telegram.bot;

import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.math.NumberUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MovieBot extends TelegramLongPollingBot {

    private static final MovieFinder MOVIE_FINDER = new MovieFinder();
    private static final String API_KEY = "512299036:AAFS2vpL7UKCI2xYnvKgyrTGiBiwQxRgVwg";
    private static final String BOT_NAME = "telegram.bot.MovieBot";
    private List<Movie> movies;
    private List<DetailedMovie> detailedMovies;

    private static final String BOT_INFORMATION = "" +
            "Hello, I'm telegram.bot.MovieBot, i can help you find a movie!\n" +
            "Use these commands:\n" +
            "/help - to get help\n" +
            "/popular - to get most popular movies\n" +
            "/rated - to get top rated movies\n" +
            "/upcoming - to get movies, which will be soon in theatres\n" +
            "/playing - to get movies, which playing in theatres right now\n" +
            "Or you can just type in the movie name if you want to find specific movie\n" +
            "In order to get detailed information about the movie, type in /details {movie_id}\n" +
            "Hope you'll like it! " + EmojiParser.parseToUnicode(":grin:");

    private static final String HELP_INFORMATION = "" +
            "Use these commands:\n" +
            "/help - to get help\n" +
            "/popular - to get most popular movies\n" +
            "/rated - to get top rated movies\n" +
            "/upcoming - to get movies, which will be soon in theatres\n" +
            "/playing - to get movies, which playing in theatres right now\n" +
            "Or you can just type in the movie name if you want to find specific movie\n" +
            "In order to get detailed information about the movie, type in /details {movie_id}\n" +
            "If you didn't get answer on your request, it means that too many requests have been made and you should try again after few minutes. Sorry for that(";
    private static final String FAIL_MESSAGE = "Can't find anything on your request " + EmojiParser.parseToUnicode(":scream:");

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String receviedMsgText = update.getMessage().getText();
            receviedMsgText = receviedMsgText.trim();


            switch (receviedMsgText) {
                case "/popular":
                    movies = MOVIE_FINDER.getPopularMovies();
                    answerOnMovieRequest(update);
                    break;

                case "/rated":
                    movies = MOVIE_FINDER.getTopRatedMovies();
                    answerOnMovieRequest(update);
                    break;

                case "/upcoming":
                    movies = MOVIE_FINDER.getUpcomingMovies();
                    answerOnMovieRequest(update);
                    break;

                case "/playing":
                    movies = MOVIE_FINDER.getMoviesInTheatres();
                    answerOnMovieRequest(update);
                    break;

                case "/start":
                    answerOnText(update, BOT_INFORMATION);
                    break;

                case "/help":
                    answerOnText(update, HELP_INFORMATION);
                    break;

                default://Checking if recevied message is movie request or detailed movie request
                    if (!receviedMsgText.startsWith("/")) {//if recevied message starts with "/" symbol, that it not movie request
                        receviedMsgText = receviedMsgText.replace(' ', '+');//replacing symbols for request to the tmdb api
                        movies = MOVIE_FINDER.getMovies(receviedMsgText);//geting movies
                        answerOnMovieRequest(update);
                    } else {
                        String[] array = receviedMsgText.split(" ");
                        if (array[0].toLowerCase().equals("/details")) {
                            List<Integer> IDs = new ArrayList<>();
                            for (int i = 0; i < array.length; i++) {
                                if (NumberUtils.isDigits(array[i]))
                                    IDs.add(Integer.parseInt(array[i]));//creating detailed movies IDs
                            }
                            if (IDs.size() != 0) {
                                detailedMovies = MOVIE_FINDER.getDetailedMovies(IDs);//getting detailed movies
                                answerOnDetailedMovieRequest(update);

                            } else
                                answerOnText(update, FAIL_MESSAGE);
                        } else {
                            answerOnText(update, FAIL_MESSAGE);
                        }
                    }
                    break;
            }
        }


    }

    private void answerOnDetailedMovieRequest(Update update) {
        long chatID = update.getMessage().getChatId();
        SendMessage sendMsg = new SendMessage()
                .setChatId(chatID);
        try {
            if (!detailedMovies.isEmpty()) {
                for (DetailedMovie m : detailedMovies) {
                    sendMsg.setText(m.getPoster() + "\n" + m.toString());
                    sendMessage(sendMsg);
                    Thread.sleep(1000);
                }
            } else {
                sendMsg.setText(FAIL_MESSAGE);
                sendMessage(sendMsg);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void answerOnMovieRequest(Update update) {//Answering on any movie request

        long chatID = update.getMessage().getChatId();

        SendMessage sendMsg = new SendMessage()
                .setChatId(chatID);
        try {

            if (!movies.isEmpty()) {
                for (Movie m : movies) {
                    sendMsg.setText(m.getPoster() + "\n" + m.toString());
                    sendMessage(sendMsg);
                    Thread.sleep(1000);
                }
            } else {
                sendMsg.setText(FAIL_MESSAGE);
                sendMessage(sendMsg);

            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    private void answerOnText(Update update, String answer) {//Answering on commands /help and /start or on the wrong request
        long chatID = update.getMessage().getChatId();

        SendMessage sendMsg = new SendMessage()
                .setChatId(chatID)
                .setText(answer);

        try {
            sendMessage(sendMsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            try {
                sendMessage(sendMsg.setText("Too many requests have been made, try again after few minutes. Sorry for that(("));
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return API_KEY;
    }
}
