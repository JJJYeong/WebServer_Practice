package com.busanit501.food.dao;

import com.busanit501.food.vo.FoodVO;
import com.busanit501.util.ConnectionUtil;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

    public List<FoodVO> selectAll() throws SQLException {
        String sql = "select * from tbl_food";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<FoodVO> list = new ArrayList<>();
        while (resultSet.next()) {
            FoodVO foodVO = FoodVO.builder()
                    .fno(resultSet.getLong("fno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(foodVO);
        }
        return  list;
    }

    public FoodVO selectOne(Long fno) throws SQLException {
        String sql = "select * from tbl_food where fno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, fno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        FoodVO foodVO = FoodVO.builder()
                .fno(resultSet.getLong("fno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return foodVO;
    }

    public void insert(FoodVO foodVO) throws SQLException {
        String sql = "insert into tbl_food (title, dueDate, finished) values (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(foodVO.getDueDate()));
        preparedStatement.setBoolean(3, foodVO.isFinished());
        preparedStatement.executeUpdate();
    }

    public void update(FoodVO foodVO) throws SQLException {
        String sql = " update tbl_food set title=?, dueDate=?, finished=?" +
                " where fno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(foodVO.getDueDate()));
        preparedStatement.setBoolean(3,foodVO.isFinished());
        preparedStatement.setLong(4,foodVO.getFno());
        preparedStatement.executeUpdate();
    }

    public void delete(Long fno) throws SQLException {
        String sql = "delete from tbl_food where fno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, fno);
        preparedStatement.executeUpdate();
    }

    // ==============================================================================

    public String getTime() {
        String now = null;
        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("select now()");
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.next();
            now = resultSet.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
        } //catch
        return now;
    } //getTime

    public String getTime2() throws SQLException {
        String now = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select now()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        now = resultSet.getString(1);
        return now;
    }

} //class