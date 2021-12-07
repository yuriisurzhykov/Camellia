package com.yuriysurzhykov.camellia.ui;

import com.yuriysurzhykov.camellia.declaration.IDecoder;
import com.yuriysurzhykov.camellia.impl.ByteEncoder;
import com.yuriysurzhykov.camellia.impl.CamelliaDecoder;
import com.yuriysurzhykov.camellia.impl.ConsoleWriter;
import com.yuriysurzhykov.camellia.utils.DecryptFileFilter;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;

import static com.yuriysurzhykov.camellia.ui.Common.*;
import static com.yuriysurzhykov.camellia.ui.UiConst.*;

public class DecryptButtonHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        IDecoder decoder = new CamelliaDecoder();
        JFileChooser decryptOpen = new JFileChooser();
        decryptOpen.setFileFilter(DecryptFileFilter.getInstance());
        int ret = decryptOpen.showDialog(null, DECRYPT_DIALOG_TITLE);
        if (ret == JFileChooser.APPROVE_OPTION) {
            showInputDialog(input -> {
                try {
                    ConsoleWriter writer = new ConsoleWriter(new ByteEncoder());
                    if (decoder.decrypt(decryptOpen.getSelectedFile().toString(), input, writer)) {
                        showInfoMessage(FILE_DECRYPTED_INFO_MESSAGE, DECRYPTED_PREFIX + writer.getText());
                    } else {
                        showErrorMessage(FILE_DECRYPTED_ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    showErrorMessage();
                }
            });
        }
    }
}
