package com.example.chamtracnghiem.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AnswerKey {
    static private HashMap<String,String> key = new HashMap<>();

    public void add(Question question){
        key.put(Integer.toString(question.id),Integer.toString(question.answer));
    }

    public JSONObject getJSONAnswerKey() throws JSONException {
        JSONObject jsonKey = new JSONObject();
        if (key.size()!=0){
            for (String question:key.keySet()){
                jsonKey.put(question, key.get(question));
            }
        }
        return jsonKey;
    }

}
