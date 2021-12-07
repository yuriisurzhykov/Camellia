package com.yuriysurzhykov.camellia.ui;

import com.yuriysurzhykov.camellia.declaration.IEncryptor;
import com.yuriysurzhykov.camellia.impl.CamelliaEncryptor;
import com.yuriysurzhykov.camellia.utils.EncryptFileFilter;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static com.yuriysurzhykov.camellia.ui.Common.*;
import static com.yuriysurzhykov.camellia.ui.UiConst.*;

public class EncryptButtonHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        IEncryptor encryptor = new CamelliaEncryptor();
        JFileChooser fileopen = new JFileChooser();
        fileopen.setFileFilter(new EncryptFileFilter());
        int ret = fileopen.showDialog(null, ENCRYPT_DIALOG_TITLE);
        if (ret == JFileChooser.APPROVE_OPTION) {
            showInputDialog(input -> {
                try {
                    File files = fileopen.getSelectedFile();
                    if (encryptor.encrypt(files.getAbsolutePath(), input)) {
                        showInfoMessage(FILE_ENCRYPTED_INFO_MESSAGE);
                    } else {
                        showErrorMessage(FILE_ENCRYPTED_ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    showErrorMessage();
                }
            });
        }
    }
}
