package com.yuriysurzhykov.camellia.ui;

import com.yuriysurzhykov.camellia.declaration.OnInputCallback;

import javax.swing.*;

import static com.yuriysurzhykov.camellia.ui.UiConst.*;

public class Common {
    public static void showErrorMessage() {
        showErrorMessage(FILE_OPEN_ERROR_MESSAGE);
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, message, JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(null, message, message, JOptionPane.PLAIN_MESSAGE);
    }

    public static void showInfoMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public static void showInputDialog(OnInputCallback callback) {
        String value = JOptionPane.showInputDialog(INPUT_DIALOG_MESSAGE);
        if (value != null && !value.isEmpty()) {
            callback.onInput(value);
        } else {
            showErrorMessage(EMPTY_KEY_ERROR_MESSAGE);
        }
    }
}
