package com.example.snacklearning.service.impl;

import com.example.snacklearning.entity.Snack;
import com.example.snacklearning.mapper.SnackMapper;
import com.example.snacklearning.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackServiceImpl implements SnackService {

    @Autowired
    private SnackMapper snackMapper;

    @Override
    public List<Snack> getSnacks(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return snackMapper.findAll(offset, pageSize);
    }

    @Override
    public int getTotalCount() {
        return snackMapper.countTotal();
    }

    @Override
    public Snack getSnackById(Integer id) {
        return snackMapper.findById(id);
    }

    @Override
    public int getTotalPages(int pageSize) {
        int totalCount = getTotalCount();
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}