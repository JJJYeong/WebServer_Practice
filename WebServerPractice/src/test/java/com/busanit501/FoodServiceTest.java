package com.busanit501;

import com.busanit501.food.dto.FoodDTO;
import com.busanit501.food.service.FoodService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class FoodServiceTest {
    private FoodService service;

    @BeforeEach
    public void ready() {
        service = FoodService.INSTANCE;
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<FoodDTO> dtoList = service.listAll();
        for (FoodDTO foodDto : dtoList) {
            log.info("test : {}", foodDto);
        }
    }

    @Test
    public void testSelectOne() throws SQLException {
        FoodDTO dto = service.getOne(1L);
        log.info("test : {}", dto);
    }

    @Test
    public void testInsert() throws SQLException {
        FoodDTO dto = new FoodDTO(null, "test22", LocalDate.parse("2024-11-11"), true);
        service.register(dto);
    }

    @Test
    public void testUpdate() throws SQLException {
        FoodDTO dto = new FoodDTO(1L, "샘플", LocalDate.now(), true);
        service.update(dto);
    }

    @Test
    public void testDelete() throws SQLException {
        service.delete(12L);
    }
}
