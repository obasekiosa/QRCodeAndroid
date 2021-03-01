package com.exvoto.qrapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.exvoto.qrapplication.R;
import com.exvoto.qrapplication.model.FormData;
import com.exvoto.qrapplication.services.FormService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FormListActivity extends AppCompatActivity {

    FormService formService;

    RecyclerView recyclerView;
    List<FormData> data;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_list);
        formService = new FormService(this);



        data = formService.getAllForms();

         recyclerView = findViewById(R.id.rv_from_list);
         layoutManager = new LinearLayoutManager(this);
         FormDataAdapter adapter = new FormDataAdapter(data);
         recyclerView.setAdapter(adapter);

         recyclerView.setLayoutManager(layoutManager);
         recyclerView.scrollToPosition(0);


         FloatingActionButton fab = findViewById(R.id.fab);
         fab.setOnClickListener(this::openFormActivity);
    }

    private void openFormActivity(View v) {

        Snackbar.make(v, "Create a new form", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        startActivity(new Intent(this, FormActivity.class));
    }

    public void onBackPressed() {

    }
}