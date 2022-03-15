package com.thepirates.subject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thepirates.subject.dto.ProductRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "item",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Options> optionsList = new ArrayList<>();

    //@OneToMany 단방향보다는 @ManyToOne 양방향을 사용.
    public void addItemOptions(Options options) {
        optionsList.add(options);
        options.saveItem(this);
    }

    //상품 추가 메서드
    public static Item createItem(ProductRequestDto request) {
        Item item = new Item();
        List<Options> options = request.getOptions();
        for (Options option : options) {
            item.addItemOptions(option);
        }
        item.setDelivery(request.getDelivery());
        item.setDescription(request.getDescription());
        item.setName(request.getName());

        return item;
    }


}
