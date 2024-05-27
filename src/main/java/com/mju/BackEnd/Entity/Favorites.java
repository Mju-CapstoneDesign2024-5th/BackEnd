package com.mju.BackEnd.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorites_table")
@Getter
@Setter
@NoArgsConstructor
public class Favorites {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "contents_id", nullable = false)
    private String contentsId;

    @ManyToOne
    @JoinColumn(name = "contents_id", referencedColumnName = "contents_id", insertable = false, updatable = false)
    private Contents contents;
}
