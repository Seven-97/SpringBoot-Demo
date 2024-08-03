package com.seven.mybatismysqldruid.typehandler;

import com.seven.mybatismysqldruid.entity.Gender;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Seven
 */
public class UserGenderEnumHandler extends BaseTypeHandler<Gender> {

    /**
     * 插入时设置参数类型
     *
     * @param preparedStatement SQL预编译对象
     * @param i                 需要赋值的索引位置(相当于在JDBC中对占位符的位置进行赋值)
     * @param gender            索引位置i需要赋的值(原本要给这个位置赋的值，在setNonNullParameter方法中主要解决的问题就是将这个自定义类型变成数据库认识的类型)
     * @param jdbcType          jdbc的类型
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, gender.getCODE());
    }

    /**
     * 获取时转换回的自定义类型
     * 根据列名获取
     *
     * @param resultSet 结果集
     * @param s         列名
     * @return
     * @throws SQLException
     */
    @Override
    public Gender getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return Gender.stateOf(code);
        }
    }

    /**
     * 获取时转换回的自定义类型
     * 根据索引位置获取
     *
     * @param resultSet 结果集
     * @param i         列索引
     * @return
     * @throws SQLException
     */
    @Override
    public Gender getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return Gender.stateOf(code);
        }
    }

    /**
     * 获取时转换回的自定义类型
     * 根据存储过程获取
     *
     * @param callableStatement 结果集
     * @param i                 列索引
     * @return
     * @throws SQLException
     */
    @Override
    public Gender getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return Gender.stateOf(code);
        }
    }
}
