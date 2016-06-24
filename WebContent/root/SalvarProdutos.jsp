<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<html>
    <head>
                <div id="header" class="sticky clearfix">

                <!-- TOP NAV -->
                <header id="topNav">
                    <div class="container">

                        <!-- BUTTONS -->
                        <ul class="pull-right nav nav-pills nav-second-main">
                        </ul>
                        <!-- /BUTTONS -->

                        <!-- Logo -->
                        <a class="logo center" href="index.jsp">
                            <img src="../assets/images/meraki-letter2.png" alt="" />
                        </a>
            </div>

            <!-- 
                PAGE HEADER 
            -->
            <section class="page-header">
                <div class="container">

                    <h1>Cadastrar Produto</h1>

                    <!-- breadcrumbs -->
                    <ol class="breadcrumb">
                        <li><a href="../login.jsp">Home</a></li>
                        <li><a href="../Produtos.jsp">Produtos</a></li>
                        <li class="active">Cadastrar Produto</li>
                    </ol><!-- /breadcrumbs -->

                </div>
            </section>
            <!-- /PAGE HEADER -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
        <link rel="stylesheet" href="../js/css/bootstrap.min.css">
        <link rel="stylesheet" href="../js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="../js/css/bootstrap.css">
        <script src="../js/funcoes.js"></script>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="SalvarProduto">
            <input type="text" hidden="true" name="operacao" value="Salvar"/>
            <div class="container container-fluid"> <!-- Containter principal da aplicacao -->
                <div class="panel panel-info col-md-10" style="margin-top: 2%">
                    <div class="panel panel-heading">Insira os dados do produto!</div>
                    <!-- Alertas da Pagina -->
                    <c:if test="${requestScope.erroSalvarProduto.size() gt 0}">
                        <c:forEach var="erro" items="${requestScope.erroSalvarProduto}">
                            <div class="alert alert-danger">${erro}</div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.sucessSalvarProduto ne null}">
                        <div class="alert alert-success">${requestScope.sucessSalvarProduto}</div>
                    </c:if>
                    <!-- Fim dos alertas da pagina  -->
                    <div class="panel panel-body">
                        <div class="form form-group form-horizontal">
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Titulo</span>
                                <input type="text" placeholder="Nome do Produto" class="form-control" name="txtTitulo" value="${requestScope.produto.titulo}"/>
                            </div>

                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Genero </span>
                                <input type="text" placeholder="Masculino ou Feminino" name="txtGenero" class="form-control" value="${requestScope.produto.genero}"/>    
                            </div>
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Numeração</span>
                                <input type="number" min="0" class="form-control" name="txtNumeracao" placeholder="Digite a numeração" value="${requestScope.produto.numeracao}"/>
                            </div>
                            
							<div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Lançamento</span>
                                <input type="text" name="txtAnoLancamento" placeholder="2016" class="form-control" value="${requestScope.produto.ano}"/>
                            </div> 
                            
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Valor</span>
                                <input type="text" class="form-control" name="txtValor" placeholder="100,00" value="${requestScope.produto.valor}"/>
                            </div>
                            
                             <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Descrição</span>
                                <textarea class="form-control" maxlength="100" name="txtDescricao" placeholder="Tenis de corrida" value="${requestScope.produto.descricao}"></textarea>
                            </div>
                            
                            <!-- CheckedBox dos Campos -->
                            <label>Importado</label>
                            <input type="checkbox" name="ckbOrigem"/>
                            <label>Sintético</label>
                            <input type="checkbox" name="ckbMaterial"/>
                            <!-- Fim do checkBox! -->

                            <div class="input-group col-sm-4">
                                <span class="input-group-addon">Tipo</span>
                                <select class="form-control" id="selecLinguages" onchange="addLinguagem()" >
                                    <option>Running</option>
                                    <option>Trekking</option>
                                    <option>Casual</option>
                                    <option>Acadêmia</option>
                                    <option>Fashion</option>
                                </select>
                            </div>
                             
                             <div class="input-group col-sm-4" style="margin-bottom: 2%">
                             <span class="input-group-addon">Categoria</span>
                             <textarea class="form-control" maxlength="100" name="txtCategoria" placeholder="Trekking" value="${requestScope.produto.categoria}"></textarea>
                            </div>
                            
                            <div class="input-group col-sm-4" style="margin-top: 2%">
                                <span class="input-group-addon">Quantidade</span>
                                <input type="number" min="10" max="50" name="txtQuantidade" class="form-control" placeholder="Qtde. Estoque min. 10"/>
                            </div>
                            <div class="input-group col-sm-4" style="margin-top: 2%">
                                <span class="input-group-addon glyphicon glyphicon-picture"></span>
                                <input type="file" class="btn btn-default" name="txtImagem"/>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-footer form-inline">
                        <div class="form-group">
                            <input type="submit" value="Salvar" class="btn btn-success"/> 
                        </div>
                        <div class="form-group">
                            <a href="dashboard.jsp" class="btn btn-primary">Voltar</a>
                        </div>
                    </div>
                </div>
            </div> <!-- FIM do Containter principal da aplicacao -->
        </form>
        <!-- JavaScript for bootstrap !-->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/tests/vendor/jquery.min.js"></script>
        <script src="../js/dropdown.js"></script>
    </body>
</html>
