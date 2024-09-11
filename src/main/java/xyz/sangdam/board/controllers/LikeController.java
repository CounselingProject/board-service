package xyz.sangdam.board.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.sangdam.board.services.LikeService;

@Tag(name="Like", description = "좋아요 API")
@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "게시글 좋아요 수 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name="seq", required = true, description = "경로변수, 게시글 등록 번호")
    @GetMapping("/{seq}/count")
    public long getLikeCount(@PathVariable("seq") Long seq) {
        return likeService.getLikeCount(seq);
    }

    @Operation(summary = "게시글 좋아요 추가", method = "POST")
    @ApiResponse(responseCode = "201")
    @Parameter(name="seq", required = true, description = "경로변수, 게시글 등록 번호")
    @PostMapping("/{seq}")
    public ResponseEntity<Void> add(@PathVariable("seq") Long seq) {
        likeService.add(seq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "게시글 좋아요 제거", method = "DELETE")
    @ApiResponse(responseCode = "200")
    @Parameter(name="seq", required = true, description = "경로변수, 게시글 등록 번호")
    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> remove(@PathVariable("seq") Long seq) {
        likeService.remove(seq);
        return ResponseEntity.ok().build();
    }
}