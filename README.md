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
  
### 문제 트러블 슈팅 및 접근 방식

- 상품 추가
  - ![image](https://user-images.githubusercontent.com/25544668/160293266-f299381a-6c14-49fa-98e9-00129ce06150.png)
    - 외래키를 @OneToMany쪽에 선언하면 객체에는 One쪽에 외래키가 갖고있는것 처럼 보이지만 DB상에서는 Many쪽에 외래키를 갖게되어 update 문이 추가로 나가기 때문에 양방향 연관관계를 사용  

<br />

- 상품 조회
  - ![image](https://user-images.githubusercontent.com/25544668/160293376-d38d49b5-2206-4460-85ae-3ae1284e312d.png)
    - 처음에 응답 요구 에따라 날짜 데이터는 포함되지 않아야했지만 new Projection (dto에 값을 바로 삽입) 시키는 방법을 사용하여 날짜 정보까지 응답하는 문제를 필드위에 @jsonIgnore을 사용하여 해결
      - ![image](https://user-images.githubusercontent.com/25544668/160293457-304e3cc4-50ed-44f5-9a41-c7626c8d96a8.png)

<br />

- 상품 상세조회
  - 상품과 상품의 옵션 관계는 1:N 관계로 단순 fetch join을 사용하면 성능상 더 좋을것 같지만 궁금증으로 먼저 product 필드정보를 먼저 조회 후 도출된 product Id값을 Option 테이블의 인자값으로 넘겨 조회하는 방식으로 구현
    - ![image](https://user-images.githubusercontent.com/25544668/160293767-a8a64150-1379-46e4-a079-18f78698aba5.png)

<br />

- 상품 수령일 선택 목록
  - ![image](https://user-images.githubusercontent.com/25544668/160294414-ddb441e3-f6a6-47cc-9c33-cd6f875daafa.png)  
    - 배송 마감일자 request 데이터 요구사항이 HH:mm 이라 LocalTime 필드를 사용.
    
  -  ![image](https://user-images.githubusercontent.com/25544668/160294208-a898e0b3-78ad-4c8a-b599-ed8d6d3d42d1.png)
     - (사진 1번) 여기서 LocalDateTime의 메서드인 LocalDateTime.isBefore()을 사용하여 마감일자를 쉽게 구별하기위해 LocalTime 필드를 LocalDateTime으로 변경   
     - (사진 2번) 현재시간.isBefore(마감시간) true false 사용하여 +0일 / +1일 결정
     - (사진 3번) "fast" / "regular" 타입에따라 +0일 / +1 일 결정
     - (사진 4번) 2,3번 데이터를 인자값으로 넘겨 최종적으로 결과물을 연산하는 메서드.
    
     
