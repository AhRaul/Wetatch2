package com.example.wetatch2;

public class CardData {

    private final String description; // описание
    private final int picture;        // изображение

    public CardData(String description, int picture, boolean like) {
        this.description = description;
        this.picture = picture;
    }

    // геттеры
    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }
}
