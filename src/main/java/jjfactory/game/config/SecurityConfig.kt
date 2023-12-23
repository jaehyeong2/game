package jjfactory.game.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
//    private val managerAuthenticationFilter: ManagerAuthenticationFilter,
//    private val clientAuthenticationFilter: ClientAuthenticationFilter,
//    private val exceptionHandlerFilter: ExceptionHandlerFilter
) {
    @Value("\${application.Environment}")
    private lateinit var environment: String

    private val TEST_URLS = arrayOf(
        "/**",
        "/test",
        "/test/**"
    )

    //    @Order(1)
    @Bean
    fun filterChainForManager(http: HttpSecurity): SecurityFilterChain {
        if ("dev".equals(environment) || "local".equals(environment)) {
            http.authorizeHttpRequests {
                it.requestMatchers(*TEST_URLS).permitAll()
            }

//            http.exceptionHandling { it.authenticationEntryPoint(authEntryPoint()) }
            http.authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // permit options
//                    .requestMatchers("/**").permitAll()
                    .requestMatchers("/", "/robots.txt").permitAll() // health check && robots.txt && error
                    .requestMatchers("/auth/**", "/regi/**").anonymous()
                    .requestMatchers("/oauth2/*/code", "/oauth2/*/authorization").permitAll()
                    .anyRequest().authenticated()
            }
                .formLogin { it.disable() }
                .logout { it.disable() }
        }

        http.sessionManagement { sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
            .csrf { csrf -> csrf.disable() }
            .cors { it }


//        http.addFilterBefore(managerAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
//        http.addFilterBefore(exceptionHandlerFilter, managerAuthenticationFilter::class.java)

        return http.build()
    }


    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
//            web.ignoring().requestMatchers("/")
        }
    }

//    @Bean
//    fun authEntryPoint(): AuthenticationEntryPoint {
//        return AuthEntryPoint()
//    }


}