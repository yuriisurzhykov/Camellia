package com.yuriysurzhykov.camellia.ui;

import static com.yuriysurzhykov.camellia.utils.Utils.CIPHER_FILE_EXT;
import static com.yuriysurzhykov.camellia.utils.Utils.TEXT_FILE_EXT;

public class UiConst {

    public static final int DEFAULT_PADDING = 50;
    public static final int BUTTON_PADDING = 15;

    public static final String WINDOW_NAME = "Шифр Camellia ";
    public static final String ENCRYPT_BTN = "Шифровать";
    public static final String DECRYPT_BTN = "Расшифровать";

    public static final String ENCRYPT_DIALOG_TITLE = "Открыть файл для шифрования";
    public static final String DECRYPT_DIALOG_TITLE = "Открыть файл для дешифрования";
    public static final String INPUT_DIALOG_MESSAGE = "Введите ключ: ";

    public static final String FILE_OPEN_ERROR_MESSAGE = "Не удалось открыть файл!";
    public static final String EMPTY_KEY_ERROR_MESSAGE = "Вы не ввели ключ!";

    public static final String FILE_ENCRYPTED_INFO_MESSAGE = "Файл успешно зашифрован!";
    public static final String FILE_ENCRYPTED_ERROR_MESSAGE = "Файл не удалось зашифровать!";
    public static final String FILE_DECRYPTED_INFO_MESSAGE = "Файл успешно расшифрован!";
    public static final String FILE_DECRYPTED_ERROR_MESSAGE = "Файл не удалось расшифровать!";

    public static final String DECRYPTED_PREFIX = "Текст файла:\n";

    public static final String FILE_FILTER_ENCRYPT = "File to encrypt " + TEXT_FILE_EXT;
    public static final String FILE_FILTER_DECRYPT = "File to decrypt  " + CIPHER_FILE_EXT;
}
