package com.funch.ledger.conf;

public enum Category {

    // 의, 식, 주, 업
    CLOTHING('C'), FOOD('F'), SHELTER('S'), JOB('J');

    private char category;

    Category(char category) {
        this.category = category;
    }

    public char value() {
        return category;
    }

    public static boolean validation(char category) {
        Category[] categories = Category.values();
        for(Category c : categories) {
            if (c.value() == category) {
                return true;
            }
        }
        return false;
    }
}
