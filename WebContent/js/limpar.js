/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//funcao para limpar o carrinho
function limpar()
{
    if(confirm("Deseja Limpar seu Carinho? Todos os seus Produtos serão apagados!"))
    {
        document.getElementById("FormClear").submit();  //submentendo a pagina
    }
}

