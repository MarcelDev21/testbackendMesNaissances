package tech.marcel.naissances.Shared.Esception;

import java.time.LocalDateTime;

public record ErrorEntity(
    LocalDateTime time,
    String code,
    String message,
    int status
) {
}
