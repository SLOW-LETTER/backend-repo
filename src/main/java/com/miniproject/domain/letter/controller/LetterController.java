package com.miniproject.domain.letter.controller;

import com.miniproject.domain.letter.dto.FileDto;
import com.miniproject.domain.letter.dto.LetterDto;
import com.miniproject.domain.letter.entity.Letter;
import com.miniproject.domain.letter.repository.FileRepository;
import com.miniproject.domain.letter.repository.LetterRepository;
import com.miniproject.domain.letter.service.LetterService;
import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.global.entity.Result;
import com.miniproject.global.file.service.S3Service;
import com.miniproject.global.jwt.service.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import java.util.Optional;

/**
 * @package : com.miniproject.domain.letter.controller;
 * @name : LetterController
 * @create-date: 2022.09.17
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@RestController
@RequestMapping(value = "/api/v1/letters")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Letter", description = "letter api 입니다.")
public class LetterController {
    private final S3Service s3Service;

    @Autowired
    LetterRepository letterRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LetterService letterService;

    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Letter 생성", description = "Letter 생성 api입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Letter 생성 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameters({
            @Parameter(name="receiverEmail", description = "수신자 이메일", example= "test1@gmail.com", required = true, in = ParameterIn.QUERY,
                schema = @Schema(description = "수신자 이메일", type ="string", nullable = false, example="test1@gmail.com")),
            @Parameter(name="boardingTime", description = "출발 시간", example= "2022-09-24 17:12", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="출발 시간", type ="string", nullable = false, example="2022-09-24 17:12")),
            @Parameter(name="arrivalTime", description = "도착 시간", example= "2022-09-25 02:25", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="도착 시간", type ="string", nullable = false, example="2022-09-25 02:25")),
            @Parameter(name="departureCountry", description = "출발 국가", example= "대한민국", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="출발 국가", type ="string", nullable = false, example="대한민국")),
            @Parameter(name="departureCity", description = "출발 도시", example= "서울", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="출발 도시", type ="string", nullable = false, example="서울")),
            @Parameter(name="arrivalCountry", description = "도착 국가", example= "미국", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="도착 국가", type ="string", nullable = false, example="미국")),
            @Parameter(name="arrivalCity", description = "도착 도시", example= "뉴욕", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="도착 도시", type ="string", nullable = false, example="뉴욕")),
            @Parameter(name="title", description = "편지 제목", example= "ㅇㅇ에게", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="편지 제목", type ="string", nullable = false, example="ㅇㅇ에게")),
            @Parameter(name="content", description = "편지 내용", example= "안녕, 잘 지내니?", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="편지 내용", type ="string", nullable = false, example="안녕, 잘 지내니?")),
            @Parameter(name="templateId", description = "탬플릿 아이디 값", example= "1", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="탬플릿 아이디 값", type ="integer", nullable = false, example="1")),
            @Parameter(name="transportationId", description = "교통수단 아이디 값", example= "1", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description ="교통수단 아이디 값", type ="integer", nullable = false, example="1")),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result createLetter(@Parameter(hidden = true) LetterDto letterDto, @ModelAttribute FileDto fileDto, @Parameter(hidden = true) UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token) throws IOException{
        Letter letter = letterDto.toEntity();
        String senderEmail = jwtTokenProvider.getUserPk(token);
        Optional<User> senderUser = userRepository.findByEmail(senderEmail);
        // AWS 경로: users/ {userId} / letters/ {boardingTime}
        if(fileDto.getFile() != null) {
            String url = s3Service.uploadFile(fileDto.getFile(), "users/"+ senderUser.get().getId()  + "/letters/" + letter.getBoardingTime()); // 뒤에 dir 경로?
            fileDto.setUrl(url);
        }
        Result result = letterService.createLetter(letterDto, fileDto, userDto, token);

        return result;
    }

    // 편지 보낸 사람 조회 (유저가 보낸 편지들 조회)
    @Operation(summary = "편지 보낸 사람 조회 (유저가 보낸 편지들 조회)", description = "편지 보낸 사람 조회 (유저가 보낸 편지들 조회) api입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "편지 보낸 사람 조회 (유저가 보낸 편지들 조회) 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/sender")
    public Result retrieveSenderList(@Parameter(hidden = true) UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token){
        Result result = letterService.retrieveSenderList(userDto, token);
        return result;
    }

    // 편지 받는 사람 조회 (유저가 받은 편지들 조회)
    @Operation(summary = "편지 받은 사람 조회 (유저가 받은 편지들 조회)", description = "편지 받은 사람 조회 (유저가 받은 편지들 조회) api입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "편지 받은 사람 조회 (유저가 받은 편지들 조회) 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/receiver")
    public Result retrieveReceiverList(@Parameter(hidden = true) UserDto userDto, @RequestHeader("X-AUTH-TOKEN") String token){
        Result result = letterService.retrieveReceiverList(userDto,token);
        return result;
    }

    // 편지 내용 조회
    @Operation(summary = "편지 내용 조회", description = "편지 내용 조회 api입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "편지 내용 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/{id}")
    public Result retrieveLetter(@PathVariable int id, @RequestHeader("X-AUTH-TOKEN") String token){
        Result result = letterService.retrieveLetter(id);
        return result;
    }

    // 편지 삭제 > 프론트에서 사용 X
    @Operation(summary = "편지 내용 삭제", description = "편지 내용 삭제 api입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "편지 내용 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @DeleteMapping("/{id}")
    public Result deleteLetter(@PathVariable int id, @RequestHeader("X-AUTH-TOKEN") String token){
        Result result = letterService.deleteLetter(id);
        return result;
    }
}
