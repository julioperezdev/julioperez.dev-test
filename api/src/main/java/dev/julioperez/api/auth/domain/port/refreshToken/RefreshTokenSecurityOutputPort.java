package dev.julioperez.api.auth.domain.port.refreshToken;

import java.util.Calendar;

public interface RefreshTokenSecurityOutputPort {

    String generateTokeUsingEmail(String emailToRefresh);

    Calendar getCalendarWithDateOfExpiration();

}
