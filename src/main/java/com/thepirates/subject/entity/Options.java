package com.thepirates.subject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer stock;

   /* @OneToMany 또는 @ManyToOne 을 사용할 때 Parent에 추가하는 Child 개체가 아직 데이터베이스에 저장되지 않아서 생긴 문제입니다.
    cascade = CascadeType.ALL 을 포함해야 에러 메시지가 출력되지 않습니다.*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void saveItem(Item item) {
        this.item = item;
    }
}
