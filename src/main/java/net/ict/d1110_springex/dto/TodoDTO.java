package net.ict.d1110_springex.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//객체 자료형을 parameter 처리하려면 객체로 생성하고 setXXX() 이용한다.
public class TodoDTO {

    private Long tno;

    @NotEmpty //빈 문자열이 들어오면 안됨
    private String title;

    @Future //과거시제가 들어오면 안됨
    private LocalDate dueDate;

    private boolean finished;

    @NotEmpty
    private String writer; //작성자

}
