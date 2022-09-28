package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    public static final ArrayList<Note> notes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes=findViewById(R.id.recyclerViewNotes);
        if(notes.isEmpty()) {
            notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
            notes.add(new Note("Баскетбол", "Игра с командой", "Вторник", 3));
            notes.add(new Note("Магазин", "Купить новые джинсы", "Понедельник", 1));
            notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
            notes.add(new Note("Баскетбол", "Игра с командой", "Вторник", 3));
            notes.add(new Note("Магазин", "Купить новые джинсы", "Понедельник", 1));
        }
        NotesAdapter adapter=new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void OnNoteClick(int position) {
                Toast.makeText(MainActivity.this, "Номер позиции: "+position, Toast.LENGTH_SHORT).show();
                //notes.remove(position);
               // adapter.notifyDataSetChanged();
            }

            @Override
            public void OnLongClick(int position) {
                notes.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);

    }
}