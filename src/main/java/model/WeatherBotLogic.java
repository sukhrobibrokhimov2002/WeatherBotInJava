package model;

import info.WeatherClass;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.xml.ws.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherBotLogic extends TelegramLongPollingBot {
    int level = 0;
    public static final String BOTTOKEN = "1871601506:AAHCvOWpvRAoUj-CZnWapTziYtMIWL4PBxg";
    public static final String BOTUSERNAME = "weatherUzbekistan_bot";
    public static String text;

    public String getBotUsername() {
        return BOTUSERNAME;
    }

    public String getBotToken() {
        return BOTTOKEN;
    }

    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();
        text = message.getText();
        Long chatId = message.getChatId();
        sendMessage.setChatId(chatId);
            System.out.println(message.getFrom().getFirstName());
        if (text.equalsIgnoreCase("/start")) {
            language(sendMessage);
            sendMessage.setText("Salom "+message.getFrom().getFirstName()+"\uD83D\uDE01   ob havo\uD83C\uDF26 botimizga xush kelibsiz"+"\n\nTilni tanlang\uD83C\uDDFA\uD83C\uDDFF \n\nChoose the language\uD83C\uDDEC\uD83C\uDDE7" +
                    "\n\nВыберите язык\uD83C\uDDF7\uD83C\uDDFA");
            level = 1;
        }
        switch (level) {
            case 1:
                if (text.equalsIgnoreCase("O'zbek tili\uD83C\uDDFA\uD83C\uDDFF")) {
                    sendMessage.setText("Shaharni tanlang");
                    cityUz(sendMessage);
                    level=2;
                } else if (text.equalsIgnoreCase("Русский\uD83C\uDDF7\uD83C\uDDFA")) {
                    sendMessage.setText("Выберите город");
                    cityRu(sendMessage);
                    level=3;
                } else if (text.equalsIgnoreCase("English\uD83C\uDDEC\uD83C\uDDE7")) {
                    sendMessage.setText("Choose the Region");
                    cityEn(sendMessage);
                    level=4;
                }
                break;
            case 2:
                if(text.equalsIgnoreCase("\uD83D\uDD19")){
                    sendMessage.setText("Bot tilini tanlang");
                    language(sendMessage);
                    level=1;
                }
                else {
                    try {
                        weatherInfo(sendMessage,1);
                        level=5;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case 3:
                if(text.equalsIgnoreCase("\uD83D\uDD19")){
                    sendMessage.setText("Bot tilini tanlang");
                    language(sendMessage);
                    level=1;
                }else {
                    try {
                        weatherInfo(sendMessage,2);
                        level=5;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case 4:
                if(text.equalsIgnoreCase("\uD83D\uDD19")){
                    sendMessage.setText("Bot tilini tanlang");
                    language(sendMessage);
                    level=1;
                }else {
                    try {
                        weatherInfo(sendMessage,3);
                        level=5;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 5:
                if(text.equalsIgnoreCase("\uD83D\uDD19")){
                    sendMessage.setText("Back");
                    language(sendMessage);
                    level=1;
                }
                break;
        }


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    public void language(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("O'zbek tili\uD83C\uDDFA\uD83C\uDDFF"));
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Русский\uD83C\uDDF7\uD83C\uDDFA"));
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("English\uD83C\uDDEC\uD83C\uDDE7"));
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        keyboardRowList.add(keyboardRow);
        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow2);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public void cityUz(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        KeyboardRow row5 = new KeyboardRow();
        row.add(new KeyboardButton("Andijon"));
        row.add(new KeyboardButton("Buxoro"));
        row1.add(new KeyboardButton("Farg'ona"));
        row1.add(new KeyboardButton("Jizzax"));
        row2.add(new KeyboardButton("Urganch"));
        row2.add(new KeyboardButton("Namangan"));
        row3.add(new KeyboardButton("Navoiy"));
        row3.add(new KeyboardButton("Qarshi"));
        row4.add(new KeyboardButton("Samarqand"));
        row4.add(new KeyboardButton("Sirdaryo"));
        row5.add(new KeyboardButton("Termiz"));
        row5.add(new KeyboardButton("Tashkent"));
        KeyboardRow row6 = new KeyboardRow();
        row6.add(new KeyboardButton("\uD83D\uDD19"));
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        keyboardRowList.add(row);
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);
        keyboardRowList.add(row3);
        keyboardRowList.add(row4);
        keyboardRowList.add(row5);
        keyboardRowList.add(row6);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public void cityRu(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        KeyboardRow row5 = new KeyboardRow();
        row.add(new KeyboardButton("Ташкент"));
        row.add(new KeyboardButton("Андижан"));
        row1.add(new KeyboardButton("Наманган"));
        row1.add(new KeyboardButton("Фергана"));
        row2.add(new KeyboardButton("Гулистан"));
        row2.add(new KeyboardButton("Джизак"));
        row3.add(new KeyboardButton("Самарканд"));
        row3.add(new KeyboardButton("Бухара"));
        row4.add(new KeyboardButton("Навои"));
        row4.add(new KeyboardButton("Карши"));
        row5.add(new KeyboardButton("Термез"));
        row5.add(new KeyboardButton("Ургенч"));
        KeyboardRow row6 = new KeyboardRow();
        row6.add(new KeyboardButton("\uD83D\uDD19"));
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        keyboardRowList.add(row);
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);
        keyboardRowList.add(row3);
        keyboardRowList.add(row4);
        keyboardRowList.add(row5);
        keyboardRowList.add(row6);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public void cityEn(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        KeyboardRow row5 = new KeyboardRow();
        row.add(new KeyboardButton("Tashkent"));
        row.add(new KeyboardButton("Andijan"));
        row1.add(new KeyboardButton("Namangan"));
        row1.add(new KeyboardButton("Fergana"));
        row2.add(new KeyboardButton("Sirdaryo"));
        row2.add(new KeyboardButton("Jizzakh"));
        row3.add(new KeyboardButton("Samarkand"));
        row3.add(new KeyboardButton("Bukhara"));
        row4.add(new KeyboardButton("Navoiy"));;
        row4.add(new KeyboardButton("Qashqadaryo"));
        row5.add(new KeyboardButton("Termez"));
        row5.add(new KeyboardButton("Urganch"));
        KeyboardRow row6 = new KeyboardRow();
        row6.add(new KeyboardButton("\uD83D\uDD19"));
        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        keyboardRowList.add(row);
        keyboardRowList.add(row1);
        keyboardRowList.add(row2);
        keyboardRowList.add(row3);
        keyboardRowList.add(row4);
        keyboardRowList.add(row5);
        keyboardRowList.add(row6);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public void weatherInfo(SendMessage sendMessage, int lang) throws IOException {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("\uD83D\uDD19"));
        List<KeyboardRow> list = new ArrayList<KeyboardRow>();
        list.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(list);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        WeatherUtil.region = text;
        String region = text;
        StringBuilder stringBuilder = new StringBuilder();

        WeatherClass weatherClass=WeatherUtil.getWeatherItems();
        LocalDateTime localDateTime = LocalDateTime.now();
        double kelvin=weatherClass.getMain().getTemp();
        int celcium = (int) (kelvin - 273);
        int celciumMax= (int) (weatherClass.getMain().getTempMax()-273);
        int celciumMin= (int) (weatherClass.getMain().getTempMin()-273);
        if (lang == 1) {
                    stringBuilder.append("\uD83D\uDCCDJoylashuv: " + region)
                            .append("\n\uD83C\uDFE2Davlat: " + weatherClass.getSys().getCountry())
                            .append("\n⏳Vaqt: " + localDateTime)
                            .append("\n\uD83C\uDF26Ob havo holati: "+weatherClass.getWeather().get(0).getMain())
                            .append("\n\uD83C\uDF21Harorat: "+celcium+"©️")
                            .append("\n\uD83C\uDF21Maksimal Harorat: "+celciumMax+"©️")
                            .append("\n\uD83C\uDF21Minimal Harorat: "+celciumMin+"©️")
                            .append("\n\uD83D\uDCA7Namlik: "+weatherClass.getMain().getHumidity()+"%")
                            .append("\n\uD83C\uDF2AShamol tezligi: "+weatherClass.getWind().getSpeed()+"m/s");
            sendMessage.setText(String.valueOf(stringBuilder));
        } else if (lang == 2) {

                    stringBuilder.append("\uD83D\uDCCDЛокация: " + region)
                            .append("\n\uD83C\uDFE2Страна: " + weatherClass.getSys().getCountry())
                            .append("\n⏳Время: " + localDateTime)
                            .append("\n\uD83C\uDF26Погода: "+weatherClass.getWeather().get(0).getMain())
                            .append("\n\uD83C\uDF21Температура: "+celcium+"©️")
                            .append("\n\uD83C\uDF21Максимальная температура: "+celciumMax+"©️")
                            .append("\n\uD83C\uDF21Минимальная температура: "+celciumMin+"©️")
                            .append("\n\uD83D\uDCA7Влажность: "+weatherClass.getMain().getHumidity()+"%")
                            .append("\n\uD83C\uDF2AСкорость ветра: "+weatherClass.getWind().getSpeed()+"m/s");
                    sendMessage.setText(String.valueOf(stringBuilder));
        } else if (lang == 3) {
                    stringBuilder.append("\uD83D\uDCCDLocation: " + region)
                            .append("\n\uD83C\uDFE2Country: " + weatherClass.getSys().getCountry())
                            .append("\n⏳Time: " + localDateTime)
                            .append("\n\uD83C\uDF26Weather condition: "+weatherClass.getWeather().get(0).getMain())
                            .append("\n\uD83C\uDF21Temperature: "+celcium+"©️")
                            .append("\n\uD83C\uDF21Max Temperature: "+celciumMax+"©️")
                            .append("\n\uD83C\uDF21Min Temperature: "+celciumMin+"©️")
                            .append("\n\uD83D\uDCA7Humidity: "+weatherClass.getMain().getHumidity()+"%")
                            .append("\n\uD83C\uDF2ASpeed of wind: "+weatherClass.getWind().getSpeed()+"m/s");

            sendMessage.setText(String.valueOf(stringBuilder));
        }
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

    }


}
