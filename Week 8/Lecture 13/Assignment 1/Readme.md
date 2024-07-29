# Research about Onceprerequestfilter

OncePerRequestFilter is a convenient base class in the Spring framework for creating servlet filters that should only execute once per request. This is useful when you have filters that should not be applied multiple times in the event of internal forwards or includes, ensuring that the filter logic is only executed a single time for each request.

## Key Points:

It extends GenericFilterBean and implements javax.servlet.Filter.
It overrides the doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) method.
This method is designed to contain the filter logic and ensures that it is called once per request.

## Example

Here's a simple example of how you can create a custom filter by extending OncePerRequestFilter.

### CustomFilter.java

```java
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Custom logic before passing the request along the filter chain
        System.out.println("CustomFilter: Request received at " + request.getRequestURI());

        // Proceed with the next filter in the chain
        filterChain.doFilter(request, response);

        // Custom logic after the request has been processed by the next filters
        System.out.println("CustomFilter: Response sent from " + request.getRequestURI());
    }

}
```

## Configuration

You need to register your custom filter in your Spring configuration. You can do this using a configuration class with the @Configuration annotation.

### WebConfig.java

```java
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<CustomFilter> loggingFilter() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/api/*"); // Set the URL patterns for the filter

        return registrationBean;
    }

}
```

In this example:

The CustomFilter class extends OncePerRequestFilter and overrides the doFilterInternal method.
The WebConfig class is used to register the CustomFilter with the Spring application context, specifying that it should apply to URL patterns matching /api/\*.

## Conclusion

Using OncePerRequestFilter simplifies the development of filters that need to be applied once per request. This can help avoid redundant processing and ensure consistent behavior across different parts of your application.
