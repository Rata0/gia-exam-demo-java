package org.example.demofx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.IOException;

public class HelloController {
    @FXML
    private WebView webView;

    @FXML
    private WebEngine webEngine;

    @FXML
    protected void onHelloButtonClick() {
        try {
            Stage stage = new Stage();
            new AddSupplier().start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize() {
        webEngine = webView.getEngine();
        webEngine.load(this.getClass().getResource("index.html").toExternalForm());
        webEngine.setUserStyleSheetLocation(this.getClass().getResource("style.css").toExternalForm());

        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> stringWebEvent) {
                if ("command:ready".equals(stringWebEvent.getData())) {
                    JSObject jsObject = (JSObject) webEngine.executeScript("window");
                    jsObject.setMember("helper", new Helper());
                }
            }
        });
    }
}
