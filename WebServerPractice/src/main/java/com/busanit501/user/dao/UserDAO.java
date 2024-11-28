package com.busanit501.user.dao;

import com.busanit501.util.ConnectionUtil;
import com.busanit501.user.vo.UserVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public UserVO login(UserVO userVO) throws SQLException {
        String sql = "select * from users where id=? and pw=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userVO.getId());
        preparedStatement.setString(2, userVO.getPw());
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        UserVO result = new UserVO();
        if(resultSet.next()) {
            result = UserVO.builder()
                    .id(resultSet.getString("id"))
                    .pw(resultSet.getString("pw"))
                    .name(resultSet.getString("name"))
                    .build();
        }
        return result;
    }

    public List<UserVO> selectAll() throws SQLException {
        String sql = "select * from users";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<UserVO> list = new ArrayList<>();
        while (resultSet.next()) {
            UserVO userVO = UserVO.builder()
                    .id(resultSet.getString("id"))
                    .pw(resultSet.getString("pw"))
                    .name(resultSet.getString("name"))
                    .build();
            list.add(userVO);
        }
        return  list;
    }

    public UserVO selectOne(String id) throws SQLException {
        String sql = "select * from users where id = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        return UserVO.builder()
                .id(resultSet.getString("id"))
                .pw(resultSet.getString("pw"))
                .name(resultSet.getString("name"))
                .build();
    }

    public void insert(UserVO userVO) throws SQLException {
        String sql = "insert into users (id, pw, name) values (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userVO.getId());
        preparedStatement.setString(2, userVO.getPw());
        preparedStatement.setString(3, userVO.getName());
        preparedStatement.executeUpdate();
    }

    public void update(UserVO userVO) throws SQLException {
        String sql = "update users set pw=?, name=? where id=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userVO.getPw());
        preparedStatement.setString(2, userVO.getName());
        preparedStatement.setString(3, userVO.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(String id) throws SQLException {
        String sql = "delete from users where id = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

}