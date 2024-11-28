package com.busanit501.food.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodVO {
    private Long fno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
