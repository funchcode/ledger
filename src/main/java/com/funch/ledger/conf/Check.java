package com.funch.ledger.conf;

public enum Check {
    YES('Y'), NO('N');

    private char check;

    Check(char check) {
        this.check = check;
    }

    public char value() {
        return check;
    }

    public static boolean validation(char check) {
        Check[] checks = Check.values();
        for(Check c : checks) {
            if (c.value() == check) {
                return true;
            }
        }
        return false;
    }
}
