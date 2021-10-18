package com.example.noteapp.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.Activity.ViewNoteActivity;
import com.example.noteapp.MainActivity;
import com.example.noteapp.Model.Note;
import com.example.noteapp.R;

import java.util.List;

public class Note_Adapter extends RecyclerView.Adapter<Note_Adapter.noteViewHolder> {
    MainActivity mainActivity;
    List<Note> notes;



    public Note_Adapter(MainActivity mainActivity, List<Note> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }


    @Override
    public noteViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new noteViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_note,parent,false));
    }

    @Override
    public void onBindViewHolder( Note_Adapter.noteViewHolder holder, int position) {

        Note note = notes.get(notes.size()-position-1);
        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.date.setText(note.notesDate);
        if (note.notesPriority.equals("3")){
            holder.notepro.setBackgroundResource(R.drawable.red_shae);
        }if (note.notesPriority.equals("2")){
            holder.notepro.setBackgroundResource(R.drawable.green_shae);
        }if (note.notesPriority.equals("1")){
            holder.notepro.setBackgroundResource(R.drawable.yellow_shae);
        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, ViewNoteActivity.class);
            intent.putExtra("id", note.id);
            //Log.d("bbbb", String.valueOf(note.id));
            intent.putExtra("notetitle", note.notesTitle);
            intent.putExtra("notesubtitle", note.notesSubtitle);
            intent.putExtra("notepriority", note.notesPriority);
            intent.putExtra("notedetail", note.notes);
            intent.putExtra("notedate", note.notesDate);
            //holder.itemView.setAnimation(AnimationUtils.loadAnimation(mainActivity,R.anim.zoom));
            mainActivity.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    static class noteViewHolder extends RecyclerView.ViewHolder{
        TextView title, subtitle, date;
        View notepro;

        public noteViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notetitle);
            subtitle = itemView.findViewById(R.id.notesubtitle);
            date = itemView.findViewById(R.id.notedate);
            notepro= itemView.findViewById(R.id.noteproti);
        }
    }
}
