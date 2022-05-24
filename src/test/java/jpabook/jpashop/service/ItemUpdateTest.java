package jpabook.jpashop.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class) // JUNIT5 에서는 @ExtendWith 이용해서 테스트 컨텍스트를 설정해줌
@SpringBootTest // 스프링 부트 테스트 컨텍스트를 생성해줌
@Transactional // 같은 PK는 같은 객체로 인식한다 ( 같은 객체를 여러번 사용하면 에러가 발생한다 그래서 롤백을 해준다 -> 테스트 케이스 에서만 )
public class ItemUpdateTest {




}
