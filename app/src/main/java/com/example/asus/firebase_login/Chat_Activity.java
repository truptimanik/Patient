package com.example.asus.firebase_login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Chat_Activity extends ActionBarActivity implements View.OnClickListener,
        MessageDataSource.MessagesCallbacks{
    public static final String USER_EXTRA = "USER";

    public static final String TAG = "ChatActivity";

    private ArrayList<Message> mMessages;
    private MessagesAdapter mAdapter;
    private String mRecipient;
    private ListView mListView;
    private Date mLastMessageDate = new Date();
    private String mConvoId;
    private MessageDataSource.MessagesListener mListener;

    private FirebaseAuth firebaseAuth;
 //   Intent intent = getIntent();
//    String pat = intent.getStringExtra("UID");
        String pat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);
       // Intent intent = getIntent();
        //pat = intent.getStringExtra("UID");


        firebaseAuth= FirebaseAuth.getInstance();

        // CHANGE THIS, IT IS NOT TAKING CAPITAL LETTERS, SO GET IT PASSED FROM MENU SELECTION

        String USER = firebaseAuth.getCurrentUser().getEmail();
        final String user1 = USER.toString();
        String inter = user1.replace('.','-');
        pat = inter.replace('@','-');



        mRecipient = "Doc";

        mListView = (ListView)findViewById(R.id.messages_list);
        mMessages = new ArrayList<>();
        mAdapter = new MessagesAdapter(mMessages);
        mListView.setAdapter(mAdapter);

        setTitle(mRecipient);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button sendMessage = (Button)findViewById(R.id.send_message);
        sendMessage.setOnClickListener(this);

        String[] ids = {"Doc","-", "patient"};
        Arrays.sort(ids);
      //  mConvoId = ids[0]+ids[1]+ids[2];
        mConvoId = "Chat";
        mListener = MessageDataSource.addMessagesListener(pat, this);
    //    mListener = MessageDataSource.addMessagesListener(pat, this);

    }

    public void onClick(View v) {
        EditText newMessageView = (EditText)findViewById(R.id.new_message);
        String newMessage = newMessageView.getText().toString();
        newMessageView.setText("");
        Message msg = new Message();
        msg.setDate(new Date());
        msg.setText(newMessage);
        msg.setSender("patient");

        MessageDataSource.saveMessage(msg,"Chat",pat);
    }


    @Override
    public void onMessageAdded(Message message) {
        mMessages.add(message);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageDataSource.stop(mListener);
    }

    private class MessagesAdapter extends ArrayAdapter<Message> {
        MessagesAdapter(ArrayList<Message> messages) {
            super(Chat_Activity.this, R.layout.message, R.id.message, messages);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            Message message = getItem(position);

            TextView nameView = (TextView) convertView.findViewById(R.id.message);
            nameView.setText(message.getText());

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nameView.getLayoutParams();

            int sdk = Build.VERSION.SDK_INT;
         //   if (message.getSender().equals("patient"))
           if( message.getSender().equals("patient")){

                TextView name = (TextView)convertView.findViewById(R.id.message_user);
                name.setGravity(Gravity.RIGHT);
                if (sdk >= Build.VERSION_CODES.JELLY_BEAN) {
                    nameView.setBackground(getDrawable(R.drawable.bubble_right_green));
                } else {
                    nameView.setBackgroundDrawable(getDrawable(R.drawable.bubble_right_green));
                }
                layoutParams.gravity = Gravity.RIGHT;
            } else {
                TextView name = (TextView)convertView.findViewById(R.id.message_user);
                name.setGravity(Gravity.LEFT);
                if (sdk >= Build.VERSION_CODES.JELLY_BEAN) {
                    nameView.setBackground(getDrawable(R.drawable.bubble_left_gray));
                } else {
                    nameView.setBackgroundDrawable(getDrawable(R.drawable.bubble_left_gray));
                }
                layoutParams.gravity = Gravity.LEFT;
            }

            nameView.setLayoutParams(layoutParams);


            return convertView;
        }

    }

    }
