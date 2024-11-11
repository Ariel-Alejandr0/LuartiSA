export function formatDate(date) {
    const dataAtual = new Date(date);
    
    const ano = dataAtual.getFullYear();
    const mes = String(dataAtual.getMonth() + 1).padStart(2, '0'); // Mes começa de 0, por isso adicionamos 1
    const dia = String(dataAtual.getDate()).padStart(2, '0');
    
    return `${ano}-${mes}-${dia}`;
}
