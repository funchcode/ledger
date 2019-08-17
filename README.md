개발정리
생각: JPA에서 최종적으로 만들어지는 Query를 확인하고 싶었음.
해결: application.yml에 logging.level.org.hibernate.type을 trace로 주면된다.

에러정리
발생: Entity, DTO 생성 후 JPA insert Test 시에 Syntax 에러
원인: 1. 처음엔 LocalDateTime과 TimeStamp가 호환이 안된다고 판단했으나 MariaDB Console에서 같은 쿼리 전송 시 싱글쿼텐션(')이 붙었다.
     2. DB의 예약어를 필드명으로 사용 시에 JPA에서 Query Syntax 에러를 뱉는다.
해결: 같은 의미의 단어를 찾아 필드명을 변경

발생: Entity Default Value가 Insert되지 않아 PrePersist로 값을 세팅을 했는데 여전히 값이 수정되지 않음.
원인: Default 값을 원하는 타입이 char였고 char의 Null 값은 '\u0000'로 들어왔음.
해결: '\u0000'의 값과 현재 Entity의 값을 비교해서 Default Value를 세팅했음.
