/**
 * Create an object constructor function for the Backpack object type.
 * @link https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/new
 */

import Fridge from "./Fridge.js";
import Bicy from "./Bicy.js";

const myFridge = new Fridge("blue", 400, "200w", 2, 100, -20, true);
console.log("This is my fridge:", myFridge);
console.log("I'm gonna change the color and liters of my fridge");
myFridge.changeColor("red");
myFridge.changeLiters(600);
console.log("This is my fridge after changes:", myFridge);

const myBicy = new Bicy(29, "aluminum", "MTB", "aluminum", "red");
console.log("This is my new bicy: ", myBicy);
console.log("I'm gonna change my bicy's properties");
myBicy.changeWheelProperties(27, "carbon");
myBicy.changeColor("green");
console.log("This is my bicy after changes:", myBicy);
