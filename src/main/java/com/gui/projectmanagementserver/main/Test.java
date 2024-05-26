package com.gui.projectmanagementserver.main;

import java.util.Timer;
import java.util.TimerTask;

public class Test {

    private int countdownSeconds = 60;
    private Timer countdownTimer;

    public boolean is_valid = true ;

    public void startCountdown() {
        countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                countdownSeconds--;
                if (countdownSeconds <= 0) {
                    countdownTimer.cancel();
                    is_valid = false ;
                }
            }
        }, 0, 1000); // Lặp lại mỗi giây (1000 milliseconds)
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.startCountdown();
    }
}
