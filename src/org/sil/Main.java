package org.sil;

import org.fcbh.callback.BiblesCallback;

public class Main {

    public static void main(String[] args) {
        BiblesCallback controller = new BiblesCallback();
        controller.setApiKey("53355c32fca5f3cac4d7a670d2df2e09");
        controller.start();
    }
}
