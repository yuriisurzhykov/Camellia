package com.yuriysurzhykov.camellia;

import com.yuriysurzhykov.camellia.ui.DecryptButtonHandler;
import com.yuriysurzhykov.camellia.ui.EncryptButtonHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static com.yuriysurzhykov.camellia.ui.UiConst.*;

public class Main extends Application {

    private final GridPane root = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(true);
        primaryStage.setTitle(WINDOW_NAME);
        initDisplay();
        primaryStage.setScene(new Scene(root, Color.TRANSPARENT));
        primaryStage.show();
    }

    private void initDisplay() {
        root.setPadding(new Insets(DEFAULT_PADDING));
        root.setAlignment(Pos.CENTER);
        Button encryptButton = new Button(ENCRYPT_BTN);
        encryptButton.setPadding(new Insets(BUTTON_PADDING));
        encryptButton.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        encryptButton.setOnMouseClicked(new EncryptButtonHandler());
        encryptButton.setAlignment(Pos.CENTER);
        root.add(encryptButton, 0, 1);

        Button decryptButton = new Button(DECRYPT_BTN);
        decryptButton.setPadding(new Insets(BUTTON_PADDING));
        decryptButton.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        decryptButton.setOnMouseClicked(new DecryptButtonHandler());
        decryptButton.setAlignment(Pos.CENTER);
        root.add(decryptButton, 0, 2);
    }
}
