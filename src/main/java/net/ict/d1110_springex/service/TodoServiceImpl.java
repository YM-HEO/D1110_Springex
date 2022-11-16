package net.ict.d1110_springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.d1110_springex.domain.TodoVO;
import net.ict.d1110_springex.dto.PageRequestDTO;
import net.ict.d1110_springex.dto.PageResponseDTO;
import net.ict.d1110_springex.dto.TodoDTO;
import net.ict.d1110_springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
        int total = todoMapper.getCount(pageRequestDTO);
        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

//    @Override
//    public List<TodoDTO> getAll() {
//        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
//                .map(vo -> modelMapper.map(vo, TodoDTO.class))
//                .collect(Collectors.toList());
//        return dtoList;
//
//        //vo는 모델맵퍼를 통해 todoDTO 타입으로 변환되고
//        //Collectors를 통해 하나의 리스트로 묶음처리하여 변환해라
//
//        /*  List<TodoVO>를  List<TodoDTO>로 변환하는 작업을 stream을 이용하여
//            각 TodoVO는 map()을 통해서 TodoDTO로 바꾸고
//            collect()를 이용하여
//             List<TodoDTO>로 묶어준다.*/
//    }

    @Override
    public TodoDTO getOne(Long tno) { //맵퍼로 받아와서 모델맵퍼로 dto의 클래스 타입으로 변환
       TodoVO todoVO = todoMapper.selectOne(tno);
       TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
       return todoDTO; //변환한 todoDTO 던져줌
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.update(todoVO);
    }


}
