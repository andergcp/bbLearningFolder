/**
 * Challenge: Create an advanced function.
 * - Loop through backpackObjectArray to create an article with the class "backpack".
 * - Give the article the ID of the current backpack object.
 * - Set the inner HTML of the article to the existing HTML output provided in const content.
 * - Append each backpack object to the <main> element.
 */
//import Backpack from "./components/Backpack.js";
import data from "./components/data.js";

const main = document.querySelector(".maincontent");

function addArticle(bp) {
  const content = `
    <figure class="backpack__image">
      <img src=${bp.image} alt="" />
    </figure>
    <h1 class="backpack__name">${bp.name}</h1>
    <ul class="backpack__features">
      <li class="packprop backpack__volume">Volume:<span> ${
        bp.volume
      }l</span></li>
      <li class="packprop backpack__color">Color:<span> ${bp.color}</span></li>
      <li class="backpack__age">Age:<span> ${bp.backpackAge()} days old</span></li>
      <li class="packprop backpack__pockets">Number of pockets:<span> ${
        bp.pocketNum
      }</span></li>
      <li class="packprop backpack__strap">Left strap length:<span> ${
        bp.strapLength.left
      } inches</span></li>
      <li class="packprop backpack__strap">Right strap length:<span> ${
        bp.strapLength.right
      } inches</span></li>
      <li class="feature backpack__lid">Lid status:<span> ${
        bp.lidOpen ? "open" : "closed"
      }</span></li>
    </ul>`;
  const newArticle = document.createElement("article");
  newArticle.classList.add("backpack");
  newArticle.setAttribute("id", bp.id);
  newArticle.innerHTML = content;
  return newArticle;
}

const articles = data.map((backpack) => addArticle(backpack));
articles.forEach((article) => main.append(article));

/*const everydayPack = new Backpack(
  "pack01",
  "Everyday Backpack",
  30,
  "grey",
  15,
  26,
  26,
  false,
  "December 5, 2018 15:00:00 PST",
  "../assets/images/everyday.svg"
);
*/
