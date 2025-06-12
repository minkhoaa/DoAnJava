    package com.example.demo.security;

    import java.io.IOException;
    import java.util.List;
    import java.util.stream.Collectors;

    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import com.example.demo.service.CustomUserDetails;
    import com.example.demo.service.JwtService;
    import com.example.demo.service.UserDetailsServiceImpl;

    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.RequiredArgsConstructor;

    @Component
    @RequiredArgsConstructor
    public class JwtAuthFilter extends OncePerRequestFilter {

        private final JwtService jwtService;
        private final UserDetailsServiceImpl userDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain)
                throws ServletException, IOException {

            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String username;
            String path = request.getServletPath();

            // ✅ Các route public không cần JWT
            List<String> publicPaths = List.of(
                    "/api/auth",
                    "/api/auth/",
                    "/v3/api-docs",
                    "/v3/api-docs/",
                    "/swagger-ui",
                    "/swagger-ui/",
                    "/swagger-ui.html"
            );

            // ✅ Nếu là route công khai thì bỏ qua filter
            if (publicPaths.stream().anyMatch(path::startsWith)) {
                filterChain.doFilter(request, response);
                return;
            }

            // ❌ Không có Bearer token
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                var userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Trích roles từ token
                    List<String> roles = jwtService.extractClaim(jwt, claims -> {
                        List<?> rawRoles = claims.get("roles", List.class);
                        return rawRoles.stream()
                                .filter(String.class::isInstance)
                                .map(String.class::cast)
                                .collect(Collectors.toList());
                    });

                    // Convert thành GrantedAuthority
                    List<GrantedAuthority> authorities = roles.stream()
                            .map(role -> {
                                String formattedRole = role.toUpperCase();
                                if (!formattedRole.startsWith("ROLE_")) {
                                    formattedRole = "ROLE_" + formattedRole;
                                }
                                return new SimpleGrantedAuthority(formattedRole);
                            })
                            .collect(Collectors.toList());

                    var authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        }

    }
