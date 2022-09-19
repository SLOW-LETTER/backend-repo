package com.miniproject.domain.user.controller;

import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.domain.user.dto.UserFileDto;
import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.domain.user.service.UserService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.controller
 * @name : UserInfoController
 * @create-date: 2022-09-19
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Slf4j
@RestController
@RequestMapping(value = "api/v1/users-info")
@Tag(name = "UserInfo", description = "Token을 사용하는 user-info api 입니다.")
public class UserInfoController {

    private final S3Service s3Service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserInfoController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, S3Service s3Service) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.s3Service = s3Service;
    }

    @Operation(summary = "user 정보 확인", description = "유저의 정보를 받아오는 api입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보 확인 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping
    public Result getUserInfo(@RequestHeader("X-AUTH-TOKEN") String token) {
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.retrieveUserByEmail(email);
        return result;
    }

    @Operation(summary = "user 정보 수정", description = "유저 정보를 수정하는 api입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보 수정 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result updateUserInfo(@RequestHeader("X-AUTH-TOKEN") String token, @ModelAttribute UserFileDto userFileDto) throws IOException {
        User user = userFileDto.toEntity();
        if (userFileDto.getFile() != null) {
            String url = s3Service.uploadFile(userFileDto.getFile(), "users");
            user.setProfileImageUrl(url);
        }
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.updateUserInfo(email, user);
        return result;
    }

    @Operation(summary = "세팅변경", description = "세팅변경 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "세팅변경 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameters({
            @Parameter(name = "isCheckedOtherSend", description = "다른 사람에게 보낸 편지 알람 설정", in = ParameterIn.QUERY,
                    schema = @Schema(description = "다른 사람에게 보낸 편지 알람 설정", type = "boolean")),
            @Parameter(name = "isCheckedMyReceive", description = "내가 받은 편지 알람 설정", in = ParameterIn.QUERY,
                    schema = @Schema(description = "내가 받은 편지 알람 설정", type = "boolean")),
            @Parameter(name = "isCheckedMySend", description = "내가 보낸 편지 알람 설정", in = ParameterIn.QUERY,
                    schema = @Schema(description = "내가 보낸 편지 알람 설정", type = "boolean")),
            @Parameter(name = "isCheckedOtherReceive", description = "다른 사람이 받은 편지 알람 설정", in = ParameterIn.QUERY,
                    schema = @Schema(description = "다른 사람이 받은 편지 알람 설정", type = "boolean")),
    })
    @PatchMapping("/settings")
    public Result updateUserInfoSettings(@RequestHeader("X-AUTH-TOKEN") String token, @Parameter(hidden = true) UserDto userDto) {
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.updateUserInfoSettings(email, userDto);
        return result;
    }

    @Operation(summary = "비밀번호변경", description = "비밀번호변경 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호변경 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameters({
            @Parameter(name = "oldPassword", description = "기존 비밀번호", example = "123456", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "기존 비밀번호", type = "string", nullable = false, example = "123456")),
            @Parameter(name = "newPassword", description = "새로운 비밀번호", example = "123456", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "새로운 비밀번호", type = "string", nullable = false, example = "123456"))
    })
    @PatchMapping("/password")
    public Result updateUserInfoPassword(@RequestHeader("X-AUTH-TOKEN") String token, @Parameter(hidden = true) UserDto userDto) {
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.updateUserInfoPassword(email, userDto, passwordEncoder);
        return result;
    }

    @Operation(summary = "회원탈퇴", description = "회원탈퇴 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원탈퇴 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameters({
            @Parameter(name = "password", description = "비밀번호", example = "123456", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "비밀번호", type = "string", nullable = false, example = "123456")),
            @Parameter(name = "withdrawFeedback", description = "회원탈퇴 피드백", example = "사용안함", in = ParameterIn.QUERY,
                    schema = @Schema(description = "회원탈퇴 피드백", type = "string", example = "사용안함"))
    })
    @DeleteMapping
    public Result deleteUser(@RequestHeader("X-AUTH-TOKEN") String token, @Parameter(hidden = true) UserDto userDto) {
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.deleteUser(email, userDto, passwordEncoder);
        return result;
    }
}
