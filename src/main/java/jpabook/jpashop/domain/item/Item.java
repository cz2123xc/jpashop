package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {


    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    @ManyToMany
    @JoinTable(name = "category_item")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//

    /**
     * 재고를 증가시키는 메서드
     * @param quantity
     */
    public void addStork(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * 재고를 감소시키는 메서드
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }





}
