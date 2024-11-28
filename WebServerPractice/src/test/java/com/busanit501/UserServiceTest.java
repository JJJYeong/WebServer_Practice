package com.busanit501;

import com.busanit501.user.dto.UserDTO;
import com.busanit501.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Log4j2
public class UserServiceTest {

    private UserService service;

    @BeforeEach
    public void setUp() {
        service = UserService.INSTANCE;
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<UserDTO> dtoList = service.listAll();
        for (UserDTO dto : dtoList) {
            log.info("test : {}", dto);
        }
    }

    @Test
    public void testSelectOne() throws SQLException {
        UserDTO dto = service.getOne("asdf");
        log.info("test : {}", dto);
    }

    @Test
    public void testInsert() throws SQLException {
        UserDTO dto = new UserDTO("test", "1234", "test", null);
        service.register(dto);
    }

    @Test
    public void testUpdate() throws SQLException {
        UserDTO dto = new UserDTO("test", "test", "test2", null);
        service.update(dto);
    }

    @Test
    public void testDelete() throws SQLException {
        service.delete("test");
    }

    @Test
    public void testUpdateUUID() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid : {}", uuid);
        service.updateUUID("asdf", uuid);
    }

    @Test
    public void testSelectOneByUUID() throws SQLException {
        UserDTO dto = service.getOneByUUID("cdfd8854-76ef-498f-acd7-28689b9ef6d3");
        log.info("test : {}", dto);
    }
}
