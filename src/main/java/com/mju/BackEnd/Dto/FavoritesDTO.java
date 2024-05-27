package com.mju.BackEnd.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesDTO {
    private String userId;
    private String contentsId;
}
