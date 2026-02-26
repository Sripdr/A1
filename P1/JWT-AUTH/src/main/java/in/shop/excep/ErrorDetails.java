package in.shop.excep;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message) implements Serializable {
}
