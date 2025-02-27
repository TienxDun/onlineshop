package com.example.onlineshop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.adapter.ChatAdapter;

import java.util.ArrayList;

public class ChatSupportActivity extends AppCompatActivity {
    RecyclerView recyclerChat;
    EditText edtMessage;
    Button btnSend;
    ArrayList<String> messageList;
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_support);

        recyclerChat = findViewById(R.id.recyclerChat);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);
        messageList = new ArrayList<>();

        chatAdapter = new ChatAdapter(messageList);
        recyclerChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerChat.setAdapter(chatAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edtMessage.getText().toString();
                if (!message.isEmpty()) {
                    messageList.add(message);
                    chatAdapter.notifyDataSetChanged();
                    edtMessage.setText("");
                }
            }
        });
    }
}
