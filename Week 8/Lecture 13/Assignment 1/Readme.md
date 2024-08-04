# 📝 **Research about Onceprerequestfilter**

## **Overview**

---

`OncePerRequestFilter` is a convenient base class in the Spring framework for creating servlet filters that should `only execute once per request`. This is useful when you have filters that should not be applied multiple times in the event of internal forwards or includes, ensuring that the filter logic is only executed a single time for each request.

_Note_ :
`Filter is a component that performs tasks before or after a request is processed by a servlet. Filters can modify request and response objects, and they are useful for cross-cutting concerns like logging, authentication, and response modification.`)

## **Key Features**

---

- Single Execution: Ensures that the filter's logic is executed once per request, regardless of whether the request is forwarded or included multiple times within the same dispatch cycle.
- Flexible Execution Points: Can be used in various stages of the request handling process.
- Ease of Use: Simplifies the creation of custom filters that should only apply once per request

## **Key Points**

---

- Extends GenericFilterBean and implements javax.servlet.Filter.
- Overrides the doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) method.
  `This method is designed to contain the filter logic and ensures that it is called once per request.`

## **Example**

Here's a simple example of how you can create a custom filter by extending OncePerRequestFilter.

### CustomAuthorizationFilter.java

```java
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !isTokenValid(authorizationHeader)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isTokenValid(String token) {
        // Add your token validation logic here (e.g., JWT verification)
        return "valid-token".equals(token); // Example validation
    }
}
```

## **Configuration**

You need to register your custom filter in your Spring configuration. You can do this using a configuration class with the @Configuration annotation.

### FilterConfig.java

```java
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomFilter> loggingFilter() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/api/*"); // Set the URL patterns for the filter

        return registrationBean;
    }

}
```

## Explanation

### *file*
- CustomAuthorizationFilter: This filter checks for an "Authorization" header in the request. If the header is missing or the token is invalid, it sets the response status to 401 Unauthorized and terminates the request.
- SecurityConfig: The filter is added to the Spring Security filter chain before the BasicAuthenticationFilter. This ensures that the custom authorization logic runs before any other authentication filters.

### *method*
- doFilterInternal Method: This is where the main logic of the filter resides. It checks the request for the "Authorization" header and validates it.
- isTokenValid Method: This method contains the token validation logic. For simplicity, it checks if the token equals "valid-token". In a real application, you would replace this with actual token validation logic, such as verifying a JWT.
- Filter Registration: The custom filter is registered in the security configuration using the addFilterBefore method to ensure it runs at the appropriate point in the filter chain.

## Summary

`OncePerRequestFilter` is a powerful tool in the Spring Security framework, enabling you to create custom filters that are guaranteed to execute only once per request. This is particularly useful for scenarios where repeated execution could cause issues or unnecessary overhead.
