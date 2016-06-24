<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

                    <h1>Atualizção Produto</h1>

                    <!-- breadcrumbs -->
                    <ol class="breadcrumb">
                        <li><a href="../login.jsp">Home</a></li>
                        <li><a href="../Produtos.jsp">Produtos</a></li>
                        <li class="active">Cadastrar Produto</li>
                    </ol><!-- /breadcrumbs -->

                </div>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
        <link rel="stylesheet" href="../js/css/bootstrap.min.css">
        <link rel="stylesheet" href="../js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="../js/css/bootstrap.css">
        <script src="../js/funcoes.js"></script>
        <title>Atualizar Produto</title>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="AtualizarProduto">
            <input type="text" value="${requestScope.produto.id}" hidden="true" name="txtId"/>
            <input type="text" hidden="true" name="operacao" value="Atualizar"/>
            <div class="container container-fluid"> <!-- Containter principal da aplicacao -->
                <div class="panel panel-info col-md-10" style="margin-top: 2%">
                    <div class="panel panel-heading">Dados do Produto!</div>
                    <!-- Alertas da Pagina -->
                    <c:if test="${requestScope.erroAtualizarProduto ne null}">
                        <c:forEach var="erro" items="${requestScope.erroAtualizarProduto}">
                            <div class="alert alert-danger">${erro}</div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.sucessAtualizarProduto ne null}">
                        <div class="alert alert-success">${requestScope.sucessAtualizarProduto}</div>
                    </c:if>
                    <!-- Fim dos alertas da pagina  -->
                    <div class="panel panel-body">
                        <div class="form form-group form-horizontal">
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Titulo</span>
                                <input type="text" placeholder="Nome do Produto" class="form-control" name="txtTitulo" value="${requestScope.produto.titulo}"/>
                            </div>

                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Descrição</span>
                                <!-- <textarea class="form-control" maxlength="100" name="txtDescricao">
                                    ${requestScope.produto.descricao.trim()}
                                </textarea> -->
                                    <input type="text" name="txtDescricao" maxlength="50" class="form-control" value="${requestScope.produto.descricao}"/>
                            </div>

                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Genero </span>
                                <input type="text" placeholder="action,funny" name="txtGenero" class="form-control" value="${requestScope.produto.genero}"/>    
                            </div>
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Numeração</span>
                                <input type="number" min="0" class="form-control" name="txtNumeracao" placeholder="Digite a numeração" value="${requestScope.produto.numeracao}"/>
                            </div>
                            <!-- 
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Plataforma</span>
                                <input type="text" class="form-control" placeholder="Nome da Plataforma"/>                           
                            </div>
                            -->
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Valor</span>
                                <input type="text" class="form-control" name="txtValor" placeholder="50.00" value="${requestScope.produto.valor}" min="10"/>
                            </div>

                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Lançamento</span>
                                <input type="text" name="txtAno" placeholder="2015" class="form-control" value="${requestScope.produto.ano}"/>
                            </div> 
                            <!-- CheckedBox dos Campos -->
                            <label>Importado</label>
                            <c:choose>
                                <c:when test="${requestScope.produto.origem eq 'S'}">
                                    <input type="checkbox" name="ckbOrigem" checked="ON"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" name="ckbOrigem"/>
                                </c:otherwise>
                            </c:choose>                        
                                
                            <label>Sintetico</label>
                            <c:choose>
                                <c:when test="${requestScope.produto.material eq 'S'}">
                                    <input type="checkbox" name="ckbMaterial" checked="ON"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" name="ckbMaterial" />
                                </c:otherwise>
                            </c:choose>
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
                            <input type="text" name="txtCategoria" id="txtCategoria" class="form-control" value="${requestScope.produto.categoria}"/>
                        </div>
                    </div>
                    <div class="panel panel-footer form-inline">
                        <div class="form-group">
                            <input type="submit" value="Atualizar" class="btn btn-success"/> 
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
