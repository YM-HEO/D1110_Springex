package net.ict.d1110_springex.domain;

/* MyBatis외 스프링으로 영속처리를 하기 위해 tier 개발 단계
1. VO 선언
2. Mapper 인터페이스 개발
3. XML 개발
4. 테스트코드 개발
*/

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer; //작성자
}
