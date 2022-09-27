package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String IspolName, songName;
    int AlbomName;

    private void next() {
        Intent intent = new Intent(this, MusicPlayer.class);
        startActivity(intent);

        intent.putExtra("isolnit", IspolName);
        intent.putExtra("song", songName);

    }

    ArrayList<Muzic> musics = new ArrayList<Muzic>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.list);
        // определяем слушателя нажатия элемента в списке
        MusicList.OnStateClickListener stateClickListener = new MusicList.OnStateClickListener() {
            @Override
            public void onStateClick(Muzic state, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + state.getName(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MusicPlayer.class);

                intent.putExtra("isolnit", musics.get(position).getIsop());
                intent.putExtra("song", musics.get(position).getName());
                startActivity(intent);


            }

        };
        // создаем адаптер
        MusicList adapter = new MusicList(this, musics, stateClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }
    public void setInitialData(){

        musics.add(new Muzic ("alena", "Луна", R.drawable.alena));
        musics.add(new Muzic ("bts", "Shut", R.drawable.bts));
        musics.add(new Muzic ("etoegr", "Не твой", R.drawable.etoegr));
        musics.add(new Muzic ("grechka", "Автомат", R.drawable.grechka));
        musics.add(new Muzic ("macs", "Подъезд", R.drawable.macs));
    }


}

