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

References

- [HTML](https://www.w3schools.com/css/)
- [CSS](https://www.w3schools.com/html/)
- [SCSS](https://sass-lang.com/guide/)

## Components Lifecycle

The Angular lifecycle consists of a sequence of events that happen from the creation of a component to its destruction. Understanding these lifecycle hooks allows developers to intervene at specific stages to perform operations like data fetching, setting up subscriptions, or cleaning up resources. Here are the main lifecycle hooks in Angular:

### 1. **ngOnChanges**

- **Triggered:** Whenever the input properties of a component or directive change.
- **Usage:** It is often used to respond to changes in input properties (e.g., `@Input` bindings). It receives a `SimpleChanges` object containing the current and previous values of the changed properties.
- Example:
  ```typescript
  ngOnChanges(changes: SimpleChanges) {
    console.log('Input property changed', changes);
  }
  ```

### 2. **ngOnInit**

- **Triggered:** Once after the first `ngOnChanges` is called. It's called after the component's input properties have been initialized.
- **Usage:** Commonly used for initialization logic, such as fetching data, setting up default values, or starting subscriptions.
- Example:
  ```typescript
  ngOnInit() {
    this.loadData();
  }
  ```

### 3. **ngDoCheck**

- **Triggered:** During every change detection cycle, after `ngOnChanges` and `ngOnInit`.
- **Usage:** It allows developers to implement custom change detection logic. Typically, it is used when more control over change detection is needed.
- Example:
  ```typescript
  ngDoCheck() {
    console.log('Change detection cycle triggered');
  }
  ```

### 4. **ngAfterContentInit**

- **Triggered:** Once after Angular projects external content into the component’s view (via `ng-content`).
- **Usage:** Used for performing actions after content projection. This is relevant when using `ng-content` to insert content from a parent component.
- Example:
  ```typescript
  ngAfterContentInit() {
    console.log('ng-content initialized');
  }
  ```

### 5. **ngAfterContentChecked**

- **Triggered:** After every check of projected content, following `ngAfterContentInit`.
- **Usage:** It runs after the content is checked and is used for responding to changes in projected content.
- Example:
  ```typescript
  ngAfterContentChecked() {
    console.log('Projected content checked');
  }
  ```

### 6. **ngAfterViewInit**

- **Triggered:** Once after Angular initializes the component's view and child views.
- **Usage:** Used for initializing logic that depends on the view being fully initialized. For example, querying DOM elements or setting up view-based subscriptions.
- Example:
  ```typescript
  ngAfterViewInit() {
    console.log('View initialized');
  }
  ```

### 7. **ngAfterViewChecked**

- **Triggered:** After every check of the component's view and child views, following `ngAfterViewInit`.
- **Usage:** It is used to respond after the view has been checked. It’s often used in conjunction with change detection.
- Example:
  ```typescript
  ngAfterViewChecked() {
    console.log('View checked');
  }
  ```

### 8. **ngOnDestroy**

- **Triggered:** Just before Angular destroys the component or directive.
- **Usage:** This is used for cleanup activities such as unsubscribing from Observables, stopping timers, or detaching event handlers to avoid memory leaks.
- Example:
  ```typescript
  ngOnDestroy() {
    console.log('Component destroyed');
    this.subscription.unsubscribe();
  }
  ```

### Summary of Lifecycle Order:

1. **ngOnChanges**
2. **ngOnInit**
3. **ngDoCheck**
4. **ngAfterContentInit**
5. **ngAfterContentChecked**
6. **ngAfterViewInit**
7. **ngAfterViewChecked**
8. **ngOnDestroy**

```
ngOnChanges() → ngOnInit() → ngDoCheck() → ngAfterContentInit() → ngAfterContentChecked() → ngAfterViewInit() → ngAfterViewChecked() → ngOnDestroy()
```

Understanding these hooks allows for better control over the component's behavior during its lifecycle and enables more efficient resource management.

Reference
[angular docs](https://angular.dev/guide/components/lifecycle#ngoninit)

<!-- Run Server Json -->
<!-- npx json-server --watch db.json -->
<!-- ng generate component shared/components/header
ng generate component shared/components/footer -->

<!-- home module -->
<!-- ng generate module modules/home --routing
ng generate component modules/home/components/home -->

<!-- sub home -->
<!-- ng generate component modules/home/components/about-us -->

<!-- customer module -->
<!-- ng generate module modules/customers --routing -->

<!-- sub customer-->
<!-- ng generate component modules/customers/components/list-customers -->
<!-- ng generate component modules/customers/components/create-customers -->
<!-- ng generate component modules/customers/components/edit-customers -->

<!-- auth module -->
<!-- ng generate module modules/auth --routing -->

<!-- sub auth module -->
<!-- ng generate component modules/auth/components/signin -->
<!--
onchange is press enter
ngmodel is press click
input is typing
 -->
