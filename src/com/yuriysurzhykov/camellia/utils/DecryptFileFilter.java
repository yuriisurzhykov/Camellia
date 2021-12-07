package com.yuriysurzhykov.camellia.utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

import static com.yuriysurzhykov.camellia.ui.UiConst.FILE_FILTER_DECRYPT;

public class DecryptFileFilter extends FileFilter {

    public static DecryptFileFilter getInstance() {
        return new DecryptFileFilter();
    }

    private DecryptFileFilter() {
    }

    @Override
    public boolean accept(File f) {
        return f.getName().endsWith(Utils.CIPHER_FILE_EXT) || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return FILE_FILTER_DECRYPT;
    }
}
