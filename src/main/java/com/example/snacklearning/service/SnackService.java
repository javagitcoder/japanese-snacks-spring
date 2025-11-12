package com.example.snacklearning.service;

import com.example.snacklearning.entity.Snack;
import java.util.List;

public interface SnackService {

    // 获取分页零食列表
    List<Snack> getSnacks(int page, int pageSize);

    // 获取零食总数
    int getTotalCount();

    // 根据ID获取零食
    Snack getSnackById(Integer id);

    // 获取总页数
    int getTotalPages(int pageSize);
}