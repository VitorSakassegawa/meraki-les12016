<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 

<html lang="en">
    <!-- Bean do produto, para utilizar na consulta! -->
    <jsp:useBean id="produto" class="meraki.com.br.domain.Produto"/>
    <jsp:useBean id="produtoCmd" class="meraki.com.br.controle.web.command.impl.CommandConsultar"/>


    ${produto.setTitulo("")} <!-- Setando titulo do produto -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Bem Vindo - Administrador</title>

        <!-- Bootstrap core CSS -->
        <link href="../js/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../js/css/dashboard.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="../js/jquery.twbsPagination.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
                 
        <script>
            $(document).ready(function () {
                $("#tableDash").twbsPagination({
                    totalPages: 35,
                    visiblePages: 7
                });
            });
            
            function showGrafics()
            {
                var div = document.getElementById("relatorios");

                if (div.hidden === true)
                    div.hidden = false;
                else
                    div.hidden = true;
            }
            
            function atualizaQuantidade(buttonId)
            {
                var button = document.getElementById(buttonId);

                if (confirm("Deseja realmente atualizar o estoque do produto?!"))
                {
                    button.click();
                }
            }
            
            function deleteProduto(formId)
            {
                var form = document.getElementById(formId);
                
                if(confirm("Deseja realmente inativar: "+formId+"?!"))
                    form.submit();
            }
        </script>
    </head>

    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="../Produtos.jsp">Meraki - Administrador</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="dashboard.jsp">Ferramentas</a></li>
                        <li><a href="../Produtos.jsp">Inicio</a></li>
                    </ul>
                    <form class="navbar-form navbar-right" method="post" action="ConsultaProdutos">
                        <div class="form form-group">
                            <input type="text" class="form-control" placeholder="Titulo" name="txtProcuraProduto">
                        </div>
                        <div class="form form-group">
                            <select class="form-control" name="selGenero">
                            <option>Genero</option>
                            <option>Masculino</option>
                            <option>Feminino</option>
<!--                                 <option>Categoria</option> -->
<!--                                 <option>Corrida</option> -->
<!--                                 <option>Trekking</option> -->
<!--                                 <option>Natação</option> -->
<!--                                 <option>Crossfit</option> -->
<!--                                 <option>Basquete</option> -->
<!--                                 <option>Triathlon</option> -->
                            </select>
                        </div>
                        <div class="form form-group">
                            <input type="number" min="15" max="100" placeholder="Numeracao" name="txtNumeracao" class="form-control"/>
                        </div>
                        <div class="form form-group">
                            <input type="number" min="2000" max="2015" placeholder="Ano" name="txtAno" class="form-control"/>
                        </div>
                        <button type="submit" class="bnt btn-success form-control">
                            <span class="glyphicon glyphicon-saved"></span>
                        </button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li class="active"><a href="#">Lista de Produtos <span class="sr-only">(current)</span></a></li>
