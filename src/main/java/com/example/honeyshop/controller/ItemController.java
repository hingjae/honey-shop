package com.example.honeyshop.controller;

import com.example.honeyshop.dto.item.SimpleItemResponse;
import com.example.honeyshop.dto.item.UploadItemRequest;
import com.example.honeyshop.service.CategoryService;
import com.example.honeyshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
@Controller
public class ItemController {
    private final ItemService itemService;
    private final CategoryService categoryService;

    @GetMapping("/new")
    public String uploadItemPage(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "upload-item";
    }

    @PostMapping("/new")
    public String uploadItem(UploadItemRequest request) {
        itemService.saveItem(request);
        return "redirect:/items";
    }

    @GetMapping
    public String getItemsPage(
            @PageableDefault(size = 10, page = 0, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) String searchParam,
            Model model) {
        Page<SimpleItemResponse> itemPage = itemService.getItemsPage(searchParam, pageable);
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("isLast", itemPage.isLast());
        model.addAttribute("nowPage", itemPage.getNumber());
        model.addAttribute("searchParam", searchParam);
        model.addAttribute("pageSize", pageable.getPageSize());
        return "items";
    }

    @GetMapping("/{itemId}")
    public String getItemPage(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemService.getItemPage(itemId));
        return "item";
    }
}
