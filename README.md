# STREAMI CODING TEST



## API 문서

- http://localhost:8080/swagger-ui/index.html

## API 설명

### 1. 코인 등록 API

GOPAX 공개 API 중 [최근 24시간 통계 조회](https://gopax.github.io/API/index.html#24) API 를 활용하여 코인 등록 기능을 구현했습니다.

해당 API 는 현재 가격을 1분마다 갱신해주기 때문에 코인 등록 시 현재 가격을 비교적 실시간으로 알 수 있습니다.

코인 등록 완료 후, 등록된 코인 정보를 조회할 수 있는 URL 을 반환해줍니다.

URL 은 가급적 짧고, 많은 사용자들에게 할당이 가능하도록 UUID 를 활용한 `{BASE_URL}/coin/{UUID}` 형식으로 구성했습니다.

### 2. 코인 정보 조회 API

코인 등록 시 응답 받은 URL 로 조회 시 호출되는 API 입니다.

UUID 를 코인 ID 로 활용하여 등록한 코인 정보를 DB 에서 조회해옵니다.

실시간 가격은 동일하게 [최근 24시간 통계 조회](https://gopax.github.io/API/index.html#24) API 를 호출하여 읽어옵니다.

등록 당시 가격과 실시간 가격을 함께 확인할 수 있도록 응답을 구성하였습니다.

## DB

### DB 구성

```sh
$ docker-compose up -d
```

### 스키마

```sql
CREATE TABLE `coin` (
  `id` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `current_at` datetime NOT NULL,
  `price` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```
