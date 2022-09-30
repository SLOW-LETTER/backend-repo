package com.miniproject.domain.letter.controller;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.domain.letter.service.FileService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.file.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author : 김현진
 * @version : 1.0.0
 * @package : com.miniproject.domain.letter.controller;
 * @name : TemplateController
 * @create-date: 2022.09.30
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@RestController
@RequestMapping(value = "/api/v1/files")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "File", description = "file api 입니다.")
public class FileController {
    private final S3Service s3Service;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FileService fileService;

    // 파일 생성
    @Operation(summary = "file 생성", description = "file 생성 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "file 생성 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result createFile(@ModelAttribute FileDto fileDto) throws IOException {
        if(fileDto.getFile() != null) {
            String url = s3Service.uploadFile(fileDto.getFile(), "letters/" + fileDto.getLetterId()); // 추후에 뒤 dir 부분은 토큰 or 아이디로?
            fileDto.setUrl(url);
        }
        Result result = fileService.createFile(fileDto);
        return result;
    }

    // 전체 파일 조회
    @Operation(summary = "전체 파일 조회", description = "전체 파일 조회 api입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "전체 파일 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping
    public Result retrieveFileList(){
        Result result = fileService.retrieveFileList();
        return result;
    }

    // 저장된 letter_id에 일치하는 file 찾기
    @Operation(summary = "저장된 letter_id에 일치하는 file 조회", description = "저장된 letter_id에 일치하는 file을 조회하는 api입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "저장된 letter_id에 일치하는 file 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/{letterId}")
    public Result retrieveLetterIdFile(int letterId){
        Result result = fileService.retrieveLetterIdFile(letterId);
        return result;
    }

    // 파일 삭제
    @Operation(summary = "파일 삭제", description = "파일 삭제 api입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @DeleteMapping("/{id}")
    public Result deleteFile(@PathVariable int id){
        Result result = fileService.deleteFile(id);
        return result;
    }
}
