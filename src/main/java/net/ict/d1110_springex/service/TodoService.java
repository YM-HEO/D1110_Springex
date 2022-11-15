package net.ict.d1110_springex.service;

import net.ict.d1110_springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
    List<TodoDTO> getAll();
    TodoDTO getOne(Long tno);
    void remove(Long tno);
    void modify(TodoDTO todoDTO);
}
