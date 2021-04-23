package com.example.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.test.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Send 버튼 클릭시 호출*/
    public void sendMessage(View view){

        //this : pakageContext
        /**
         인텐트는 컴포넌트(액티비티, 서비스, 브로드캐스트 리시버)간에 통신을 하기 위한 메시지 객체
         - 액티비티의 시작: startActivity(Intent), startActivityForResult(Intent, requestCode)
         - 서비스의 시작: startService(Intent), bindService(Intent)
         - 브로드케스트 전달: sendBroadcast(Intent), sendOrderedBroadcast(Intent), sendStickyBroadcast()
         **/
        Intent intent = new Intent(this,  DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}