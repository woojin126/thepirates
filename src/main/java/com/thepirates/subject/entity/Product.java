package com.thepirates.subject.entity;

import com.thepirates.subject.dto.ProductRequestDto;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

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

    public Product(String name, String description, Delivery delivery) {
        this.name = name;
        this.description = description;
        this.delivery = delivery;
    }

    //@OneToMany 단방향보다는 @ManyToOne 양방향을 사용.
    public void addItemOptions(Options options) {
        optionsList.add(options);
        options.saveItem(this);
    }

    //상품 추가 메서드
    public static Product createItem(ProductRequestDto request) {
        Product item = new Product(request.getName(),request.getDescription(),request.getDelivery());
        List<Options> options = request.getOptions();
        for (Options option : options) {
            item.addItemOptions(option);
        }

        return item;
    }


}
