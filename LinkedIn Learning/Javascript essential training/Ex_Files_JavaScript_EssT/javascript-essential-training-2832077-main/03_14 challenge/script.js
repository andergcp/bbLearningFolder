/**
 * Challenge: Create a new object type
 *
 * - Create a new object type "Book" using a class or an object constructor function.
 * - Add at least 5 book objects.
 */

import Backpack from "./Backpack.js";
import Book from "./Book.js";

const everydayPack = new Backpack(
  "Everyday Backpack",
  30,
  "grey",
  15,
  26,
  26,
  false,
  "December 5, 2018 15:00:00 PST"
);

console.log("The everydayPack object:", everydayPack);
console.log("The pocketNum value:", everydayPack.pocketNum);
console.log("Days since aquired:", everydayPack.backpackAge());

const bookOne = new Book(300, "SciFi", "Douglas McGregor", "2015-03-20", 1240);
const bookTwo = new Book(400, "Arts", "Arthur McGregor", "2014-03-20", 1340);
const bookThree = new Book(
  500,
  "Entrepreneurship",
  "Cami McGregor",
  "2013-03-20",
  1440
);
const bookFour = new Book(600, "Comedy", "Anne McGregor", "2012-03-20", 1540);

console.log("These are my books:");
console.log(bookOne);
console.log(bookTwo);
console.log(bookThree);
console.log(bookFour);
console.log("I'm gonna change unist sold");
bookOne.changeUnitsSold(1300);
bookTwo.changeUnitsSold(1400);
bookThree.changeUnitsSold(1500);
bookFour.changeUnitsSold(1600);
console.log("After change");
console.log(bookOne);
console.log(bookTwo);
console.log(bookThree);
console.log(bookFour);
console.log("Days from launching");
console.log(bookOne.daysFromLaunching());
console.log(bookTwo.daysFromLaunching());
console.log(bookThree.daysFromLaunching());
console.log(bookFour.daysFromLaunching());
