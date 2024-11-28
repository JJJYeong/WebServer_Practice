package com.busanit501.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private Long fno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
