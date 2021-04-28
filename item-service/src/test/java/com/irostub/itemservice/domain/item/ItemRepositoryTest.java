package com.irostub.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository repository = new ItemRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save(){
        Item item = Item.builder()
                .itemName("coco")
                .price(6000)
                .quantity(10)
                .build();

        Item save = repository.save(item);
        assertThat(save).isEqualTo(item);
    }
}