<!--                         <li><a class="btn btn-link"onclick="showGrafics()">Relatórios de Vendas (Estado)</a></li> -->   
						<li><a href="../RelatorioClientes.jsp">Relatórios de Vendas (Lucro - Cliente)</a></li>                     
						<li><a href="../RelatorioClientesIdade.jsp">Relatórios de Vendas (Lucro - Idade)</a></li>    
						<li><a href="../RelatorioClientesEstado.jsp">Relatórios de Vendas (Lucro - Estado)</a></li>
						<li><a href="../RelatorioClientesCompras.jsp">Relatórios de Vendas (Qtde. Compras)</a></li>                                                                              
                        <li><a href="SalvarProdutos.jsp">Cadastrar Produtos</a></li>
						<li><a href="../Trocas.jsp">Aprovar Trocas</a></li>

                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div class="form form-inline">
                        <h1 class="page-header">Dashboard</h1>
                        <c:if test="${MsgExcluirProduto ne null}">
                            <div class="alert alert-success">${MsgExcluirProduto}</div>
                            <% request.setAttribute("MsgExcluirProduto",null); %>
                        </c:if>
                        <c:if test="${erroMsgEstoque ne null}">
                            <c:forEach var="erro" items="${erroMsgEstoque}">
                                <div class="alert alert-danger">${erro}</div>
                                <% request.setAttribute("erroMsgEstoque", null); %>
                            </c:forEach>
                        </c:if>

                        <c:if test="${requestScope.sucessAtualizarProduto ne null}">
                            <div class="alert alert-success">${requestScope.sucessAtualizarProduto}</div>
                            <% request.setAttribute("sucessAtualizarProduto", null); %>
                        </c:if>

                        <c:if test="${sucessMsgEstoque ne null}">
                            <div class="alert alert-success">${sucessMsgEstoque}</div>
                            <% request.setAttribute("sucessMsgEstoque", null); %>
                        </c:if>
                    </div>

                    <h2 class="sub-header form form-inline">
                        Bem Vindo - ${sessionScope.user.nome}
                    </h2>

                    <c:if test="${requestScope.MsgExcluirProduto ne null}">
                        <div class="label label-success">
                            ${requestScope.MsgExcluirProduto}
                        </div>
                    </c:if>
                    <!--  -->
                    <div class="table-responsive">
                        <table class="table table-striped" id="tableDash">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Titulo</th>
                                    <th>Genero</th>
                                    <th>Numeracao</th>
                                    <th class="text-center">Lançamento</th>
                                    <th class="text-center">Estoque</th>
                                    <th>Editar</th>
                                    <th>Excluir</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${requestScope.produtos eq null}">
                                    <% request.setAttribute("produtos", produtoCmd.execute(produto).getEntidades());%>
                                </c:if>

                                <c:forEach var="jg" items="${requestScope.produtos}">
                                    <tr 
                                        <c:if test="${jg.quantidade lt 5}"> 
                                            style="background-color: #e7c3c3" 
                                        </c:if>>
                                        <td>
                                            ${jg.id} 
                                        </td>
                                        <td>
                                            ${jg.titulo} 
                                        </td>
                                        <td>
                                            ${jg.genero} 
                                        </td>
                                        <td>
                                            ${jg.numeracao} 
                                        </td>
                                        <td class="text-center">
                                            ${jg.ano}
                                        </td>
                                        <td class="text-center">
                                            <form method="post" action="AtualizaQuantidade">
                                                <div class="form form-inline">
                                                    <input type="text" name="txtId" value="${jg.id}" hidden="true"/>
                                                    <input type="text" name="operacao" value="Atualizar" hidden="true"/>
                                                    <input type="number" value="${jg.quantidade}" name="txtQuantidade" class="form-control text-center"/>
                                                    <input type="submit" id="${jg.id.toString()}" hidden="true"/>
                                                    <input type="button" id="btnEstoque" value="Atualizar" class="btn btn-link" onclick="atualizaQuantidade(${jg.id.toString()})"/>
                                                </div>
                                            </form>
                                            <!-- ${jg.quantidade} -->
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <form method="post" action="EditarProduto" >
                                                    <input type="text" value="${jg.id}" name="txtId" hidden="true"/>
                                                    <input type="text" value="${jg.titulo}" name="txtTitulo" hidden="true"/>
                                                    <input type="text" value="${jg.genero}" name="txtGenero" hidden="true"/>
                                                    <input type="text" value="${jg.numeracao}" name="txtNumeracao" hidden="true"/>
                                                    <input type="text" value="${jg.ano}" name="txtAno" hidden="true"/>
                                                    <input type="text" value="${jg.descricao}" name="txtDescricao" hidden="true"/>
                                                    <input type="text" value="${jg.valor}" name="txtValor" hidden="true"/>
                                                    <input type="text" value="${jg.categoria}" name="txtCategoria" hidden="true"/>
                                                    <input type="text" value="${jg.origem}" name="txtOrigem" hidden="true"/>
                                                    <input type="text" value="${jg.material}" name="txtMaterial" hidden="true"/>
                                                    <button type="submit" data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                                                        <span class="glyphicon glyphicon-pencil"></span>
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <form method="post" action="ExcluirProduto" id="${jg.titulo}">
                                                    <input type="text" value="Excluir" name="operacao" hidden="true"/>
                                                    <input type="text" value="${jg.id}" name="txtIdProduto" hidden="true"/>
                                                    <input type="text" value="${jg.titulo}" name="txtTitulo" hidden="true"/>
                                                    <button type="button" class="btn btn-default dropdown-toggle" onclick="deleteProduto('${jg.titulo}')">
                                                        <span class="glyphicon glyphicon-trash"></span>
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../../dist/js/bootstrap.min.js"></script>
        <script src="../../assets/js/docs.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
