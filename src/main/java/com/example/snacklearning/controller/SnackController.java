package com.example.snacklearning.controller;

import com.example.snacklearning.entity.Snack;
import com.example.snacklearning.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SnackController {

    @Autowired
    private SnackService snackService;

    @Value("${app.image.base-url}")
    private String imageBaseUrl;

    @Value("${app.page.size}")
    private int defaultPageSize;

    @GetMapping("/")
    public String index(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        // 确保页码有效
        if (page < 1) page = 1;

        // 获取零食列表
        List<Snack> snacks = snackService.getSnacks(page, size);

        // 设置图片完整URL
        for (Snack snack : snacks) {
            if (snack.getImageName() != null && !snack.getImageName().isEmpty()) {
                snack.setImageName(imageBaseUrl + "/" + snack.getImageName());
            }
        }

        // 计算分页信息
        int totalCount = snackService.getTotalCount();
        int totalPages = snackService.getTotalPages(size);

        // 确保页码不超过总页数
        if (page > totalPages && totalPages > 0) {
            page = totalPages;
        }

        // 添加到模型
        model.addAttribute("snacks", snacks);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("imageBaseUrl", imageBaseUrl);

        return "index";
    }

    @GetMapping("/snack/{id}")
    public String snackDetail(@PathVariable Integer id, Model model) {
        Snack snack = snackService.getSnackById(id);
        if (snack != null && snack.getImageName() != null && !snack.getImageName().isEmpty()) {
            snack.setImageName(imageBaseUrl + "/" + snack.getImageName());
        }
        model.addAttribute("snack", snack);
        return "detail";
    }
}