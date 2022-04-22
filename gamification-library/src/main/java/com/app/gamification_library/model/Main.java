package com.app.gamification_library.model;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("data")
    private Data data;

    public class Data{

        @SerializedName("title")
        String name;

        @SerializedName("description")
        String description;

        @SerializedName("expiryDate")
        String expiryDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
