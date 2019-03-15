package com.example.wetatch2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private final static String TEXT_CONTENT = "В 2014 году на конференции был представлен новый подход к дизайну приложений. " +
            "Это попытка сделать единообразный интерфейс для всех приложений Google, " +
            "неважно где они работают на телефоне, планшете или компьютере. " +
            "А также для всех Андроид приложений. Данный стиль основан на размещении плоской бумаги на экране. " +
            "Бумага тонкая, плоская, но расположенная в трехмерном пространстве, с тенями, с движением. " +
            "Такую бумагу называют квантумной, или цифровой. Если происходит анимация, " +
            "то она и показывает пользователю, что происходит. Однако чрезмерная анимация не нужна, " +
            "никому не интересно ждать пару секунд, пока окно с сообщением налетается по экрану.\n";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        TextView content = findViewById(R.id.content);
//        content.setText(TEXT_CONTENT);

        FloatingActionButton fab = findViewById(R.id.fab);
        //обработка плавающей кнопки
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Вы нажали на плавающую кнопку", Snackbar.LENGTH_LONG).show();
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // установим аниматор по умолчанию
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // эта установка служит для повышения производительности системы.
        recyclerView.setHasFixedSize(true);

        // будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // создаем источник данных
        DataSourceBuilder builder = new DataSourceBuilder(getResources());
        final List<CardData> dataSource = builder.build();
        // установим адаптер
        final WetatchAdapter adapter = new WetatchAdapter(dataSource);
        recyclerView.setAdapter(adapter);

        // установить слушателя
        adapter.SetOnItemClickListener(new WetatchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, getString(R.string.position, position),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (id) {
            case R.id.action_settings:
                Snackbar.make(toolbar, "Вы выбрали пункт меню установки", Snackbar.LENGTH_LONG)
                        .setAction("Кнопка", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,
                                        "Кнопка в Snackbar нажата", Toast.LENGTH_LONG).show();
                            }
                        }).show();
                return true;

            case R.id.action_preferences:
                Snackbar.make(toolbar, "Вы выбрали пункт меню настройки",
                        Snackbar.LENGTH_LONG).show();
                return true;

            case R.id.action_params:
                Snackbar.make(toolbar, "Вы выбрали пункт меню параметры",
                        Snackbar.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
