package com.example.chamtracnghiem.model;

import java.util.HashMap;
import java.util.Map;

public class AnswerKey {
    static private Map<String, String> key = new HashMap<>();

    static void add(Question question){
        key.put(Integer.toString(question.id),Integer.toString(question.answer));
    }

    public static Map<String,String> getKey(){
        return key;
    }

}
