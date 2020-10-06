package home.edu.telegabot.bot;

import home.edu.telegabot.exception.IncorrectCityNameException;
import home.edu.telegabot.service.WServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

import static home.edu.telegabot.service.ApiConstans.BOT_TOKEN;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    public String BOT_NAME;

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        String cityName = update.getMessage().getText();
        var service = new WServiceImpl();
        SendMessage send = new SendMessage();
        send.enableMarkdown(true);
        send.setChatId(update.getMessage().getChatId());

        try {
            send.setText(service.getCityName(cityName));
        } catch (NullPointerException e){
            send.setText(new IncorrectCityNameException("Incorrect city name!").getMessage());
        }
        KeyboardRow fkeyboardRow = new KeyboardRow();
        fkeyboardRow.add("Kharkiv");

        KeyboardRow skeyboardRow = new KeyboardRow();
        skeyboardRow.add("Kiev");

        send.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setKeyboard(List.of(fkeyboardRow,skeyboardRow));
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        execute(send);
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }


}
