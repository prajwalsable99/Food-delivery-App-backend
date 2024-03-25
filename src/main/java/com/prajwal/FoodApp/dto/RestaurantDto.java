package com.prajwal.FoodApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private Long id;

    private String title;



    private String description;


    @Column(length = 1000)
    private List<String> images;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDto that = (RestaurantDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
