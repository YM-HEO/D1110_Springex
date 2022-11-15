package net.ict.springex.mapper;

import lombok.extern.log4j.Log4j2;
import net.ict.d1110_springex.domain.TodoVO;
import net.ict.d1110_springex.mapper.TimeMapper2;
import net.ict.d1110_springex.mapper.TodoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml") //경로 설정 주의!
public class TodoMapperTest {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testNow() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {

        TodoVO todoVO = TodoVO.builder()
                .title("spring Test")
                .dueDate(LocalDate.of(2022, 11, 14))
                .writer("ict05")
                .build();

        todoMapper.insert(todoVO);

    }

    @Test
    public void testSelectAll(){
        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo -> log.info(vo));
    }


}
