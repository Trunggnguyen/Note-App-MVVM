package com.example.noteapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.noteapp.R;
import com.example.noteapp.ViewModel.NoteViewModel;
import com.example.noteapp.databinding.ActivityInsertNoteBinding;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.example.noteapp.databinding.ActivityViewNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ViewNoteActivity extends AppCompatActivity {

    ActivityViewNoteBinding binding;
    Intent intent;
    int id;
    String notetitle;
    String notesubtitle;
    String notedetail;
    String notepriority;
    String notedate;
    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        intent = getIntent();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        id = intent.getIntExtra("id", 1);
        notetitle = intent.getStringExtra("notetitle");
        notesubtitle = intent.getStringExtra("notesubtitle");
        notepriority = intent.getStringExtra("notepriority");
        notedetail = intent.getStringExtra("notedetail");
        notedate = intent.getStringExtra("notedate");


        binding.updatnotetitle.setText(notetitle);
        binding.updatnotesubtitle.setText(notesubtitle);
        binding.updatenote.setText(notedetail);
        binding.datenote.setText("Cập nhật gần nhất: " + notedate);

        binding.updatenotesbt.setOnClickListener(this::onClick);
        binding.backbt.setOnClickListener(v -> {
            finish();
        });



    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    private void onClick(View v) {
        Intent intent1 = new Intent(ViewNoteActivity.this, UpdateNoteActivity.class);

        intent1.putExtra("id", id);
        intent1.putExtra("notetitle", notetitle);
        intent1.putExtra("notesubtitle", notesubtitle);
        intent1.putExtra("notepriority", notepriority);
        intent1.putExtra("notedetail", notedetail);

        startActivityForResult(intent1, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK) {

                id = data.getIntExtra("id", 1);
                String newnotetitle = data.getStringExtra("notetitle");
                String newnotesubtitle = data.getStringExtra("notesubtitle");
                String newnotepriority = data.getStringExtra("notepriority");
                String newnotedetail = data.getStringExtra("notedetail");
                String newnotedate = data.getStringExtra("notedate");


                binding.updatnotetitle.setText(newnotetitle);
                binding.updatnotesubtitle.setText(newnotesubtitle);
                binding.updatenote.setText(newnotedetail);
                binding.datenote.setText("Cập nhật gần nhất: " + newnotedate);

                Log.d("bbbb", String.valueOf(newnotetitle));

            }
        }
        if (resultCode == RESULT_CANCELED) {
            //   mTextViewResult.setText("Nothing selected");

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            BottomSheetDialog dialog = new BottomSheetDialog(ViewNoteActivity.this);
            View view = LayoutInflater.from(ViewNoteActivity.this).inflate(R.layout.delete_diaog,findViewById(R.id.delete_brlliner));
            dialog.setContentView(view);

            TextView yes, no;
            yes = view.findViewById(R.id.yes);
            no = view.findViewById(R.id.no);

            yes.setOnClickListener(v -> {
               noteViewModel.deleteNote(id);
               finish();

            });
            no.setOnClickListener(v -> {
                dialog.dismiss();
            });
            dialog.show();
        }
        return true;
    }
}