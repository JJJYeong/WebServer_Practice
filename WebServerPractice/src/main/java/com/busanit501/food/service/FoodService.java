package com.busanit501.food.service;

import com.busanit501.food.dao.FoodDAO;
import com.busanit501.food.dto.FoodDTO;
import com.busanit501.food.vo.FoodVO;
import com.busanit501.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum FoodService {
    INSTANCE;

    private FoodDAO foodDAO;
    private ModelMapper modelMapper;

    FoodService() {
        foodDAO = new FoodDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // get list
    public List<FoodDTO> listAll() throws SQLException {
        List<FoodVO> voList = foodDAO.selectAll();
        log.info("voList : {}", voList);

        return voList.stream().map(vo -> modelMapper.map(vo, FoodDTO.class)).collect(Collectors.toList());
    }

    // get one
    public FoodDTO getOne(Long fno) throws SQLException {
        FoodVO foodVO = foodDAO.selectOne(fno);
        log.info("foodVO : {}", foodVO);

        return modelMapper.map(foodVO, FoodDTO.class);
    }

    // register
    public void register(FoodDTO foodDTO) throws SQLException {
        // 모델 맵퍼 이용하여 DTO -> VO 변환
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        log.info("foodVO : {}", foodVO);

        foodDAO.insert(foodVO);
    }

    // update
    public void update(FoodDTO foodDTO) throws SQLException {
        // 모델 맵퍼 이용하여 DTO -> VO 변환
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        log.info("foodVO : {}", foodVO);

        foodDAO.update(foodVO);
    }

    // delete
    public void delete(Long fno) throws SQLException {
        foodDAO.delete(fno);
    }
}
