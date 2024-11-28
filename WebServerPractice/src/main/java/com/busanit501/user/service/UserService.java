package com.busanit501.user.service;

import com.busanit501.user.dao.UserDAO;
import com.busanit501.user.dto.UserDTO;
import com.busanit501.util.MapperUtil;
import com.busanit501.user.vo.UserVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum UserService {
    INSTANCE;

    private UserDAO userDAO;
    private ModelMapper modelMapper;

    UserService() {
        userDAO = new UserDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // login
    public UserDTO login(UserDTO userDTO) throws SQLException {
        UserVO userVO = userDAO.login(modelMapper.map(userDTO, UserVO.class));
        return modelMapper.map(userVO, UserDTO.class);
    }

    // get list
    public List<UserDTO> listAll() throws SQLException {
        List<UserVO> voList = userDAO.selectAll();
        log.info("voList : {}", voList);

        return voList.stream().map(vo -> modelMapper.map(vo, UserDTO.class)).collect(Collectors.toList());
    }

    // get one
    public UserDTO getOne(String id) throws SQLException {
        UserVO userVO = userDAO.selectOne(id);
        log.info("userVO : {}", userVO);

        return modelMapper.map(userVO, UserDTO.class);
    }

    // register
    public void register(UserDTO userDTO) throws SQLException {
        // 모델 맵퍼 이용하여 DTO -> VO 변환
        UserVO userVO = modelMapper.map(userDTO, UserVO.class);
        log.info("userVO : {}", userVO);

        userDAO.insert(userVO);
    }

    // update
    public void update(UserDTO userDTO) throws SQLException {
        // 모델 맵퍼 이용하여 DTO -> VO 변환
        UserVO userVO = modelMapper.map(userDTO, UserVO.class);
        log.info("userVO : {}", userVO);

        userDAO.update(userVO);
    }

    // delete
    public void delete(String id) throws SQLException {
        userDAO.delete(id);
    }
}
