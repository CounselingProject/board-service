package xyz.sangdam.board.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import xyz.sangdam.board.entities.Board;
import xyz.sangdam.board.entities.BoardData;
import xyz.sangdam.board.services.BoardDeleteService;
import xyz.sangdam.board.services.BoardInfoService;
import xyz.sangdam.board.services.BoardSaveService;
import xyz.sangdam.board.services.BoardViewCountService;
import xyz.sangdam.board.services.config.BoardConfigInfoService;
import xyz.sangdam.board.validators.BoardValidator;
import xyz.sangdam.global.CommonSearch;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Utils;
import xyz.sangdam.global.exceptions.BadRequestException;
import xyz.sangdam.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Board", description = "게시글 API")
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardConfigInfoService configInfoService;
    private final BoardInfoService infoService;
    private final BoardSaveService saveService;
    private final BoardDeleteService deleteService;
    private final BoardViewCountService viewCountService;
    private final BoardValidator validator;
    private final Utils utils;

    // 게시판 설정
    @GetMapping("/config/{bid}")
    public JSONData getConfig(@PathVariable("bid") String bid) {

        Board board = configInfoService.get(bid);

        return new JSONData(board);
    }

    // 글쓰기
    @PostMapping("/write/{bid}")
    public ResponseEntity<JSONData> write(@PathVariable("bid") String bid, @RequestBody @Valid RequestBoard form, Errors errors) {
        form.setBid(bid);
        form.setMode("write");

        return save(form, errors);
    }

    // 글 수정
    @PatchMapping("/update/{seq}")
    public ResponseEntity<JSONData> update(@PathVariable("seq") Long seq, @RequestBody @Valid RequestBoard form, Errors errors) {
        form.setSeq(seq);
        form.setMode("update");

        return save(form, errors);
    }

    // 글 작성, 수정 처리
    private ResponseEntity<JSONData> save(RequestBoard form, Errors errors) {

        validator.validate(form, errors);

        if (errors.hasErrors()) { // 검증 실패
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        BoardData data = saveService.save(form);
        data.setBoard(null);
        data.setComments(null);

        JSONData jsonData = new JSONData(data);
        HttpStatus status = HttpStatus.CREATED;
        jsonData.setStatus(status);

        return ResponseEntity.status(status).body(jsonData);
    }

    @GetMapping("/info/{seq}")
    public JSONData info(@PathVariable("seq") Long seq) {
        BoardData item = infoService.get(seq);

        viewCountService.update(seq); // 조회수 카운트

        return new JSONData(item);
    }

    @GetMapping("/list/{bid}")
    public JSONData list(@PathVariable("bid") String bid, @ModelAttribute BoardDataSearch search) {
        ListData<BoardData> data = infoService.getList(bid, search);

        return new JSONData(data);
    }

    @DeleteMapping("/delete/{seq}")
    public JSONData delete(@PathVariable("seq") Long seq) {
        BoardData item = deleteService.delete(seq);

        return new JSONData(item);
    }

    @GetMapping("/wish")
    @PreAuthorize("isAuthenticated()")
    public JSONData wishList(CommonSearch search) {
        ListData<BoardData> data = infoService.getWishList(search);

        return new JSONData(data);
    }
}
