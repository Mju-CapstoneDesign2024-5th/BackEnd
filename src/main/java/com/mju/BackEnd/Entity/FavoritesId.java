package com.mju.BackEnd.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavoritesId implements Serializable {

    private String userId;
    private String contentsId;

    // Constructors, getters, setters, hashCode, and equals methods

    public FavoritesId() {}

    public FavoritesId(String userId, String contentsId) {
        this.userId = userId;
        this.contentsId = contentsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesId that = (FavoritesId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(contentsId, that.contentsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contentsId);
    }
}
