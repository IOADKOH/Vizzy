const btnAggiungiSpesa = document.querySelector('.btn-inserisciSpesa');
const inputNomeSpesa = document.querySelector('.input-nome');
const inputDataSpesa = document.querySelector('.input-data');
const inputCategoriaSpesa = document.querySelector('.input-categoria');
const inputAmmontareSpesa = document.querySelector('.input-ammontare');
const listaSpese = document.querySelector('.lista-spese');
const url='http://localhost:8080/expenses';
document.addEventListener('DOMContentLoaded', fetchSpese);
document.addEventListener('DOMContentLoaded', fetchCategorie);
async function fetchSpese() {
    const response = await fetch('http://localhost:8080/expenses');
    spese = await response.json();
    spese.forEach(expense => {
        const listItem = document.createElement('li');
        if (expense.categories != null) {
            listItem.textContent = `${expense.data},${expense.categories.nome},${expense.nome},${expense.ammontare+'€'}`;
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
btnAggiungiSpesa.addEventListener('click',aggiungiSpesa);
function aggiungiSpesa(){
    const data=inputDataSpesa.value;
    const nome=inputNomeSpesa.value;
    const ammontare=inputAmmontareSpesa.value;
    const categoria=inputCategoriaSpesa.options[inputCategoriaSpesa.selectedIndex].textContent;

    if(data===""|| nome===""||ammontare===""||categoria===""||isNaN(ammontare)){
        alert('Compila tutti i campi!');
        return;
    }

            // Crea un oggetto per la nuova spesa
            const newExpense = {
                data,
                id,
                ammontare,
                nome
            };

            // Invia la nuova spesa al backend tramite POST
            fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newExpense)
            })
            .then(response => response.json())
            .then(data => {
                console.log('Spesa aggiunta con successo:', data);
                loadExpenses(); // Ricarica la lista aggiornata delle spese
            })
            .catch(error => {
                console.error('Errore nell\'aggiunta della spesa:', error);
            });

            // Resetta i campi di input
            dateInput.value = '';
            categoryInput.value = '';
            amountInput.value = '';
            nameInput.value = '';
        }
        function loadExpenses() {
            fetch(API_URL)
                .then(response => response.json())
                .then(expenses => {
                    displayExpenses(expenses);
                })
                .catch(error => {
                    console.error('Errore nel caricamento delle spese:', error);
                });
        }
        function displayExpenses(expenses) {
            listaSpese.innerHTML = ''; // Pulisci la lista

            expenses.forEach(expense => {
                const row = document.createElement('li');
                row.innerHTML = `
                    <li>${expense.data},
                    ${getCategoryNameById(expense.categoryId)},
                    ${expense.nome},
                    ${expense.ammontare}</li>
                `;
                listaSpese.appendChild(row);
            });
        }

        // Carica le spese al caricamento della pagina
        window.onload = function() {
        fetchCategorie();
            loadExpenses(); // Recupera le spese dal backend
        };
        btnAggiungiSpesa.addEventListener('click', aggiungiSpesa);
      

