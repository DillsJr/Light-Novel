package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sata.izonovel.Database.DatabaseClient;
import com.sata.izonovel.Database.model.Session;

public class InfoUserActivity extends AppCompatActivity {

    TextView userName, userEmail;
    Button btnSignOut;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        userName = findViewById(R.id.tv_user);

        getSessions();


        btnSignOut = findViewById(R.id.btn_sign_out);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseClient.getInstance(InfoUserActivity.this).getAppDatabase()
                                .dataBaseAction().clearSessionList();

                    }
                });

                Intent intent = new Intent(InfoUserActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    private void getSessions() {
        class GetSessions<voids> extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                session = DatabaseClient.getInstance(InfoUserActivity.this).getAppDatabase()
                        .dataBaseAction().getSessions();
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                if (session != null){
                    btnSignOut.setVisibility(View.VISIBLE);
                    userName.setVisibility(View.VISIBLE);

                    userName.setText(View.VISIBLE);
                } else {
                    userName.setVisibility(View.VISIBLE);
                    btnSignOut.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}