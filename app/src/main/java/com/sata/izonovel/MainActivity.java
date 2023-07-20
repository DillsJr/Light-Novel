package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sata.izonovel.Database.DatabaseClient;
import com.sata.izonovel.Database.model.Session;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView menuFavorite, menuInfoPengguna, menuDaftarNovel, menuInputNovel, userName, userEmail;
    LinearLayout htfLL, tbateLL, tbLL, lookLL;
    Button btnSignOut, btnSignIn;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.tv_user);
        userEmail = findViewById(R.id.tv_email);

        menuInfoPengguna = findViewById(R.id.informasi_pengguna);
        menuInfoPengguna.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(MainActivity.this, InfoUserActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuFavorite = findViewById(R.id.favorite_more);
        menuFavorite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, FavoritActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuDaftarNovel = findViewById(R.id.daftar_novel);
        menuDaftarNovel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, DaftarNovelActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuInputNovel = findViewById(R.id.tvInputNovel);
        menuInputNovel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, FormInptActivity.class);
                startActivity(intent);
                return false;
            }
        });

        htfLL = findViewById(R.id.htf);
        htfLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailNovelActivity.class);

                intent.putExtra("id", "1");
                intent.putExtra("judul", "How To Fight");
                intent.putExtra("sinopsis", "Hobin, pecundang di sekolah, tidak sengaja menemukan suatu channel lama di Newtube dan sejak hari itu hidupnya berubah. Apakah Hobin bisa belajar cara berkelahi dan membuat konten yang bagus?");


                startActivity(intent);
            }
        });

        tbateLL = findViewById(R.id.tbate);
        tbateLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailNovelActivity.class);

                intent.putExtra("id", "2");
                intent.putExtra("judul", "The Beginning After The End");
                intent.putExtra("sinopsis", "Menceritakan kisah dari King Grey, penguasa dari dunia yang diperintah dengan kehebatan bela diri seseorang. Meskipun King Grey telah memiliki kekuatan, kekayaan, prestise dan semua hal yang diimpikan oleh setiap orang di dunianya, dia malah merasakan kesendirian yang mendalam. Hingga pada akhirya beliau meninggal dan bagun menuju isekai..");

                startActivity(intent);
            }
        });

        tbLL = findViewById(R.id.tb);
        tbLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailNovelActivity.class);

                intent.putExtra("id", "3");
                intent.putExtra("judul", "The Boxer");
                intent.putExtra("sinopsis", "Anda memiliki bakat mentah. Atau Anda yang diunggulkan. Tapi apakah kamu punya hati? Apakah Anda memiliki apa yang diperlukan? Mari kita lihat Anda terbuat dari apa. Pertarungan sesungguhnya dimulai sekarang!");

                startActivity(intent);
            }
        });

        lookLL = findViewById(R.id.lookism);
        lookLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailNovelActivity.class);

                intent.putExtra("id", "4");
                intent.putExtra("judul", "Lookism");
                intent.putExtra("sinopsis", "Mengisahkan tentang seorang remaja laki-laki bernama Park Hyung Seok yang mengalami bully penampilannya yang dianggap tidak menarik. Bully yang begitu parah membuatnya memutuskan untuk pindah rumah dan sekolah. Cowok ini punya 2 wujud! Pilih yang mana ya..?");

                startActivity(intent);
            }
        });



        getSessions();

        btnSignOut = findViewById(R.id.btn_sign_out);
        btnSignIn = findViewById(R.id.btn_signin);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseClient.getInstance(MainActivity.this).getAppDatabase()
                                .dataBaseAction().clearSessionList();

                    }
                });

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void NovelClick(View view) {
        Intent intent = new Intent(MainActivity.this, DetailNovelActivity.class);
        startActivity(intent);
    }


    private void getSessions() {
        class GetSessions extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                session = DatabaseClient.getInstance(MainActivity.this).getAppDatabase()
                        .dataBaseAction().getSessions();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(session != null){
                    btnSignOut.setVisibility(View.VISIBLE);
                    userName.setVisibility(View.VISIBLE);
                    userEmail.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.INVISIBLE);

                    userName.setText(session.getFullName());
                    userEmail.setText(session.getName());
                }else{
                    userName.setVisibility(View.INVISIBLE);
                    userEmail.setVisibility(View.INVISIBLE);
                    btnSignOut.setVisibility(View.INVISIBLE);

                    btnSignIn.setVisibility(View.VISIBLE);
                }

            }
        }

        GetSessions getSessions = new GetSessions();
        getSessions.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // getSessions();
    }
}