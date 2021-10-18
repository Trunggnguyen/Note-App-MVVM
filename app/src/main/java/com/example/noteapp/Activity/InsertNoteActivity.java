package com.example.noteapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.noteapp.MainActivity;
import com.example.noteapp.Model.Note;
import com.example.noteapp.R;
import com.example.noteapp.ViewModel.NoteViewModel;
import com.example.noteapp.databinding.ActivityInsertNoteBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.noteapp.R.color.red;

public class InsertNoteActivity extends AppCompatActivity {
    String title;
    String subtitle;
    String notes;
    String priority =  "3";
    NoteViewModel noteViewModel;

    ActivityInsertNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.redpriority.setVisibility(View.GONE);
        binding.greenpriority.setVisibility(View.GONE);
        binding.yellowpriority.setVisibility(View.GONE);

        binding.notetitle.setVisibility(View.GONE);
        binding.notesubtitle.setVisibility(View.GONE);
        binding.notenote.setVisibility(View.GONE);
        binding.priority.setVisibility(View.GONE);

        if (savedInstanceState == null) {
            binding.background.setVisibility(View.INVISIBLE);

            final ViewTreeObserver viewTreeObserver = binding.background.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        binding.background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                });
            }

        }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        binding.redpriority.setOnClickListener(v -> {
            priority="1";
            binding.redpriority.setImageResource(R.drawable.ic_baseline_check_24);
            binding.yellowpriority.setImageResource(0);
            binding.greenpriority.setImageResource(0);
        });
        binding.yellowpriority.setOnClickListener(v -> {
            priority="1";
            binding.yellowpriority.setImageResource(R.drawable.ic_baseline_check_24);
            binding.redpriority.setImageResource(0);
            binding.greenpriority.setImageResource(0);
        });
        binding.greenpriority.setOnClickListener(v -> {
            priority="2";
            binding.greenpriority.setImageResource(R.drawable.ic_baseline_check_24);
            binding.redpriority.setImageResource(0);
            binding.yellowpriority.setImageResource(0);
        });
        binding.redpriority.setOnClickListener(v -> {
            priority="3";
            binding.redpriority.setImageResource(R.drawable.ic_baseline_check_24);
            binding.yellowpriority.setImageResource(0);
            binding.greenpriority.setImageResource(0);
        });

        binding.insertnotesbt.setOnClickListener(v -> {

            title= binding.notetitle.getText().toString();
            subtitle = binding.notesubtitle.getText().toString();
            notes = binding.notenote.getText().toString();
            if (title.equals("")){
                Toast.makeText(this, "Title is missing !", Toast.LENGTH_SHORT).show();
            }
            if (subtitle.equals("")){
                Toast.makeText(this, "Subtitle is missing !", Toast.LENGTH_SHORT).show();
            }else {
                createnote( title, subtitle, notes);
            }



        });
    }

    private void createnote(String title, String subtitle, String notes) {


        DateFormat dateFormat= DateFormat.getDateInstance();
        Calendar cal = Calendar.getInstance();
        String curr_date = dateFormat.format(cal.getTime());

        Note note = new Note();
        note.notesTitle=title;
        note.notesSubtitle= subtitle;
        note.notes= notes;
        note.notesDate= curr_date;
        note.notesPriority= priority;


        noteViewModel.insertNote(note);
        Toast.makeText(this, "Create notes succsessfully !", Toast.LENGTH_SHORT).show();
        onBackPressed();


    }
    private void circularRevealActivity() {
        int cx = binding.background.getRight() - getDips(44);
        int cy = binding.background.getBottom() - getDips(44);

        float finalRadius = Math.max(binding.background.getWidth(), binding.background.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                binding.background,
                cx,
                cy,
                0,
                finalRadius);

        circularReveal.setDuration(600);


        binding.background.setVisibility(View.VISIBLE);
        circularReveal.start();

        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                binding.background.setBackgroundResource(R.color.background);

                binding.redpriority.setVisibility(View.VISIBLE);
                binding.greenpriority.setVisibility(View.VISIBLE);
                binding.yellowpriority.setVisibility(View.VISIBLE);

                binding.yellowpriority.setAnimation(AnimationUtils.loadAnimation(InsertNoteActivity.this,R.anim.fade_scale_animation));
                binding.greenpriority.setAnimation(AnimationUtils.loadAnimation(InsertNoteActivity.this,R.anim.fade_scale_animation));
                binding.redpriority.setAnimation(AnimationUtils.loadAnimation(InsertNoteActivity.this,R.anim.fade_scale_animation));

                binding.notetitle.setVisibility(View.VISIBLE);
                binding.notesubtitle.setVisibility(View.VISIBLE);
                binding.notenote.setVisibility(View.VISIBLE);
                binding.priority.setVisibility(View.VISIBLE);

                binding.notetitle.setAnimation(AnimationUtils.loadAnimation(InsertNoteActivity.this,R.anim.fade_scale_animation));
                binding.notesubtitle.setAnimation(AnimationUtils.loadAnimation(InsertNoteActivity.this,R.anim.fade_scale_animation));
                binding.notenote.setAnimation(AnimationUtils.loadAnimation(InsertNoteActivity.this,R.anim.fade_scale_animation));
                binding.priority.setAnimation(AnimationUtils.loadAnimation(InsertNoteActivity.this,R.anim.fade_scale_animation));


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private int getDips(int dps) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                resources.getDisplayMetrics());
    }
    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_scale_animation_back);


                    int cx = binding.background.getWidth() - getDips(44);
                    int cy = binding.background.getBottom() - getDips(44);

                    binding.background.setBackgroundResource(red);
                    binding.redpriority.setVisibility(View.GONE);
                    binding.greenpriority.setVisibility(View.GONE);
                    binding.yellowpriority.setVisibility(View.GONE);

                    binding.notetitle.setVisibility(View.GONE);
                    binding.notesubtitle.setVisibility(View.GONE);
                    binding.notenote.setVisibility(View.GONE);
                    binding.priority.setVisibility(View.GONE);

                    float finalRadius = Math.max(binding.background.getWidth(), binding.background.getHeight());
                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(binding.background, cx, cy, finalRadius, 0);

                    circularReveal.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            binding.background.setVisibility(View.INVISIBLE);

                            finish();
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    circularReveal.setDuration(600);
                    circularReveal.start();




        } else {
            super.onBackPressed();

        }
    }


}