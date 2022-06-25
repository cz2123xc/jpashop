package jpabook.jpashop.service;

import jpabook.jpashop.repository.ItemRepository;
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

    @Transactional
    public Item updateItem(Long itemId, String name, int price, int stockQuantity) { // 준영속 상태의 엔티티를 받아서 영속성 엔티티가 변경을 감지해서 자동으로 업데이트 되는게 래퍼런스이다
        Item findItem = itemRepository.findOne(itemId); // 엔티티로 부터 가져온 영속성 컨텍스트 이므로 자동으로 업데이트 된다 (트랜잭션에 의해 자동 커밋되어 flush 된다)
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        return findItem;
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
    

}
