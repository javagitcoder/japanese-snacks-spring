package com.example.snacklearning.entity;

import java.time.LocalDateTime;

public class Snack {
    private Integer id;
    private String name;
    private String japaneseName;
    private String englishName;
    private String description;
    private String imageName;
    private LocalDateTime createdAt;

    // 构造函数
    public Snack() {}

    public Snack(String name, String japaneseName, String englishName, String description, String imageName) {
        this.name = name;
        this.japaneseName = japaneseName;
        this.englishName = englishName;
        this.description = description;
        this.imageName = imageName;
    }

    // Getter 和 Setter 方法
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

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", japaneseName='" + japaneseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}