// console.log("Hello World!");

// function getPokemon() {
//   fetch("https://pokeapi.co/api/v2/pokemon/754")
//     .then((response) => response.json())
//     .then((data) => {
//       console.log(data);
//       document.querySelector("h1").textContent = data.name;
//     })
//     .catch((error) => console.error(error));
// }

// getPokemon();

const documents = document.querySelector("button");

function onClick(e) {
  console.log("clicked");
  // some logic
}
console.log(documents);
documents.addEventListener("click", onClick);

(a, b) => a * b;

(a, c) => {
  return a * c;
};
