package com.example.chamtracnghiem.model;

import android.widget.RadioGroup;
import android.widget.TextView;

public class Question {
    public int id;
    public int answer;

    public Question(int id) {
        this.id = id;
        this.answer = -1;
    }
}
