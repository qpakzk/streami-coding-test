package streami.coin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinStatisticsResponse {
    private Long open;
    private Long high;
    private Long low;
    private Long close;
    private Long volume;
    private LocalDateTime time;
}
