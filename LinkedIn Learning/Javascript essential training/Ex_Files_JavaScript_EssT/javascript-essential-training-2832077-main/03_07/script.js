/**
 * Create a different objects.
 */

const fridge = {
  color: "gray",
  liters: 400,
  energyConsumption: "200w",
  doors: 2,
  freezer: {
    liters: 80,
    minimiumTemp: -23,
    noFrost: true,
  },
};

console.log("Fridge characts:", fridge);
console.log("Feeze characts:", fridge.freezer);
