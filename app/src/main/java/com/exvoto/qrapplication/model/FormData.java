package com.exvoto.qrapplication.model;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Objects;

public class FormData {
    private Integer id;
    private String name;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    public FormData(String name, String address) {
        if (name == null || address == null)
            throw new IllegalArgumentException("Form can not be initialized with null arguments");
        this.name = name;
        this.address = address;
        this.id = null;
        this.createdAt = null;
        this.updatedAt = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int hashCode() {
        if (this.id == null) return 0; // throw illegal hash exception
        return  this.id;
    }

    @Override
    public boolean equals( Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        FormData form = (FormData) obj;

        return Objects.equals(this.id, form.id) && Objects.equals(this.name, form.name)
                && Objects.equals(this.address, form.address)
                && Objects.equals(this.createdAt, form.createdAt);
    }
}
