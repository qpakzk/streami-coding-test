package streami.coin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import streami.coin.domain.Coin;
import streami.coin.dto.CoinReadResponse;
import streami.coin.dto.CoinRegisterResponse;
import streami.coin.dto.CoinRequest;
import streami.coin.service.CoinService;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coin")
public class CoinController {
    private final CoinService coinService;

    // 1번
    @Operation(summary = "코인 등록", description = "현재 시간의 코인 가격 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    @PostMapping
    public ResponseEntity<CoinRegisterResponse> registerCoin(
            HttpServletRequest request,
            @Valid @RequestBody CoinRequest body) {
        Coin coin = coinService.registerCoin(body);
        String url = request.getRequestURL() + "/" + coin.getId().toString();
        return new ResponseEntity<>(CoinRegisterResponse.toResponse(url), HttpStatus.CREATED);
    }

    // 2번
    @Operation(summary = "코인 정보 조회", description = "등록된 코인 정보와 실시간 가격 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping( "/{id}")
    public ResponseEntity<CoinReadResponse> retrieveCoin(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(coinService.retrieveCoin(id));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
