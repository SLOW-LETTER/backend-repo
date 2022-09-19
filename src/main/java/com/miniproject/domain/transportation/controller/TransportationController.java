package com.miniproject.domain.transportation.controller;

import com.miniproject.domain.transportation.dto.TransportationDto;
import com.miniproject.domain.transportation.repository.TransportationRepository;
import com.miniproject.domain.transportation.service.TransportationService;
import com.miniproject.global.entity.Result;
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
import org.springframework.web.bind.annotation.*;

/**
 * @author : 원우연
 * @version : 1.0.0
 * @package : com.miniproject.domain.transportation.controller
 * @name : TransportationController
 * @create-date: 2022.09.06
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Slf4j
@RestController
@RequestMapping(value = "api/v1/transportations")
@Tag(name = "Transportation", description = "Transportation api입니다.")
public class TransportationController {

    @Autowired
    TransportationRepository transportationRepository;

    @Autowired
    TransportationService transportationService;

    @Operation(summary = "transportation 전체 조회", description = "transportation 전체 목록을 조회하는 api입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "transportation 전체 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping
    public Result retrieveTransportation() {
        Result result = transportationService.retrieveTransportationList();
        return result;
    }

    @Operation(summary = "transportation 개별 조회", description = "transportation 개별 목록을 조회하는 api입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "transportation 개별 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping("/{transportationId}")
    public Result retrieveTransportation(@PathVariable Integer transportationId) {
        Result result = transportationService.retrieveTransportation(transportationId);
        return result;
    }

    @Operation(summary = "transportation 생성", description = "transportation 생성 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameters({
            @Parameter(name = "name", description = "이름", example = "taxi", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "이름", type = "string", nullable = false, example = "taxi")),
            @Parameter(name = "velocity", description = "속도", example = "60.1", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "속도", type = "number", nullable = false))
    })
    @PostMapping
    public Result createTransportation(@Parameter(hidden = true) TransportationDto transportationdto) {
        Result result = transportationService.createTransportation(transportationdto);
        return result;
    }

    @Operation(summary = "transportation 수정", description = "transportation 수정 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "transportation 수정 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @Parameters({
            @Parameter(name = "name", description = "기존 이름", example = "taxi", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "기존 이름", type = "string", nullable = false, example = "taxi")),
            @Parameter(name = "velocity", description = "기존 속도", example = "60.1", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(description = "기존 속도,", type = "number", nullable = false, example = "60.1"))
    })
    @PatchMapping("/{transportationId}")
    public Result updateTransportation(@Parameter(hidden = true) TransportationDto transportationdto, @PathVariable Integer transportationId) {
        Result result = transportationService.updateTransportation(transportationdto, transportationId);
        return result;
    }

    @Operation(summary = "transportation 삭제", description = "transportation 삭제 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "transportation 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @DeleteMapping("/{transportationId}")
    public Result deleteTransportation(@PathVariable Integer transportationId) {
        Result result = transportationService.deleteTransportation(transportationId);
        return result;
    }
}
