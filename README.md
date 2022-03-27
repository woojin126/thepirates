# thepirates

### 설치 및 환경 가이드
File -> Settings -> gradle 검색 -> 아래사진처럼 설정후 ok
![image](https://user-images.githubusercontent.com/25544668/158130789-cde957f2-ab93-4b54-88ae-be26228f7414.png)

File -> Settings -> annotation 검색 -> Enable annotation processing
![image](https://user-images.githubusercontent.com/25544668/158130868-23238ecd-ec61-43a5-8a3d-2cc2902df571.png)



- 노션 api 설명서 
https://www.notion.so/API-29f992f9ca674ef18f4f54b6b4f15e18

### 엔티티 관계
- Product(@OneToMany) <ㅡㅡ> Options(@ManyToOne) 1 : N 양방향 으로 설정 
- Product(@OneToOne) <ㅡㅡ> Delivery 1: : 1 단방향 설정
  
### 문제 접근 방식

- 상품 추가
  - ![image](https://user-images.githubusercontent.com/25544668/160293266-f299381a-6c14-49fa-98e9-00129ce06150.png)
    - 외래키를 @OneToMany쪽에 선언하면 객체에는 One쪽에 외래키가 갖고있는것 처럼 보이지만 DB상에서는 Many쪽에 외래키를 갖게되어 update 문이 추가로 나가기 때문에 양방향 연관관계를 사용  

- 상품 조회
  - ![image](https://user-images.githubusercontent.com/25544668/160293376-d38d49b5-2206-4460-85ae-3ae1284e312d.png)
    - 처음에 응답 요구 에따라 날짜 데이터는 포함되지 않아야했지만 new Projection (dto에 값을 바로 삽입) 시키는 방법을 사용하여 날짜 정보까지 응답하는 문제를 필드위에 @jsonIgnore을 사용하여 해결
      - ![image](https://user-images.githubusercontent.com/25544668/160293457-304e3cc4-50ed-44f5-9a41-c7626c8d96a8.png)
