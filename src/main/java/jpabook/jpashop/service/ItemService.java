package jpabook.jpashop.service;

import jpabook.jpashop.Repository.ItemRepository;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 서비스 선언
@Transactional(readOnly = true) // 기본값이 true 이므로 읽기 전용으로 설정해준다 (검색쿼리 이므로)
@RequiredArgsConstructor // 생성자 주입
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
    

}
