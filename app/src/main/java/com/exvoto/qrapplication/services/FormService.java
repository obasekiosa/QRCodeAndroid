package com.exvoto.qrapplication.services;

import android.content.Context;
import android.graphics.Bitmap;

import com.exvoto.qrapplication.data.MockFormDataHolder;
import com.exvoto.qrapplication.data.MockFormDataHolder.FormHolder;
import com.exvoto.qrapplication.model.FormData;

import java.util.List;

public class FormService {
    private Context context;

    public FormService(Context context) {
        this.context = context;
    }

    public static boolean validateName(String name) {
        if (name == null) return false;
        if (name.trim().isEmpty()) return false;
        return (name.split(" ")).length >= 2;
    }

    public static boolean validateAddress(String address) {
        if (address == null) return false;
        if (address.trim().isEmpty()) return false;
        return address.trim().length() >= 3;
    }

    public int saveForm(FormData form) {
        FormHolder db =  MockFormDataHolder.getInstance(context);
        return db.save(form);
    }

    public void deleteForm(FormData formData) {
        FormHolder db =  MockFormDataHolder.getInstance(context);
        db.delete(formData.getId());
    }

    public FormData getForm(int id) {
        FormHolder db =  MockFormDataHolder.getInstance(context);
        return db.getFormById(id);
    }

    public List<FormData> getAllForms() {
        FormHolder db =  MockFormDataHolder.getInstance(context);
        return db.getAll();
    }

    //Todo: remove all ":" colons and replace all , with a delimiter such as space
    public static Bitmap getQRCode(FormData form) {
        StringBuilder s = new StringBuilder();
        s.append("ID ");
        s.append(form.getId());
        s.append(",");
        s.append("NAME ");
        s.append(form.getName());
        s.append(",");
        s.append("ADDRESS ");
        s.append(form.getAddress());
        s.append(",");
        s.append("CREATED ");
        s.append(form.getCreatedAt().getTime());
        s.append(",");
        s.append("UPDATED ");
        s.append(form.getUpdatedAt().getTime());
        return QRGenerator.generateQRCodeBitmap(s.toString());
    }
}
