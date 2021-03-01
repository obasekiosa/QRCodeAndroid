package com.exvoto.qrapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exvoto.qrapplication.Constants;
import com.exvoto.qrapplication.R;
import com.exvoto.qrapplication.model.FormData;
import com.exvoto.qrapplication.services.FormService;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

public class FormViewActivity extends AppCompatActivity {

    private static final String TAG = FormViewActivity.class.getName();

    TextView tvFormId;
    TextView tvFullName;
    TextView tvAddress;
    TextView tvDateCreated;
    TextView tvDateUpdated;
    ImageView ivQRCode;

    FormService formService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view);
        this.formService = new FormService(this);

        this.tvAddress = findViewById(R.id.tv_address);
        this.tvFormId = findViewById(R.id.tv_form_id);
        this.tvFullName = findViewById(R.id.tv_full_name);
        this.tvDateCreated = findViewById(R.id.tv_date_created);
        this.tvDateUpdated = findViewById(R.id.tv_date_updated);
        this.ivQRCode = findViewById(R.id.iv_qr_code);


        Intent intent = getIntent();
        int formId = intent.getIntExtra(Constants.EXTRA_FORM_ID, 0);

        if (formId != 0) {
            FormData form = formService.getForm(formId);
            Log.e(TAG, "form: " + form.getId().toString());
            if (form != null) {
                populateForm(form);
            } else {
                showFormDoesNotExistErrorMessage();
                Log.e(TAG, "Form does not exist" + form.getId().toString());
            }

        } else {
            showInvalidFormErrorMessage();
            Log.e(TAG, "Invalid Form" + formId);
        }

    }

    private void populateForm(FormData form) {
        this.tvFormId.setText(getString(R.string.form_id) + form.getId().toString());
        this.tvFullName.setText(getString(R.string.full_name) + form.getName());
        this.tvAddress.setText(getString(R.string.address) + form.getAddress());
        //Todo: format the date
        this.tvDateCreated.setText(getString(R.string.date_created) + form.getCreatedAt().toString());
        this.tvDateUpdated.setText(getString(R.string.date_updated) + form.getUpdatedAt().toString());


        Bitmap qr = FormService.getQRCode(form );
        if (qr != null) {
            this.ivQRCode.setImageBitmap(qr);
        } else {
            showInvalidQRCodeErrorMessage();
        }
    }

    private void showInvalidFormErrorMessage() {
        Toast.makeText(this, "Invalid Form", Toast.LENGTH_LONG).show();
    }

    private void showFormDoesNotExistErrorMessage() {
        Toast.makeText(this, "Form does not exist", Toast.LENGTH_LONG).show();
    }

    private void showInvalidQRCodeErrorMessage() {
        Toast.makeText(this, "Could not generate QR Code", Toast.LENGTH_LONG).show();
    }
}