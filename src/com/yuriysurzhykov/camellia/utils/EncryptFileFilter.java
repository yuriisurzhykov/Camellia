package com.yuriysurzhykov.camellia.utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

import static com.yuriysurzhykov.camellia.ui.UiConst.FILE_FILTER_ENCRYPT;
import static com.yuriysurzhykov.camellia.utils.Utils.TEXT_FILE_EXT;

public class EncryptFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return f.getName().endsWith(TEXT_FILE_EXT) || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return FILE_FILTER_ENCRYPT;
    }
}
