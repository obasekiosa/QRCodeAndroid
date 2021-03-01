package com.exvoto.qrapplication.data;

import android.content.Context;

import com.exvoto.qrapplication.model.FormData;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class MockFormDataHolder {
    private static FormHolder sInstance = null;

    public static synchronized FormHolder getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new FormHolder();
        }
        return sInstance;
    }



    public static class FormHolder extends MockFormDataHolder {

        private final Map<Integer, FormData> formsDB;
        private int idCount;


        public FormHolder() {
            this.formsDB = new HashMap<>();
            this.idCount = 0;
        }



        // Todo: optimize the logic here
        public int save(FormData form) {
            if (form.getId() == null) {
                form.setId(++idCount);
            }

            if (form.getCreatedAt() == null) {
                Date now = new Date();
                form.setCreatedAt(now);
                form.setUpdatedAt(now);
            } else {
                FormData data = formsDB.get(form.getId());
                if (data.getUpdatedAt() != form.getUpdatedAt()) form.setUpdatedAt(new Date());
            }

            this.formsDB.put(form.getId(), form);
            return form.getId();
        }

        public void delete(int id) {
            this.formsDB.remove(id);
        }

        public FormData getFormById(int id) {
            return this.formsDB.get(id);
        }

        public List<FormData> getAll() {
            return new ArrayList<>(this.formsDB.values());
        }

        public List<FormData> getAllSortedById() {
            List<FormData> result = getAll();
            Collections.sort(result, (a, b) -> a.getId().compareTo(b.getId()));
            return result;
        }

        public List<FormData> getAllSortedByDateCreated() {
            List<FormData> result = getAll();
            Collections.sort(result, (a, b) -> a.getCreatedAt().compareTo(b.getCreatedAt()));
            return result;
        }

        public List<FormData> getAllSortedByDateUpdated() {
            List<FormData> result = getAll();
            Collections.sort(result, (a, b) -> a.getUpdatedAt().compareTo(b.getUpdatedAt()));
            return result;
        }

        // pre Populate data base with data
        public void prePopulate() {

        }
    }
}
