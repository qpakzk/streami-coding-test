package streami.coin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import streami.coin.domain.Coin;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoinReadResponse {
    @Schema(description = "코인 정보가 등록된 시간")
    private LocalDateTime time;
    @Schema(description = "코인 종류")
    private String type;
    @Schema(description = "코인 정보 등록 당시 가격")
    private Long price;
    @Schema(description = "실기간 가격")
    private Long realTimePrice;

    public static CoinReadResponse toResponse(Coin coin, Long realTimePrice) {
        return new CoinReadResponse(coin.getCurrentAt(), coin.getType(), coin.getPrice(), realTimePrice);
    }
}
