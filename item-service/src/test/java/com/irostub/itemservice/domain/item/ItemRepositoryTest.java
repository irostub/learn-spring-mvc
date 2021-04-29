package com.irostub.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository repository = new ItemRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    @DisplayName("저장 테스트")
    void save(){
        //given
        Item item = Item.builder()
                .itemName("coco")
                .price(6000)
                .quantity(10)
                .build();

        //when
        Item save = repository.save(item);

        //then
        Item findItem = repository.findById(item.getId());
        assertThat(findItem).isEqualTo(item);
    }
    
    @Test
    @DisplayName("전체 조회 테스트")
    void findAll() {
        //given
        Item item1 = Item.builder().itemName("iro").price(2000).quantity(1).build();
        Item item2 = Item.builder().itemName("diph").price(3000).quantity(1).build();
        repository.save(item1);
        repository.save(item2);
        
        //when
        List<Item> items = repository.findAll();

        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    @DisplayName("갱신 테스트")
    void update() {
        //given
        Item item = Item.builder().itemName("iro").quantity(1).price(2000).build();
        Item saveItem = repository.save(item);
        Long id = saveItem.getId();

        Item updateParam = Item.builder().itemName("diph").price(3000).quantity(2).build();
        //when
        repository.update(id, updateParam);

        //then
        Item findItem = repository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo("diph");
        assertThat(findItem.getPrice()).isEqualTo(3000);
        assertThat(findItem.getQuantity()).isEqualTo(2);
    }
}