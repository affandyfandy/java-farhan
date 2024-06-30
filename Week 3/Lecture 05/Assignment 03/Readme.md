# 👨🏻‍🏫 Comparison of Restful API Best Practice

## **1️⃣ Accept and Respond with JSON**

**Best Practices**

- **JSON Handling:** REST APIs should handle requests and responses in JSON format, given its wide acceptance as a data interchange format.
- **Content Negotiation:** APIs should support `Content-Type` and `Accept` headers for managing JSON.

**Assignment 2 Implementation**

- **Usage:** The code ensures JSON responses by using `@RestController`. Methods with `@PostMapping` and `@PutMapping` annotations accept JSON payloads through `@RequestBody`.
- **Response Handling:** JSON responses are configured and returned using `ResponseEntity`.

### 2️⃣ Use Nouns in enpoint paths

- **Best Practices:** Use nouns to represent resources in endpoint paths, as HTTP methods already define actions.

#### Assignment 2 Implementation

- **Usage:** Most paths, such as `@RequestMapping("/api/v1/employee")`, correctly use nouns, but still any endpoints like `"/upload"` use verbs

### 3️⃣ Use Logical Nesting on Endpoints

- **Best Practice:** CRUD operations are organized under the shared base path `/api/v1/employee`.

#### Assignment 2 Implementation

- **Usage:** Endpoints for CRUD operations are organized but still need improve for complex CRUD

### 4️⃣ Handle errors gracefully and return standard error codes

- **Best Practice:** Responses include appropriate status codes (e.g., `204 No Content`, `404 Not Found`, `200 OK`, `400 Bad Request`, `500 Internal Server Error`).

#### Assignment 2 Implementation

- **Usage:** In Controller CRUD operations align with best practices, but still need improve for handling some scenario in one controller.

### 5️⃣ Allow filtering, sorting, and pagination

- **Best Practice:** APIs should support filtering, sorting, and pagination to handle large datasets efficiently.

#### Assignment 2 Implementation

- **Usage:** In Controller CRUD operations align with best practices, like filtering by department, but still not implement in Sorting and Pagination.

### 6️⃣ Maintain good security practices

- **Best Practice:** Employ SSL/TLS for encrypted communication between the client and service, and implement role-based access control to distribute privileges among users.

#### Assignment 2 Implementation

- **Usage:** In code SSL/TSL still not used, and check roles not implemented

### 7️⃣ Cache Data to Improve Performance
- **Best Practice:** Use caching to decrease database load and enhance response times, while carefully managing the risk of delivering outdated data.

#### Assignment 2 Implementation
- **No Caching:** In code caching is not implemented, leading to potential performance issues (in production environments).

### 8️⃣ Versioning APIs
#### Best Practices
- **API Versioning:**  Implement versioning by incorporating version numbers,like(v1/v2/others) in the URL to introduce changes without disrupting existing clients.

#### Assignment 2 Implementation
- **Versioning Implemented:** In code ,API versioning is in place with `v1`, as indicated by `@RequestMapping("/api/v1/employee")`.

### Summary of still not implement in assignment 2 
- **Endpoint Naming:** Update endpoints to use nouns instead of verbs.
- **Path Nesting:** Use nested paths to represent more complex relationships.
- **Filtering, Sorting, and Pagination:** Implement sorting and pagination to enhance data handling.
- **Security:** Implement SSL/TLS and role-based access control.
- **Caching:** Utilize caching to boost performance.

