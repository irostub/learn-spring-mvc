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
        Item item = new Item("coco", 6000, 10);

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
        Item item1 = new Item("iro", 2000,10);
        Item item2 = new Item("diph", 3000, 20);
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
        Item item = new Item("iro", 3000, 20);
        Item saveItem = repository.save(item);
        Long id = saveItem.getId();

        Item updateParam = new Item("diph", 2000, 19);
        //when
        repository.update(id, updateParam);

        //then
        Item findItem = repository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo("diph");
        assertThat(findItem.getPrice()).isEqualTo(2000);
        assertThat(findItem.getQuantity()).isEqualTo(19);
    }
}