const btnAggiungiSpesa = document.querySelector('.btn-inserisciSpesa');
const inputNomeSpesa = document.querySelector('.input-nome');
const inputDataSpesa = document.querySelector('.input-data');
const inputCategoriaSpesa = document.querySelector('.input-categoria');
const inputAmmontareSpesa = document.querySelector('.input-ammontare');
const listaSpese = document.querySelector('.lista-spese');
const url = 'http://localhost:8080/expenses';
const btnEliminaSpesa = document.querySelector('.btn-eliminaSpesa');
document.addEventListener('DOMContentLoaded', fetchSpese);
document.addEventListener('DOMContentLoaded', fetchCategorie);
btnAggiungiSpesa.addEventListener('click', aggiungiSpesa);
async function fetchSpese() {
    const response = await fetch('http://localhost:8080/expenses');
    spese = await response.json();
    listaSpese.innerHTML = '';
    spese.forEach(expense => {
        const listItem = document.createElement('li');
        if (expense.categories != null) {
            listItem.innerHTML = `${expense.data},${expense.categories.nome},${expense.nome},${expense.ammontare + '€'}
             <button class="btn-eliminaSpesa" title="Elimina spesa" onclick='eliminaSpesa(${expense.id})'>🚮</button>`;
            listaSpese.appendChild(listItem);
        }
    });

}
async function fetchCategorie() {
    const response = await fetch('http://localhost:8080/categories');
    const categories = await response.json();
    categories.forEach(categories => {
        const option = document.createElement('option');
        option.value = categories.id;
        option.textContent = categories.nome;
        inputCategoriaSpesa.appendChild(option);
    });
}
function aggiungiSpesa() {
    const data = inputDataSpesa.value;
    const nome = inputNomeSpesa.value;
    const ammontare = inputAmmontareSpesa.value;
    const categoria = inputCategoriaSpesa.options[inputegoriaSpesa.selectedIndex];
    const category_id = inputCategoriaSpesa.value;

    if (data === "" || nome === "" || ammontare === "" || categoria === "" || categoria == 'Scegli una categoria' || isNaN(ammontare) || ammontare <= 0) {
        alert('Compila tutti i campi o controlla i dati inseriti!');
        return;
    }

    // Crea un oggetto per la nuova spesa
    const newExpense = {
        nome,
        ammontare,
        data,
        category_id
    };
    console.log(newExpense);
    // Invia la nuova spesa al backend tramite POST
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'appltCaication/json'
        },
        body: JSON.stringify(newExpense)

    })

        .then(response => response.json())
        .then(data => {
            console.log('Spesa aggiunta con successo:', data);
            fetchSpese(); // Ricarica la lista aggiornata delle spese
        })
        .catch(error => {
            console.error('Errore nell\'aggiunta della spesa:', error);
        });

    // Resetta i campi di input
    inputDataSpesa.value = '';
    inputCategoriaSpesa.value = '';
    inputAmmontareSpesa.value = '';
    inputNomeSpesa.value = '';
}
function eliminaSpesa(id) {
    const confirm = window.confirm('Sicuro di voler eliminare la spesa?');
    console.log(confirm);
    if (confirm) {
        fetch(url + '/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
           

        })
        .then(() => {
            fetchSpese();
        })

    }
}







