package com.example.test_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.test.MESSAGE";

    // https://androman.tistory.com/105
    // 채널 생성하기
    private static final String PRIMARY_CHANNEL_ID = "notify_channel";
    private NotificationManager notificationManager;
    // Notification에 대한 ID 생성
    private static final int NOTIFICATION_ID = 0;
    // Notification을 호출 할 button 변수
    private Button button_notify;

    private Button move_timepicker;
    private Button sidebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // push 알람 버튼 listener 등록
        button_notify = findViewById(R.id.notify);
        button_notify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        createNotificationChannel();

        // timepicker
        move_timepicker = findViewById(R.id.timepicker_view);
        move_timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotificationDetail.class);
                startActivity(intent);
            }
        });

        // sidebar 템플릿 확인
        sidebar = findViewById(R.id.sidebar);
        sidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
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

    // 채널 생성 메소드
    private void createNotificationChannel() {
        try{
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Channel 정의 생성자 (채널 아이디, 채널 이름, 중요도)
                NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Test", notificationManager.IMPORTANCE_HIGH);
                // Channel 에 대한 기본 설정
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setDescription("테스트다");

                notificationManager.createNotificationChannel(notificationChannel);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    // Notification Builder 생성 후 NotificationManager 로 전달하여 호출
    public void sendNotification(){
        try{
            // 알림 탭 설정
            Intent intent = new Intent(this, NotificationDetail.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notificatioin_watch)
                    .setContentTitle("커밋해야지")
                    .setContentText("커밋해야지")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setContentIntent(pendingIntent)
                    .setFullScreenIntent(pendingIntent, true)
                    .setAutoCancel(true);
            notificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}