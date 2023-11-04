package org.windhand.timer;

public enum Tips {

    WORKOUT("I suggest squeezing 10 times");

    private final String tip;

    Tips(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }
}
