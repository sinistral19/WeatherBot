package com.project.WeatherBot;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.project.WeatherBot.components.BotCommands;
import com.project.WeatherBot.components.Buttons;
import com.project.WeatherBot.weatherclasses.BWeather;
import jakarta.validation.constraints.NotNull;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.project.WeatherBot.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.project.WeatherBot.config.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
@Slf4j
@Component
public class WeatherBot extends TelegramLongPollingBot {
    final BotConfig config;
    Logger log= LoggerFactory.getLogger(WeatherBot.class);
    public WeatherBot(BotConfig configs) {
        config=configs;
    }
    @Override
    public String getBotUsername() { return config.getBotName(); }
    @Override
    public String getBotToken() { return config.getBotToken(); }
    @Override
    public void onUpdateReceived(@NotNull Update update) {
       /* if(update.hasMessage() && update.getMessage().hasText()){
            String messageText=update.getMessage().getText();
            long chatId=update.getMessage().getChatId();
            String name=update.getMessage().getChat().getFirstName();

            switch (messageText)
            {
                case "/start":
                    startBot(chatId,name);
                case "/getweather":
                    getWeather(chatId);
                default:log.info("Unexpected message");
            }
        }*/
        long chatId = 0;
        long userId = 0; //это нам понадобится позже
        String userName = null;
        String receivedMessage;
        if(update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userId = update.getMessage().getFrom().getId();
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                botAnswerUtils(receivedMessage, chatId, userName);
            }
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            botAnswerUtils(receivedMessage, chatId, userName);
        }

        }


    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        String mat=receivedMessage;

       /* switch (receivedMessage){
            case "/start":
                startBot(chatId, userName);
                break;
            case "/help":
                sendHelpText(chatId, BotCommands.HELP_TEXT);
                break;
            case "/weather":
                sendHelpText(chatId,"Send city name lika as city-{city_name}");
                break;

            default: break;
        }*/
        if(mat.equals("/start")) {
            startBot(chatId, userName);
        }
        if(mat.equals("/help")) {
            sendHelpText(chatId, BotCommands.HELP_TEXT);
        }
        if(mat.equals("/weather")) {
            sendHelpText(chatId,"Send city name lika as city-{city_name}");
        }
        if(mat.matches("city-[a-zA-Z]{2,}")) {
            getWeather(chatId,receivedMessage);
        }
    }
    private void startBot(long chatId, String userName){
        SendMessage message=new SendMessage();
        message.setChatId(chatId);
        message.setText("Hello "+userName+" im weatherBot");
        message.setReplyMarkup(Buttons.inlineMarkup());
        try{
            execute(message);
            log.info("send mes");
        }
        catch(TelegramApiException e){
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }
    private void getWeather(long chatId,String cityname){
        String city=cityname.substring(5);
        String Key="d925004b7ce15c9b68ab903c773a1165";
        String Url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+Key;
        OkHttpClient client=new OkHttpClient();


        Request request=new Request.Builder().url(Url).build();
        BWeather weather=null;
        try {
            Response response = client.newCall(request).execute();
            String ansver = response.body().string();
            ObjectMapper objectMapper=new ObjectMapper();
            //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            weather=objectMapper.readValue(ansver,BWeather.class);
            BWeather weather1=new BWeather();



        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }
        //double temp=(weather.main.temp-32)/1.8;





        double temp=Math.round(weather.main.temp-273.15);


        SendMessage message=new SendMessage();
        message.setChatId(chatId);
        message.setText("Temperature in "+city+" "+temp);

        try{
            execute(message);
            log.info("send mes");
        }
        catch(TelegramApiException e){
            log.error(e.getMessage());
        }


    }
}
enum BotState{
    ADD_KEYWORDS;
}
