class Fridge {
  constructor(
    color,
    liters,
    energyConsumption,
    doors,
    freezerLiters,
    minimiumTemp,
    noFrost
  ) {
    (this.color = color),
      (this.liters = liters),
      (this.energyConsumption = energyConsumption),
      (this.doors = doors),
      (this.freezer = {
        liters: freezerLiters,
        minimiumTemp: minimiumTemp,
        noFrost: noFrost,
      });
  }
  changeColor(newColor) {
    this.color = newColor;
  }
  changeLiters(newLiters) {
    this.liters = newLiters;
  }
}

export default Fridge;
