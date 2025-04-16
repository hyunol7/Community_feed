package org.example.Common.principal;

import org.example.auth.domain.TokenProvider;
import org.springframework.core.MethodParameter;
import org.springframework.jdbc.core.metadata.HanaCallMetaDataProvider;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

    public AuthPrincipalArgumentResolver(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            String authToken = webRequest.getHeader("Authorization");
            if (authToken == null || authToken.split(" ").length != 2) {
                throw new IllegalArgumentException();
            }
            String token = authToken.split(" ")[1];
            Long userid = tokenProvider.getUserId(token);
            String role = tokenProvider.getUserRole(token);

            return new UserPrincipal(userid, role);
        } catch (Exception e) {
            throw new IllegalArgumentException("올바르지 않은 토큰 값 입니다.");
        }

    }
}
