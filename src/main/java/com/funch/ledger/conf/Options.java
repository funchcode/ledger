package com.funch.ledger.conf;

public enum Options {
    CASH('H'), CARD('C');

    private char options;
    Options(char options) {
        this.options = options;
    }

    public char value() {
        return this.options;
    }

    public static boolean validation(char option) {
        Options[] options = Options.values();
        for(Options op : options) {
            if(op.value() == option) {
                return true;
            }
        }
        return false;
    }
}
