package net.ict.d1110_springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.d1110_springex.dto.PageRequestDTO;
import net.ict.d1110_springex.dto.PageResponseDTO;
import net.ict.d1110_springex.dto.TodoDTO;
import net.ict.d1110_springex.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor //생성자 주입 어노테이션
public class TodoController {

    //실제로 동작하도록 service를 컨트롤러와 연결
    private final TodoService todoService;

    @RequestMapping("/list")  // 최종경로: /todo/list

    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){
        log.info("..................todo list........................");
        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
        //model에는 'dtoList'이름으로 목록데이터가 담겨있다.
    }

//    public void list(Model model){
//
//        log.info("..................todo list........................");
//        model.addAttribute("dtoList",todoService.getAll());
//        //model에는 'dtoList'이름으로 목록데이터가 담겨있다.
//        // todoService.getAll()를 이용해서 dtoList에 담는다.
//
//    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET) // 최종경로: todo슬래쉬register
//    public void registerGet() {
//        log.info("................todo register() get..................");
//    }

//    @PostMapping("/register")
//    public void registerPost() {
//        log.info("................todo register() post..................");
//    }

    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("................ex1.....................");
        log.info("........name: "+ name +"..................");
        log.info("........age: "+ age +"..................");
    }

    @GetMapping("/ex2") //requestParam에 parameter 기본값을 지정해주는 방법!
    public void ex2(@RequestParam(name="name", defaultValue = "AAA")String name, @RequestParam(name="age", defaultValue = "20")int age) {
        log.info("................ex2.....................");
        log.info("........name: "+ name +"..................");
        log.info("........age: "+ age +"..................");
    }

    /*TodoDTO 생성 후 작성. todo register를 post 방식으로 처리하는 registerPost()는 todoDTO를 parameter로 적용해서 자동 형변환을 지원한다.   */
//    @PostMapping("/register")
//    public void registerPost(TodoDTO dto) {
//        log.info("................todo register..................");
//        log.info(dto);
//    }

    @GetMapping({"/read", "/modify"}) //배열 표기법으로 한번에 read, modify 기능을 처리
    public void read(PageRequestDTO pageRequestDTO, Long tno, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto",todoDTO);
    }


    @PostMapping("/register")                                                       //얘가 있어야 수집한 데이터를 db로 넘겨준다. 없으면 콘솔창에만 뿌리고 날려버림
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("POST todo register..................");

        //에러가 발생하면
        if(bindingResult.hasErrors()){
            log.error("has errors.....");
            //잘못된 결과를 addFlashAttribute에 담아서 일회성으로 redirect 하겠다는 의미
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        //발생 안하면
        log.info(todoDTO);
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){
        log.info("---------remove----------");
        log.info("tno : "+tno);
        todoService.remove(tno);
        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.error("....has errors....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        log.info(todoDTO);
        todoService.modify(todoDTO);
        return "redirect:/todo/list";
    }

}
