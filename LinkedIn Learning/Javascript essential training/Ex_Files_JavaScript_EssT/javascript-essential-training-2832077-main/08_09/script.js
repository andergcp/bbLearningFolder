/**
 * Passing data to functions through parameters.
 * @link https://developer.mozilla.org/en-US/docs/Glossary/Function
 * @link https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat
 *
 * List of ISO language codes:
 * @link http://www.lingoes.net/en/translator/langcode.htm
 */

const info = {
  title: "Start-ups",
  imageURL:
    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.jivochat.es%2Fblog%2Fcomunicacion%2Fmejores-startups.html&psig=AOvVaw0__RvobtUAOMw875eXQE2C&ust=1620957720811000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKD78prIxfACFQAAAAAdAAAAABAD",
  caption: "This image is about startups",
  paragraph: "I'm gonna be the CEO of a startup",
};

const createFigure = (imageURL, captionText) => {
  const figure = document.createElement("figure");
  const img = document.createElement("img");
  const caption = document.createElement("figcaption");
  img.setAttribute("src", imageURL);
  caption.innerHTML = captionText;
  figure.appendChild(img);
  figure.appendChild(caption);
  return figure;
};

const createArticle = (info) => {
  const newArticle = document.createElement("article");
  const imageFigure = createFigure(info.imageURL, info.caption);
  const title = document.createElement("h1");
  title.innerHTML = info.title;
  const paragraph = document.createElement("p");
  paragraph.innerHTML = info.paragraph;
  newArticle.appendChild(title);
  newArticle.appendChild(paragraph);
  newArticle.appendChild(imageFigure);
  return newArticle;
};

const main = document.querySelector("main");

main.appendChild(createArticle(info));
