## What this application should teach us about?

## Index:
## Angular Fundamentals


|       |                                         |
| ----- | --------------------------------------- |
| **1** | Building blocks of Angular Apps         |
| **2** | Components                              |
| **3** | Generating Components Using Angular CLI |
| **4** | Templates                               |
| **5** | Directives                              |
| **6** | Dependency Injection                    |
| **7** | Generating Services Using Angular CLI   |



<br/>

## Displaying Data and Handling Events

|        |                    |
| ------ | ------------------ |
| **1**  | Property Binding   |
| **2**  | Attribute Binding  |
| **3**  | Adding Bootstrap   |
| **4**  | Class Binding      |
| **5**  | Style Binding      |
| **6**  | Event Binding      |
| **7**  | Event FIltering    |
| **8**  | Template Variables |
| **9**  | Two-Way Binding    |
| **10** | Pipes              |
| **11** | Custom Pipes       |

## Component

* Encapsulates the **Data**, the **HTML markup** and the **Logic**
* Used to break a (eg. a web site) view in several **smaller components**
* Like a sideBar, a footer, a navBar in **seperatelly components.**
* This is more **mantainable** and **re-usable** to code in Angular.
* A component also can break it's contents in others smallest components.
* Will be a component of a component!
* Logic for calling an HTTP service
* Component should NOT include any logic other then the presentation logic
* one of the reason that services are used in Angular
  
* Every application has an App component or a Root component.

## Decorators
Preceded by at and inside brackets, will tell how this component works, on *courses.component.ts > class:* 
```ts
@Component({
    selector: 'courses',     
    template: ``
})
```
## Selectors
on *courses.component.ts > class:*  
```ts
    export class CoursesComponent {
    selector:'courses';
```
Anywhere we have an element called courses angular will render the template inside that element fitting, on *courses.component.ts > annotations > template*:
```html
    <course> => "courses"
    <div class="courses"> => ".courses"
    <div id="courses" => "#courses"
```

## String Interpolation
on *courses.component.ts > annotations > template*: 
```html
    <h2> {{title}} (Interpolation) </h2>
    <img style="width: 30%;" src="{{ imageUrl }}" />
    '<h2>{{ getTitle() }}</h2>'    
```
on *courses.component.ts > class:*
```ts
    export class CoursesComponent {
        title = "List of Courses";
        getTitle() {
        return this.title;
        }
    imageUrl = "https://www.geteducated.com/wp-content/uploads/2019/11/online-tutoring-concept-ebooks-internet-courses-process-vector-staff-vector-id1053519062.jpg";
    
```


## Dependency Injection
on *courses.component.ts > class:* 

* Injecting/providing a dependency of a class into it's constructor
* It will initiate and passes it to the coursesComponent
* Angular has a dependency injection framework build into it
* Dependency, needs to be registered somewhere in our model 

on *app.module.ts*
```ts
    export class CoursesComponent {
        courses;
        //                      v Injection
        constructor(service: CoursesService) {
```

* Adding the provider, to dependency injection works
* If it is not register, an error should pop: "No provider for CoursesService!"
* When u register a dependency as a provider in a model, angular will create a 
* single instance of that class for that entire module.
* *Singleton Pattern* 
* To auto generate a service type in terminal: ng g s serviceName
* passing a parameter angular will always instantiate a new coursesService Option
* fake implementations for unit tests 
* no need to create a new Object variable:
* let service = new CoursesService();

on *courses.component.ts > class:*  
```ts
    export class CoursesComponent {
        courses;
        // TS way:
        this.courses = service.getCourses();
        }
```

on *app.module.ts*
```ts
    @NgModule({
        providers: [  
        CoursesService,
        AuthorsService
    ],
```
on *courses.component.ts > class:*  
```ts
export class CoursesComponent {
    courses;
    constructor(service: CoursesService) {
            this.courses = service.getCourses();
```



## Directives

Directives (*ngFOr) are used to manipulate the dom, on *courses.component.ts > annotations > template*:  
```html
    <ul>
        <!-- Loop variable in JS v -->
        <li *ngFor="let course of courses"> {{ course }}</li>
    </ul>
```

## Binding
> Binding =  vinculação

on *courses.component.ts > annotations > template*:
```html
    <table>
        <tr>
            <td [colspan]="colSpan"> </td>
        </tr>
    </table>
```

* Not Allowed! Can't bind to 'colspan' since it isn't a known property of 'td'
* There is a difference between DOM (Document Object Model) and HTML
* DOM is a model of objects that represents the structure of document, it's a tree of objects stored and rendered by the browser
* HTML is a markup language
* So the currently DOM does not have colSpan property yet
* This is the right syntax so dom and it's functions can be recognize.
on *courses.component.ts > annotations > template*: 
```html
    <table>
        <tr>                    
            <td [attr.colspan]="colSpan"> </td>
        </tr>
    </table>
```
on *courses.component.ts > class:*
```ts
    export class CoursesComponent {
        colSpan = 2;
    }
```

##  Property Binding 
on *courses.component.ts > annotations > template*: 
```html
    <h2 [textContent]="title"> (Property Binding - This comment won't show up!!) </h2>  
    <img style="width: 30%;"[src]="imageUrl" /> 
```

## Class Binding
on *courses.component.ts > annotations > template*: 
```html 
    <button class="btn btn-primary" [class.active]="isActive">Save</button> 
```

on *courses.component.ts > class:*
```ts
    export class CoursesComponent {
        isActive = false;
```

## Style Binding
on *courses.component.ts > annotations > template*: 
```html
    <button [style.backgroundColor]="isActive ? 'white' : 'gray'">Save</button>
```

on *courses.component.ts > class:*
```ts
    export class CoursesComponent {
        isActive = false;
```

## Event Binding
on *courses.component.ts > annotations > template*: 
```html
    <button (click)="onSave($event)"> Save </button>
```
on *courses.component.ts > class:*
```ts
    export class CoursesComponent {
        onSave($event) {
            // To stop event bubbling  // doesn't work at all :S
            $event.stopPropagation();

            window.alert("Button was clicked");
            console.log($event)
            }
```

## Event Bubbling
on *courses.component.ts > annotations > template*: 
```html
    <div (click)="onDivClicked()">
        <button (click)="onSave($event)"> Save </button>
    </div>
```
on *courses.component.ts > class:*
```ts
    export class CoursesComponent {
        onDivClicked() {
            console.log("Div was clicked")
    }
```

## Event Filtering
on *courses.component.ts > annotations > template*: 
```html
    <input (keyup)="onKeyUp($event)"/>
```
on *courses.component.ts > class:*
```ts
    onKeyUp ($event) {
        if ($event.keyCode == 13) // 13 = enter key
        window.alert("Enter Was pressed!")
    }
```
Here is the filter, for more specific keypress, same function below
on *courses.component.ts > annotations > template*: 
```html
    <input (keyup.enter)="onEnterKeyUp()"/> 
```

on *courses.component.ts > class:*
```ts
     onEnterKeyUp () {
        window.alert("Enter Was pressed!")
    }
```

## Template Variables
on *courses.component.ts > annotations > template*: 
```html
    <input (keyup.enter)="keyUp($event)"/>
```
on *courses.component.ts > class:*
```ts
     keyUp($event) {
        window.alert($event.target.value)
    }
```
the other way with template variable,
on *courses.component.ts > annotations > template*: 
```html
     <input #email (keyup.enter)="keyUpEmail(email.value)"/> Template Variables
```
on *courses.component.ts > class:*
```ts
    keyUpEmail(email) {
        window.alert(email)
    }

```

## Two-Way Binding