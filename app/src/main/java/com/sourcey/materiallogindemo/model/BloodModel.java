package com.sourcey.materiallogindemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul Satish on 26-08-2016.
 */
public class BloodModel {
    @SerializedName("Hosp_address")
    public String address;
    @SerializedName("Patient_name")
    public String name;
    @SerializedName("Hospital_name")
    public String hop_name;
    @SerializedName("Blood_group")
    public String blood_group;
    @SerializedName("quantity")
    public int quantity;

    public BloodModel(String address, String name, String hop_name, String blood_group, int quantity) {
        this.address = address;
        this.name = name;
        this.hop_name = hop_name;
        this.blood_group = blood_group;
        this.quantity = quantity;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHop_name(String hop_name) {
        this.hop_name = hop_name;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getHop_name() {
        return hop_name;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public int getQuantity() {
        return quantity;
    }
}
