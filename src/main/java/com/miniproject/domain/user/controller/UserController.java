package com.miniproject.domain.user.controller;

import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.domain.user.service.UserService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.jwt.service.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import javax.transaction.Transactional;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.controller
 * @name : UserController
 * @create-date: 2022-09-19
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Slf4j
@RestController
@RequestMapping(value = "api/v1/users")
@Tag(name = "User", description = "Token을 사용하지 않는 user api 입니다.")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "email 중복 검사", description = "email이 중복인지 검사하는 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "email 중복검사 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameter(name = "email", description = "이메일", example = "test@test.com", required = true, in = ParameterIn.QUERY,
            schema = @Schema(description = "이메일", type = "string", nullable = false, example = "test@test.com"))
    @PostMapping("/email/validation")
    public Result emailValidation(@Parameter(hidden = true) User user) {
        Result result = new Result();
        JSONObject validation = new JSONObject();
        validation.put("validation", !userService.emailValidation(user.getEmail()));
        result.setPayload(validation);
        return result;
    }

    @Operation(summary = "회원가입", description = "회원가입 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameters({
            @Parameter(name = "email", description = "이메일", example = "test@test.com", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "이메일", type = "string", nullable = false, example = "test@test.com")),
            @Parameter(name = "password", description = "비밀번호", example = "123456", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "비밀번호", type = "string", nullable = false, example = "123456")),
            @Parameter(name = "name", description = "이름", example = "홍길동", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "이름", type = "string", nullable = false, example = "홍길동")),
            @Parameter(name = "phone", description = "전화번호", example = "010-1234-5678", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "전화번호", type = "string", nullable = false, example = "010-1234-5678"))
    })
    @PostMapping("/join")
    public Result join(@Parameter(hidden = true) UserDto userDto) {
        Result result = userService.createUser(userDto, passwordEncoder);
        return result;
    }

    @Operation(summary = "로그인", description = "로그인 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameters({
            @Parameter(name = "email", description = "이메일", example = "test@test.com", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "이메일", type = "string", nullable = false, example = "test@test.com")),
            @Parameter(name = "password", description = "비밀번호", example = "123456", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "비밀번호", type = "string", nullable = false, example = "123456"))
    })
    @PostMapping("/login")
    public Result login(@Parameter(hidden = true) User userInput) {
        Result result = new Result();

        User user = userRepository.findByEmailIsNotDeleted(userInput.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
        if (!passwordEncoder.matches(userInput.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        JSONObject token = new JSONObject();
        token.put("token", jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
        String refreshToken = jwtTokenProvider.createRefreshToken();
        userService.updateToken(refreshToken, user);
        token.put("refreshToken", refreshToken);
        result.setPayload(token);
        return result;
    }

    @Operation(summary = "토큰 재발급", description = "토큰을 재발급한다")
    @Parameters({
            @Parameter(name = "X-AUTH-TOKEN", description = "access-token", required = true, in = ParameterIn.HEADER,
                            schema = @Schema(description = "access-token", type = "string", nullable = false)),
            @Parameter(name = "REFRESH-TOKEN", description = "refresh-token", required = true, in = ParameterIn.HEADER,
                    schema = @Schema(description = "refresh-token", type = "string", nullable = false))
    })
    @PostMapping(value = "/refresh")
    public Result refreshToken(@RequestHeader(value="X-AUTH-TOKEN") String token, @RequestHeader(value="REFRESH-TOKEN") String refreshToken ) {
        if(!jwtTokenProvider.validateTokenExceptExpiration(token)) throw new AccessDeniedException("아직 유효한 token입니다");
        User user = userRepository.findByEmail(jwtTokenProvider.getUserPk(token)).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 TOKEN입니다"));
        if(!jwtTokenProvider.validateToken(user.getToken()) || !refreshToken.equals(user.getToken()))
            throw new AccessDeniedException("");
        user.setToken(jwtTokenProvider.createRefreshToken());

        Result result = new Result();
        JSONObject tokenJson = new JSONObject();
        tokenJson.put("token", jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
        result.setPayload(token);

        return result;
    }

    @Operation(summary = "로그 아웃", description = "로그 아웃")
    @Parameters({
            @Parameter(name = "X-AUTH-TOKEN", description = "access-token", required = true, in = ParameterIn.HEADER,
                    schema = @Schema(description = "access-token", type = "string", nullable = false))
    })
    @PostMapping(value = "/logout")
    public Result logout(@RequestHeader(value="X-AUTH-TOKEN") String token) {
        User user = userRepository.findByEmailIsNotDeleted(jwtTokenProvider.getUserPk(token)).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 TOKEN입니다"));
        userService.updateToken("invalidate", user);

        Result result = new Result();
        result.setPayload("success");

        return result;
    }
}
