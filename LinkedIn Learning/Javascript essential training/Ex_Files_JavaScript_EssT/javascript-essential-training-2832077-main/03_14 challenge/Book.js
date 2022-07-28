class Book {
  constructor(pages, genre, author, dateLaunched, unitsSold) {
    (this.pages = pages),
      (this.genre = genre),
      (this.author = author),
      (this.dateLaunched = dateLaunched),
      (this.unitsSold = unitsSold);
  }
  changeUnitsSold(newUnits) {
    this.unitsSold = newUnits;
  }
  daysFromLaunching() {
    let launched = new Date(this.dateLaunched);
    let now = new Date();
    let elapsed = now - launched;
    let daysLaunched = Math.floor(elapsed / (1000 * 3600 * 24));
    return daysLaunched;
  }
}

export default Book;
