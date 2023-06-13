package fun.tans.tools;


import org.junit.jupiter.api.Test;

public class SecurityUtilTest {

    @Test
    public void testMd5() {
        String salt = "123456";
        String message = "abcdefg";
        System.out.println(SecurityUtil.crypto(salt, message));

    }

}
