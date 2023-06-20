package streami.coin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinRegisterResponse {
    @Schema(description = "등록한 코인 조회 URL")
    private String url;

    public static CoinRegisterResponse toResponse(String url) {
        return new CoinRegisterResponse(url);
    }
}
