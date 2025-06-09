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
            if (path.startsWith("/api/auth")) {
                filterChain.doFilter(request, response);
                return;
            }
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


                var userDetails = userDetailsService.loadUserByUsername(username);
                 if (jwtService.isTokenValid(jwt, userDetails)) {
            // 🟡 Trích roles từ token
            List<String> roles = jwtService.extractClaim(jwt, claims -> {
                List<?> rawRoles = claims.get("roles", List.class);
                return rawRoles.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .collect(Collectors.toList());
            });

                    // 🟡 Convert thành GrantedAuthority
                     List<GrantedAuthority> authorities = roles.stream()
                             .map(role -> {
                                 String formattedRole = role.toUpperCase(); // "ADMIN"
                                 if (!formattedRole.startsWith("ROLE_")) {
                                     formattedRole = "ROLE_" + formattedRole;
                                 }
                                 return new SimpleGrantedAuthority(formattedRole);
                             })
                             .collect(Collectors.toList());

                    // 🔐 Tạo token auth kèm roles
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
