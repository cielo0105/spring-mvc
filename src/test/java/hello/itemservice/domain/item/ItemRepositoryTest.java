package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        Item item = new Item("a",5000,10);
        Item saveItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(saveItem.getId());
        assertThat(findItem).isEqualTo(saveItem);

    }

    @Test
    void findAll(){
        Item item1 = new Item("a",5000,10);
        Item item2 = new Item("b",3000,20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        List<Item> all = itemRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1,item2);
    }

    @Test
    void update(){
        Item item = new Item("a",5000,10);

        Item savedItem = itemRepository.save(item);
        Long id = savedItem.getId();
        Item updateParam = new Item("item2", 1000, 20);
        itemRepository.update(id,updateParam);
        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getPrice()).isEqualTo(1000);
    }
}