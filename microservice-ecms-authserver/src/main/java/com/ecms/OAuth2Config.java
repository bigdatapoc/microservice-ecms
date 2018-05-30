package com.ecms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter
{

    private String clientId = "ecmsclientid";
    private String clientSecret = "ecms-secret";
    private String privateKey =
        "-----BEGIN RSA PRIVATE KEY-----\r\n"
            +
            "MIIEpAIBAAKCAQEA5OBPYqFdCeIXxaD5i109H1Qlq08skN56LEERtpPpTwgoZgNP\r\n" +
            "oR2EiydnK5v1cBkS5K5x7Ll4VFvwVPCutUGv919SO3zSrXpo6eJcHeHWRqtPskAU\r\n" +
            "J9aPYRSmYjEtZZg0VYuyseAev9G/BWnAAP4/Q6q4v33rqLo3rEgXMxBL2il2y5nB\r\n" +
            "ZKhT2xpNq5w/1lCYL/0LEQiv/WQityakm0XrsLjm4SZ2g/R2A4ObGmp5tTqSECCQ\r\n" +
            "oFRpG9KGhCR9eDSgyfqa26aOM3FrsWpqnhVLCtfRGjdguQ03gMjIStlm+QV4EaO5\r\n" +
            "IXJWwZ2yb5npFjnB/kFIGHhLiwIMKQt7BNmbxwIDAQABAoIBAGbc4RDE0Nm2QVTS\r\n" +
            "yhR2bRf9a4iYZsEdG+9ztxBFXZ/lXLKsXkdcysr3hIi26RHeUv1v4Uew+4F+aH7k\r\n" +
            "RMC1qu4UaxLNQKoyz5/92cScUP8l0wWbgvN8nKfW7ghHbc2xICQqSr9iqK8xBxbZ\r\n" +
            "ti88FRbGTY3Se+0y8/57z+FlO5HqIyOyN23fBzR6QREAx7/ngjdamG7GDajsJ9DF\r\n" +
            "1Cx5AB2kAzuSpIXJv7rnQ6F3tJaZfUpTZ2kGi953L3k7jkM0Jj+PWTapRu4n4a0i\r\n" +
            "0onxFsZippU1wxRx+NKOTfbq/+MiLNfd3/ny+hiIgaKfPPr3z2wN3X7+U7HqoZaR\r\n" +
            "mGKd76ECgYEA8rRPfplOBR1MCybHjvCMldiNy25vzk/7s8pD/GVBB8siYxhtPJgD\r\n" +
            "odAd5j2w3Um69x07V7gCkIOZdamJZuhR6pXvDNr24TNwQjGBQJ8nvZSmZnJu+Bbo\r\n" +
            "w/IUePaG9Gk8dGnL60LjoC8xLmNnN6E0eUtPJxnjrZbmUQXlPtdrWy8CgYEA8WoS\r\n" +
            "3mgTAqkAvoBLkSrjFsbIgygGcW02x+sdiuaNEFb1gWf87q8h7DxgLI4xujHEymxq\r\n" +
            "B++NKqaxniO+pA2qaa+JqXNOQ8kRLGBbMxkO0UcJsB6F/FFfJJTq7EC5FlxiDgP7\r\n" +
            "9lau8OEB4wTNeafgZM79qGIrZDjsyKIsn60WwukCgYEAwmyXUf9VdNtnU3eZXXRt\r\n" +
            "6owz6sdpYYjJRdm+ZK5oTen4fhHPUD8mibXCffLlgB/UWqlcJIUP6NzDTnKl035A\r\n" +
            "QQAjI9U0+GrQfvZRaIcWutQeCOua4brYdD+1DsHOknTLLSajaK+I0YV8jICs6u+E\r\n" +
            "rbYajmQ685ByqG1km9bN9ZkCgYEAsiXeqrUOYkYowGuYWBXX4nTqfq7Iomc/p6O+\r\n" +
            "90NPEaG6JbaBFfmzBMtBBWAhHg+f8uiyDg0elTzRkas7AYD8ImVhJuRp+uejfqXU\r\n" +
            "Zw60uoa7hXXg1eOOXEZwXcsdSnz99u3Fv3EUQ7knVUmz/3R8yKde1CUtdBX11dCn\r\n" +
            "MWXfiBECgYAgWFYKhJUo0ve0RocqEe3N5i+kNYYW+6P7L6quN9OHNr89HaVc5upt\r\n" +
            "eWUbQ7LUIvu+u5CpLsYox6fcyWwj8wabHCydi3g3HHObt6AuY7MdwgCvKTpeGGZf\r\n" +
            "FVo4hllM+M79LlWh1Nh+rD1CntVcakDFxdk1dqrWAKlQ7ZpyzYrrGw==\r\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey =
        "-----BEGIN PUBLIC KEY-----\r\n"
            +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5OBPYqFdCeIXxaD5i109\r\n" +
            "H1Qlq08skN56LEERtpPpTwgoZgNPoR2EiydnK5v1cBkS5K5x7Ll4VFvwVPCutUGv\r\n" +
            "919SO3zSrXpo6eJcHeHWRqtPskAUJ9aPYRSmYjEtZZg0VYuyseAev9G/BWnAAP4/\r\n" +
            "Q6q4v33rqLo3rEgXMxBL2il2y5nBZKhT2xpNq5w/1lCYL/0LEQiv/WQityakm0Xr\r\n" +
            "sLjm4SZ2g/R2A4ObGmp5tTqSECCQoFRpG9KGhCR9eDSgyfqa26aOM3FrsWpqnhVL\r\n" +
            "CtfRGjdguQ03gMjIStlm+QV4EaO5IXJWwZ2yb5npFjnB/kFIGHhLiwIMKQt7BNmb\r\n" +
            "xwIDAQAB\r\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Bean
    public JwtAccessTokenConverter tokenEnhancer()
    {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }


    @Bean
    public JwtTokenStore tokenStore()
    {
        return new JwtTokenStore(tokenEnhancer());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints
            .authenticationManager(authenticationManager).tokenStore(tokenStore())
            .accessTokenConverter(tokenEnhancer());
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
    {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {

        clients
            .inMemory().withClient(clientId).secret(clientSecret).scopes("read", "write")
            .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
            .refreshTokenValiditySeconds(20000);

    }
}
