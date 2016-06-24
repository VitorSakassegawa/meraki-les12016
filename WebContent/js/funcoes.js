/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//funcoes da pagina
function verificaCompra()
{
    var form = document.getElementById("formCompra");
    var cep = document.getElementById("txtCep").getAttribute("value");
    
    if(cep !== null)
    {
        form.submit();
    }
    else
    {
        alert("É preciso efetuar o calculo do frete para prosseguir!");
    }
}

function addLinguagem()
{
    var linguagem = document.getElementById("selecLinguages");
    var input = document.getElementById("txtLinguagem");
    
    if(input.getAttribute("value") === null || input.getAttribute("value") === "")
        input.setAttribute("value","");
    
    var valor = input.getAttribute("value") + linguagem.options[linguagem.selectedIndex].value + ",";
    
    input.setAttribute("value",valor);
}


