package streami.coin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import streami.coin.domain.Coin;
import streami.coin.dto.CoinReadResponse;
import streami.coin.dto.CoinRequest;
import streami.coin.dto.CoinStatisticsResponse;
import streami.coin.repository.CoinRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinService {
    private final CoinRepository coinRepository;

    private final RestTemplate restTemplate;

    private static final String GOPAX_API = "https://api.gopax.co.kr";

    public Coin registerCoin(CoinRequest body) {
        CoinStatisticsResponse coinStatistics = retrieveCoinStatistics(body.getType());
        LocalDateTime time = coinStatistics.getTime();
        Long price = coinStatistics.getClose();
        return coinRepository.save(Coin.of(body.getType(), time, price));
    }

    public CoinReadResponse retrieveCoin(UUID id) throws NoSuchElementException {
        Coin coin = coinRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Coin [" + id + "] is not registered"));
        CoinStatisticsResponse coinStatistics = retrieveCoinStatistics(coin.getType());
        Long realTimePrice = coinStatistics.getClose();
        return CoinReadResponse.toResponse(coin, realTimePrice);
    }

    private CoinStatisticsResponse retrieveCoinStatistics(String type) {
        String url = GOPAX_API + "/trading-pairs/" + type + "-KRW/stats";
        ResponseEntity<CoinStatisticsResponse> response = restTemplate.getForEntity(url, CoinStatisticsResponse.class);
        return response.getBody();
    }
}
