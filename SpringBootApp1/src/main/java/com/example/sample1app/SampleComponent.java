package com.example.sample1app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SampleComponent {
    // private String message = "default message";

    // 引数でmessageを指定
    // プロパティファイルがUTF-8エンコーディングじゃないので文字化けするが、本質じゃないので一旦放置
    @Value("${sample1app.samplecomponent.message:default value}")
    private String message;

    public SampleComponent(){
        super();
    }

    public String message(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
