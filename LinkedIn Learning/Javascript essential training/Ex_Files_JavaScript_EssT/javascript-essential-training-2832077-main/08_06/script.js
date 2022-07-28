/**
 * How arrow functions help us with scoping.
 * @link https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/Arrow_functions#this_and_Arrow_Functions
 * @link https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/this
 */

const title = "This is my title Ander";

function myFunction(title) {
  const newTitle = document.createElement("h1");
  newTitle.innerHTML = title;
  return newTitle;
}

const article = document.querySelector("article");
article.append(myFunction(title));

const subtitle = "A subtitle";
const otherFunction = function (subtitle) {
  const newSubtitle = document.createElement("h2");
  newSubtitle.innerHTML = subtitle;
  return newSubtitle;
};

article.append(otherFunction(subtitle));

(function () {
  const paragraph = document.createElement("p");
  paragraph.innerHTML = "This is a new paragraph";
  article.append(paragraph);
})();

const lastFunction = () => {
  const paragraph = document.createElement("p");
  paragraph.innerHTML = "This is another paragraph";
  article.append(paragraph);
};
lastFunction();
