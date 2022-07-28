/**
 * Create a Backpack object.
 */

const backpack = {
  name: "Everyday Backpack",
  volume: 30,
  color: "grey",
  pocketNum: 15,
  strapLength: {
    left: 26,
    right: 26,
  },
  lidOpen: false,
  toggleLid: function (newLid) {
    this.lidOpen = newLid;
  },
  changeColor: function (newColor) {
    this.color = newColor;
  },
};

console.log("The backpack object:", backpack);
console.log("The pocketNum value:", backpack.pocketNum);

console.log("Lid before:", backpack.lidOpen);
backpack.toggleLid(true);
console.log("Lid after:", backpack.lidOpen);
