package net.ict.d1110_springex.mapper;

import net.ict.d1110_springex.domain.TodoVO;
import net.ict.d1110_springex.dto.PageRequestDTO;
import net.ict.d1110_springex.dto.TodoDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();
    void insert(TodoVO todoVO);
    List<TodoVO> selectAll(); //java.util 패키지로 임포트
    TodoVO selectOne(Long tno);
    void delete(Long tno);
    void update(TodoVO todoVO);
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);
}
