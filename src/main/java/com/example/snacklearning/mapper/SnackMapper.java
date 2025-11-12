package com.example.snacklearning.mapper;

import com.example.snacklearning.entity.Snack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SnackMapper {

    // 查询所有零食（分页）
    List<Snack> findAll(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 查询零食总数
    int countTotal();

    // 根据ID查询零食
    Snack findById(Integer id);
}