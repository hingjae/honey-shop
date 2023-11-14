package com.example.honeyshop.service;

import com.example.honeyshop.dto.item.SimpleItemResponse;
import com.example.honeyshop.entity.Category;
import com.example.honeyshop.entity.Item;
import com.example.honeyshop.repository.CategoryRepository;
import com.example.honeyshop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @DisplayName("getItemPage() : PageRequest를 인자로 넣어 요청하면 DTO로 변환하여 반환")
    @Test
    void test() {
        int categoryCount = 3;
        int itemCount = 30;
        createCategory(categoryCount);
        createItem(itemCount);
        PageRequest request = PageRequest.of(0, 10);
        Page<SimpleItemResponse> itemPage = itemService.getItemPage(request);
        assertThat(itemPage.getContent()).isNotEmpty();
        assertThat(itemPage.getSize()).isEqualTo(request.getPageSize());
        assertThat(itemPage.getTotalPages()).isEqualTo(categoryCount);
        assertThat(itemPage.getNumber()).isEqualTo(request.getPageNumber());
        assertThat(itemPage.getTotalElements()).isEqualTo(itemCount);
    }

    private void createItem(int count) {
        List<Item> itemList = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            Item item = Item.builder()
                    .category(categoryRepository.findById(Long.valueOf(getRandomNumber(1, 3))).get())
                    .name("item" + i)
                    .price((int) ((Math.random() * 11) + 1) * 1000)
                    .stock((int) (Math.random() * 20))
                    .description("description")
                    .build();
            itemList.add(item);
            System.out.println("item = " + item);
        }
        itemRepository.saveAll(itemList);
    }

    private void createCategory(int count) {
        List<Category> categoryList = new ArrayList<>();
        for(int i = 1; i <= count; i++) {
            Category category = Category.builder()
                    .title("category" + i)
                    .build();
            System.out.println("category = " + category);
            categoryList.add(category);
        }
        categoryRepository.saveAll(categoryList);

    }

    private static int getRandomNumber(int min, int max) {

        return (int) (Math.random() * (max - min + 1)) + min;
    }

}