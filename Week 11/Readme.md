# **Some basic Frontend and Angular Framework**

## **Some basic about html/css**

---

### **HTML (Hypertext Markup Language)**

HTML is the standard language used to create the structure of a web page. It uses elements like headings, paragraphs, links, and forms to define content.

Example:

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sample Page</title>
  </head>
  <body>
    <h1>Hello, World!</h1>
    <p>This is an example of HTML.</p>
  </body>
</html>
```

### **CSS (Cascading Style Sheets)**

CSS is used to style and layout HTML elements. It controls the look and feel of a webpage, including colors, fonts, spacing, and positioning.

Example:

```html
<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    color: #333;
  }

  h1 {
    color: #007bff;
  }
</style>
```

### **SCSS (Sassy CSS) and SASS (Syntactically Awesome Stylesheets)**

SCSS and SASS are extensions of CSS that add powerful features like variables, nested rules, mixins, and more.

- **SASS:** Uses indentation and omits semicolons and braces.
- **SCSS:** Uses the same syntax as CSS but allows advanced features.

### **Comparison Between CSS and SASS/SCSS**

| Feature              | CSS                                       | SASS/SCSS                                          |
| -------------------- | ----------------------------------------- | -------------------------------------------------- |
| **Syntax**           | Simple, standard CSS syntax.              | Adds advanced features (variables, nesting).       |
| **Variables**        | Not supported.                            | Supported using `$` (e.g., `$primary-color`).      |
| **Nesting**          | Not supported.                            | Allows nesting of selectors.                       |
| **Mixins/Functions** | Not supported.                            | Allows reusable styles with `@mixin`.              |
| **Partials**         | Not supported.                            | Can import partial files with `@import`.           |
| **Code Readability** | Simple but repetitive for complex styles. | Cleaner and more maintainable for large codebases. |

### **Example Comparison**

#### CSS:

```css
/* CSS example */
.primary-btn {
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
}

.primary-btn:hover {
  background-color: #0056b3;
}
```

#### SCSS:

```scss
// SCSS example
$primary-color: #007bff;
$primary-hover-color: #0056b3;

.primary-btn {
  background-color: $primary-color;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;

  &:hover {
    background-color: $primary-hover-color;
  }
}
```

### Key Advantages of SCSS/SASS Over CSS:

1. **Variables**: Store values like colors and fonts in variables for reuse.
2. **Nesting**: Write cleaner, more structured code by nesting selectors.
3. **Mixins**: Reuse chunks of code across your styles.
4. **Partials**: Organize styles into smaller, maintainable files.

In conclusion, while CSS is the base for styling, SCSS and SASS offer more powerful features for larger and more complex projects.

<!-- Run Server Json -->
<!-- npx json-server --watch db.json -->
