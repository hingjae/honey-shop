package com.example.honeyshop.controller;

import com.example.honeyshop.dto.item.SimpleItemResponse;
import com.example.honeyshop.service.CategoryService;
import com.example.honeyshop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;
    @MockBean
    private CategoryService categoryService;

    @Test
    @WithMockUser
    public void testGetItemPage() throws Exception {
        // 가상의 아이템 데이터 생성
        SimpleItemResponse item1 = SimpleItemResponse.builder()
                .id(1L)
                .name("Item1")
                .price(1000)
                .stock(20)
                .build();
        SimpleItemResponse item2 = SimpleItemResponse.builder()
                .id(2L)
                .name("Item2")
                .price(2000)
                .stock(40)
                .build();
        List<SimpleItemResponse> items = Arrays.asList(item1, item2);

        // ItemService의 getItemPage 메서드를 호출하면서 가상의 데이터를 리턴하도록 설정
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<SimpleItemResponse> itemPage = new PageImpl<>(items, pageable, items.size());
        when(itemService.getItemPage(pageable)).thenReturn(itemPage);

        // GET /items 요청 수행
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(view().name("items"))
                .andExpect(model().attributeExists("items", "isLast"))
                .andExpect(model().attribute("isLast", true)) // 예시로 모든 페이지가 마지막 페이지라고 가정
                .andExpect(model().attribute("items", items))
                .andExpect(model().attribute("nowPage", 0))
                .andExpect(content().string(containsString("Item1"))) // 예시로 첫 번째 아이템 이름이 HTML에 포함되었는지 확인
                .andExpect(content().string(containsString("Item2"))); // 예시로 두 번째 아이템 이름이 HTML에 포함되었는지 확인

        // getItemPage 메서드가 한 번 호출되었는지 확인
        verify(itemService, times(1)).getItemPage(pageable);
    }
}