### Constructors

```ts
    class aPoint {
        x: number;
        y: number;

        // Constructor:

        // constructor(x: number, y: number) {
        constructor(x?: number, y?: number) {   // ? means that this is a variable with opitional values
            this.x = x;
            this.y = y;
        }

        // Method:
        draw() {
            console.log('X: ' + this.x + ', Y: ' + this.y);
        }
    }

    let apoint = new aPoint(); 
    point.draw();
```

<br>

#### Transpiling:
```sh
    sudo npm install -g typescript
    tsc main.ts | node main.js
```
