package ru.yaropolk.mockkafkaclient.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ru.yaropolk.mockkafkaclient.Service.KafkaProducerService;


public class HelloController {
    public KafkaProducerService kafkaProducerService;
    @FXML
    private Label welcomeText;
    @FXML
    private TextArea messageText;
    @FXML
    private TextField bootstrapList;

    @FXML
    private TextField topicName;

    @FXML
    private TextField keyText;
    @FXML
    private TextArea terminal;
    @FXML
    private Label status;

    @FXML
    protected void onConnectClick(){
        try {
            this.kafkaProducerService = new KafkaProducerService(bootstrapList.getText());
            status.setText("Подключено!");
            terminal.setText("Подключено!");
            status.setTextFill(Color.color(0, 1, 0));

        } catch (Exception e){
            terminal.setWrapText(true);
            terminal.setText("Ошибка подключения к топику: " + e.getLocalizedMessage());
            status.setText("Отключено");
            status.setTextFill(Color.color(1, 0, 0));
            e.printStackTrace();
        }
    }

    @FXML
    protected void onDisconnectionClick(){
        try {
            kafkaProducerService.Disconnect();
            status.setText("Отключено :(");
            status.setTextFill(Color.color(1, 0, 0));

        } catch (Exception e){
            terminal.setWrapText(true);
            terminal.setText("Ошибка отключения от топика: " + e.getLocalizedMessage());
            status.setText("Отключено");
            status.setTextFill(Color.color(1, 0, 0));
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSendMessageClick(){
        try {
            kafkaProducerService.SendMessage(topicName.getText(), keyText.getText(), messageText.getText());
            status.setText("Отправлено!");
            terminal.setText("Отправлено");
            status.setTextFill(Color.color(0, 0, 1));

        } catch (Exception e){
            terminal.setWrapText(true);
            terminal.setText("Ошибка отправки сообщения: " + e.getMessage());
            status.setText("Отключено");
            status.setTextFill(Color.color(1, 0, 0));
            e.printStackTrace();
        }
    }
}