package com.changgou.token;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

/*****
 * @Author: www.itheima
 * @Date: 2019/7/7 13:48
 * @Description: com.changgou.token
 *  使用公钥解密令牌数据
 ****/
public class ParseJwtTest {

    /***
     * 校验令牌
     */
    @Test
    public void testParseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjIwMzQzMTgxNjcsInVzZXJfbmFtZSI6InpoYW5nc2FuIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiJmNzJkMWZhNy1jZDZiLTRlOWMtYTQ1Zi1kNTUzMTNiNmU5ODQiLCJjbGllbnRfaWQiOiJjaGFuZ2dvdSIsInNjb3BlIjpbImFwcCJdfQ.opJ6xvy4yxLeS3riztIjcp6ZicMmuspzMf7UwLm-6KMoYTpIfmHrTynVMHhNEtfxNK5_klT2f8itivUNuz66FV3Ho12_beQjBDQKPYuvefleD5C8_k6tvTmS7q84CITryM3my5SnZCQ3gksmJ1lCS10AHb33bSvBoO8dpFB4HHUWCLjNbtGljia3aW7OVUNGJblLKeugtlh5Iz-r2AOTDo8MudNWapm52NEvEhoykBwIMpMg5eh9nLfYW-rlB9cDP-KMtYjeL3NfTYuynTN7w-QCxirRIJMSnjxsM2-7lb8tY5-Ja0aGB10KiIjbd9DuWulXZgzzIMlaWOSHq9nxKg";

        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApeP7I97Dwj5jUKpwyNk1z9iKvKksuuaM4A5OGgSImf0kBlCSe7OFdvj79T3V/d+tCpJr2TnLP3be5HzxuzLIj70aytoaVrcG2Atb//6j44QeO+XwygA4dub1eWN4bkmCjh1hA1XUoqsJdIeh4HMAEAWRXhpms51/qvchlCE2Cp0cGUbaLYG6KAaWNkk7KtbbhnJvHUkgOwqd7giAimUJbyCcBtHZutW14efpVYvq/tXeI9AXMJhDhW18tQgqCrZyWyZ+6pIURQmfZ17CzwDfs2VM3fmiXWckzcP4FpacuK9HIxIDnqF4C4VYpxNIAxPd1y1FqypGu6VzqxbSPYIPnwIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
