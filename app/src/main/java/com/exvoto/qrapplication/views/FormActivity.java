package com.exvoto.qrapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exvoto.qrapplication.Constants;
import com.exvoto.qrapplication.R;
import com.exvoto.qrapplication.model.FormData;
import com.exvoto.qrapplication.services.FormService;

public class FormActivity extends AppCompatActivity {

    private static final String TAG = FormActivity.class.getName();

    Button btSubmit;
    EditText etFullName;
    EditText etAddress;

    FormService formService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        this.formService = new FormService(this);

        btSubmit = findViewById(R.id.bt_submit);
        etAddress = findViewById(R.id.et_address);
        etFullName = findViewById(R.id.et_full_name);

        btSubmit.setOnClickListener(v -> submitForm());
    }

    private void submitForm() {
       String address = etAddress.getText().toString();
       String name = etFullName.getText().toString();
       boolean validAddress = FormService.validateAddress(address);
       boolean validName = FormService.validateName(name);

       if (validAddress && validName) {
           int formId = formService.saveForm(new FormData(name, address));
           // Todo: replace string with string resource
           Toast.makeText(getApplicationContext(), "Form Saved", Toast.LENGTH_SHORT).show();
           Intent formViewIntent = new Intent(this, FormListActivity.class);
           Log.e(TAG, "ID: " + formId);
           formViewIntent.putExtra(Constants.EXTRA_FORM_ID, formId);
           startActivity(formViewIntent);
       } else {
           // Todo: replace string with string resource
           Toast.makeText(getApplicationContext(), "Invalid entries", Toast.LENGTH_SHORT).show();
       }
    }
}