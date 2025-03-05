package planIt.planIt.common.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;


class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;
    private final String SECRET_KEY = "PALNIT";
    private final String TEST_USER_ID = "testUser";

    @BeforeEach
    void setUp(){
        jwtTokenProvider = new JwtTokenProvider();
        ReflectionTestUtils.setField(jwtTokenProvider, "SECRET_KEY", SECRET_KEY);
    }

    @Test
    void generateTokenTest() {

        //given
        String token = jwtTokenProvider.generateToken(TEST_USER_ID);

        //when
        String extractedUserId = jwtTokenProvider.extractUserId(token);

        //then
        assertNotNull(token);
        assertEquals(TEST_USER_ID, extractedUserId);

    }

    @Test
    void extractUserIdTest() {

        //given
        String token = jwtTokenProvider.generateToken(TEST_USER_ID);

        //when
        String extractedUserId = jwtTokenProvider.extractUserId(token);

        //then
        assertEquals(TEST_USER_ID, extractedUserId);

    }

    @Test
    void extractUserIdTest_Fail() {

        //given
        String token = jwtTokenProvider.generateToken(TEST_USER_ID);
        String failId = "TEST_USER_ID_FAIL";

        //when
        String extractedUserId = jwtTokenProvider.extractUserId(token);

        //then
        assertNotEquals(failId, extractedUserId);

    }

    @Test
    void validateTokenTest() {

        //given
        String token = jwtTokenProvider.generateToken(TEST_USER_ID);
        UserDetails userDetails = User.withUsername(TEST_USER_ID).password("pw").build();

        //when
        Boolean result = jwtTokenProvider.vaildateToken(token, userDetails);

        //then
        assertTrue(result);

    }

    @Test
    void validateTokenTest_Fail() {

        //given
        String token = jwtTokenProvider.generateToken(TEST_USER_ID);
        String filaId = "TEST_USER_ID_FAIL";
        UserDetails userDetails = User.withUsername(filaId).password("pw").build();

        //when
        Boolean result = jwtTokenProvider.vaildateToken(token, userDetails);

        //then
        assertFalse(result);

    }

}