package streami.coin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import streami.coin.domain.Coin;

import java.util.UUID;

public interface CoinRepository extends JpaRepository<Coin, UUID> {
}
