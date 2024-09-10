const btnAggiungiSpesa = document.querySelector('.btn-inserisciSpesa');
const inputNomeSpesa = document.querySelector('.input-nome');
const inputDataSpesa = document.querySelector('.input-data');
const inputCategoriaSpesa = document.querySelector('.input-categoria');
const inputAmmontareSpesa = document.querySelector('.input-ammontare');
const listaSpese = document.querySelector('.lista-spese');
document.addEventListener('DOMContentLoaded', fetchSpese);
document.addEventListener('DOMContentLoaded', fetchCategorie);
async function fetchSpese() {
    const response = await fetch('http://localhost:8080/expenses');
    spese = await response.json();
    spese.forEach(expense => {
        const listItem = document.createElement('li');
        if (expense.categories != null) {
            listItem.textContent = `${expense.categories.nome},${expense.data},${expense.nome},${expense.ammontare}`;
            listaSpese.appendChild(listItem);
        }

    });

}
async function fetchCategorie(){
    const response=await fetch('http://localhost:8080/categories');
    const categories=await response.json();
    categories.forEach(categories=>{
        const option=document.createElement('option');
        option.value=categories.id;
        option.textContent=categories.nome;
        inputCategoriaSpesa.appendChild(option);
    });
}
