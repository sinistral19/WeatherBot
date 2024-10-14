package com.project.WeatherBot.components;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {

    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info"),
             new BotCommand("/weather", "get weather")
    );

    String HELP_TEXT = "/start - start the bot\n" +
            "/help - help menu\n"+
            "/weather - weather menu\n";
}