// The global objeect process give us info about the process running node
// With process we can get info from the terminal when beginning the application

console.log(process.pid);
console.log(process.versions.node);
console.log(process.argv); // Arguments passed to the process
console.log(process.argv[3] + process.argv[4]); // Arguments passed to the process

// The way we use the arguments is passing trhough flags
// Run: node globalProcess --user "Anderson Castiblanco" --greeting "Hola que tal"
const grab = (flag) => {
  let indexAfterFlag = process.argv.indexOf(flag) + 1;
  return process.argv[indexAfterFlag];
};

const greeting = grab("--greeting");
const user = grab("--user");
console.log(`${greeting} ${user}`);
