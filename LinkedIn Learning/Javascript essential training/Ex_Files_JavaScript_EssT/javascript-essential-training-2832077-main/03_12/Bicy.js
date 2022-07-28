class Bicy {
  constructor(wheelSize, wheelMaterial, type, material, color) {
    (this.wheel = {
      size: wheelSize,
      material: wheelMaterial,
    }),
      (this.type = type),
      (this.material = material),
      (this.color = color);
  }
  changeColor(newColor) {
    this.color = newColor;
  }
  changeWheelProperties(wheelSize, wheelMaterial) {
    this.wheel.size = wheelSize;
    this.wheel.material = wheelMaterial;
  }
}

export default Bicy;
