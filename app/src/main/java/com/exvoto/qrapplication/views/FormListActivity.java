package com.exvoto.qrapplication.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.exvoto.qrapplication.R;
import com.exvoto.qrapplication.model.FormData;
import com.exvoto.qrapplication.services.FormService;

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
    }
}