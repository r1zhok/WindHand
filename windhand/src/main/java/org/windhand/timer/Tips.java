package org.windhand.timer;

public enum Tips {

    WORKOUT_SQUEEZING("I suggest squeezing 10 times"),
    WORKOUT_SQUATTING("I suggest squatting 15 times"),
    TRY_REST("I suggest rest 20 minutes");


    private final String tip;

    Tips(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }
}
