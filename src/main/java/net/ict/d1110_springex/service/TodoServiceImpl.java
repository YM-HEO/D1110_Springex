package net.ict.d1110_springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.d1110_springex.domain.TodoVO;
import net.ict.d1110_springex.dto.TodoDTO;
import net.ict.d1110_springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
//생성자 주입 방식
//의존성 주입이 필요한 객체의 타입을 final로 고정하고 생성자 @RequireArgsConstructor를 이용하여 생성자를 생성하는 방식 사용
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;
    
    @Override
    public void register(TodoDTO todoDTO){
        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);
    }
}
