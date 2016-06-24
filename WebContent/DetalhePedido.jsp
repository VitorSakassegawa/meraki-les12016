<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<!DOCTYPE html>
<html>
    <head>
            <head>
              <head>
                <!-- TOP NAV -->
                <header id="topNav">
                    <div class="container">

                        <!-- BUTTONS -->
                        <ul class="pull-right nav nav-pills nav-second-main">
                        </ul>
                        <!-- /BUTTONS -->

                        <!-- Logo -->
                        <a class="logo center" href="Produtos.jsp">
                            <img src="assets/images/meraki-letter2.png" alt="" />
                        </a>
            </div>

            <!-- 
                PAGE HEADER 
            -->
            <section class="page-header">
                <div class="container">

                    <h1>Detalhes do pedido</h1>

                    <!-- breadcrumbs -->
                    <ol class="breadcrumb">
                        <li><a href="login.jsp">Home</a></li>
                        <li><a href="Produtos.jsp">Produtos</a></li>
                        <li class="active">Detalhes do pedido</li>
                    </ol><!-- /breadcrumbs -->

                </div>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalhe do Pedido</title>
        <!-- BootStrap -->
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
    </head>
    <body style="background-color: whitesmoke">
        <div class="container control-label center-block">
            <div class="form-inline" style="margin-top: 2%"> <!-- Form de ajuste principal -->
                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <strong>${requestScope.pedido.id} - Informações do Pedido</strong>
                    </div>
                    <div class="panel panel-body">
                        <div class="form-horizontal">
                            <div class="form-inline text-center">
                                <h4>
                                    Data do Pedido: <div class="label label-success"><f:formatDate pattern="dd/MM/yyyy" value="${requestScope.pedido.dataPedido.getTime()}"></f:formatDate></div>
                                    Forma de Pagamento: <div class="label label-success">${requestScope.pedido.tipoPagamento}</div>
                                    Posição:<div class="label label-success"> ${requestScope.pedido.posicao}</div>
                                    Status:<div class="label label-success">${requestScope.pedido.status}</div>
                                </h4>
                            </div>
                            <h3 class="well well-sm text-center">Endereço de Entrega</h3>
                            <table class="table table-responsive table-striped text-center">
                                <td class="text-info"><strong>Logradouro</strong></td>
                                <td class="text-info"><strong>N.</strong></td>
                                <td class="text-info"><strong>Bairro</strong></td>
                                <td class="text-info"><strong>CEP</strong></td>
                                <td class="text-info"><strong>Cidade</strong></td>
                                <td class="text-info"><strong>Estado</strong></td>

                                <!-- Linhas da tabela -->
                                <tr>
                                    <td>${requestScope.pedido.entrega.logradouro}</td>
                                    <td>${requestScope.pedido.entrega.numero}</td>
                                    <td>${requestScope.pedido.entrega.bairro}</td>
                                    <td>${requestScope.pedido.entrega.cep}</td>
                                    <td>${requestScope.pedido.entrega.cidade}</td>
                                    <td>${requestScope.pedido.entrega.estado}</td>
                                </tr>
                            </table>

                            <h3 class="well well-sm text-center">Itens do Pedido</h3>
                            <table class="table table-responsive table-striped">
                                <td class="text-info"><strong>Produto</strong></td>
                                <td class="text-info"><strong>Quantidade</strong></td>
                                <td class="text-info"><strong>Unitário</strong></td>
                                <td class="text-info"><strong>Total</strong></td>
                                <td class="text-info"><strong>Total Desconto</strong></td>

                                <!-- Linhas da tabela -->
                                <c:forEach var="itens" items="${requestScope.pedido.itens}">
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${itens.desconto ne null}">
                                                    <form method="post" action="InfoProduto">
                                                        <input hidden="true" type="text" name="operacao" value="Consultar"/>
                                                        <input hidden="true" type="text" value="${itens.produto.titulo}" name="txtTitulo"/>
                                                        <input type="submit" value="${itens.produto.titulo}" class="btn btn-link"/>
                                                        <span class="label label-success text-right">-<f:formatNumber pattern="##">${itens.desconto * 100}</f:formatNumber></span>
                                                        </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form method="post" action="InfoProduto">
                                                        <input hidden="true" type="text" name="operacao" value="Consultar"/>
                                                        <input hidden="true" type="text" value="${itens.produto.titulo}" name="txtTitulo"/>
                                                        <input type="submit" value="${itens.produto.titulo}" class="btn btn-link"/>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${itens.quantidade}</td>
                                        <td><f:formatNumber pattern="###,###.##" value="${itens.produto.valor}"></f:formatNumber></td>
                                        <td><f:formatNumber pattern="###,###.##" value="${itens.produto.valor * itens.quantidade}"></f:formatNumber></td>
                                        <td class="text-success"><f:formatNumber pattern="###,###.##" value="${itens.valorTotalItem()}"></f:formatNumber></td>
                                        </tr>
                                </c:forEach>
                            </table>
                            <h3 class="well well-sm">Resumo dos Valores</h3>
                            <div class="form-inline">
                                <h3>
                                    Sub-Total:<div class="label label-success">${requestScope.pedido.ValorTotal()}</div>
                                    +
                                    Frete:<div class="label label-success">${requestScope.pedido.frete.valor}</div>
                                    <strong>= </strong>
                                    <div class="label label-success">
                                        <f:formatNumber pattern="###,###.##">
                                            ${requestScope.pedido.ValorTotal() + requestScope.pedido.frete.valor}
                                        </f:formatNumber>
                                    </div>
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-footer">
                        <div class="form form-inline">
                            <a href="Pedidos.jsp" class="btn btn-default">Pedidos</a>
                            <a href="Produtos.jsp" class="btn btn-primary">Voltar</a>
                        </div>                        
                    </div>
                </div>
            </div> <!-- Form de ajuste principal -->
        </div>
                                                                   <!-- FOOTER -->
            <footer id="footer">
                <div class="container">

                    <div class="row">
                        
                        <div class="col-md-3">
                            <!-- Footer Logo -->
                            <img class="footer-logo" src="assets/images/logoo.png" alt="" />

                            <!-- Small Description -->
                            <p>Seu esporte, nossa paixão</p>

                            <!-- Contact Address -->
                            <address>
                                <ul class="list-unstyled">
                                    <li class="footer-sprite address">
                                        R. Carlos Barattino, Nº 908<br>
                                        Mogi das Cruzes, SP,  Brasil<br>
                                    </li>
                                    <li class="footer-sprite phone">
                                        Telefone: +55 11 4699-2799
                                    </li>
                                    <li class="footer-sprite email">
                                        <a href="mailto:support@meraki.com.br">support@meraki.com.br</a>
                                    </li>
                                </ul>
                            </address>
                            <!-- /Contact Address -->

                        </div>

                        <div class="col-md-3">

                            <!-- Latest Blog Post -->
                            <h4 class="letter-spacing-1">LATEST NEWS</h4>
                            <ul class="footer-posts list-unstyled">
                                <li>
                                    <a href="#">Donec sed odio dui. Nulla vitae elit libero, a pharetra augue</a>
                                    <small>29 June 2015</small>
                                </li>
                                <li>
                                    <a href="#">Nullam id dolor id nibh ultricies</a>
                                    <small>29 June 2015</small>
                                </li>
                                <li>
                                    <a href="#">Duis mollis, est non commodo luctus</a>
                                    <small>29 June 2015</small>
                                </li>
                            </ul>
                            <!-- /Latest Blog Post -->

                        </div>

                        <div class="col-md-2">

                            <!-- Links -->
                            <h4 class="letter-spacing-1">EXPLORE MERAKI</h4>
                            <ul class="footer-links list-unstyled">
                                <li><a href="#">Home</a></li>
                                <li><a href="#">About Us</a></li>
                                <li><a href="#">Our Services</a></li>
                                <li><a href="#">Our Clients</a></li>
                                <li><a href="#">Our Pricing</a></li>
                                <li><a href="#">Smarty Tour</a></li>
                                <li><a href="#">Contact Us</a></li>
                            </ul>
                            <!-- /Links -->

                        </div>

                        <div class="col-md-4">

                            <!-- Newsletter Form -->
                            <h4 class="letter-spacing-1">KEEP IN TOUCH</h4>
                            <p>Subscribe to Our Newsletter to get Important News &amp; Offers</p>

                            <form class="validate" action="php/newsletter.php" method="post" data-success="Subscribed! Thank you!" data-toastr-position="bottom-right">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                    <input type="email" id="email" name="email" class="form-control required" placeholder="Enter your Email">
                                    <span class="input-group-btn">
                                        <button class="btn btn-success" type="submit">Subscribe</button>
                                    </span>
                                </div>
                            </form>
                            <!-- /Newsletter Form -->

                            <!-- Social Icons -->
                            <div class="margin-top-20">
                                <a href="#" class="social-icon social-icon-border social-facebook pull-left" data-toggle="tooltip" data-placement="top" title="Facebook">

                                    <i class="icon-facebook"></i>
                                    <i class="icon-facebook"></i>
                                </a>

                                <a href="#" class="social-icon social-icon-border social-twitter pull-left" data-toggle="tooltip" data-placement="top" title="Twitter">
                                    <i class="icon-twitter"></i>
                                    <i class="icon-twitter"></i>
                                </a>

                                <a href="#" class="social-icon social-icon-border social-gplus pull-left" data-toggle="tooltip" data-placement="top" title="Google plus">
                                    <i class="icon-gplus"></i>
                                    <i class="icon-gplus"></i>
                                </a>

                                <a href="#" class="social-icon social-icon-border social-linkedin pull-left" data-toggle="tooltip" data-placement="top" title="Linkedin">
                                    <i class="icon-linkedin"></i>
                                    <i class="icon-linkedin"></i>
                                </a>

                                <a href="#" class="social-icon social-icon-border social-rss pull-left" data-toggle="tooltip" data-placement="top" title="Rss">
                                    <i class="icon-rss"></i>
                                    <i class="icon-rss"></i>
                                </a>
                    
                            </div>
                            <!-- /Social Icons -->

                        </div>

                    </div>

                </div>

                <div class="copyright">
                    <div class="container">
                        <ul class="pull-right nomargin list-inline mobile-block">
                            <li><a href="#">Terms &amp; Conditions</a></li>
                            <li>&bull;</li>
                            <li><a href="#">Privacy</a></li>
                        </ul>
                        &copy; All Rights Reserved, HD-18
                    </div>
                </div>
            </footer>
            <!-- /FOOTER -->
    </body>
    <!-- JavaScript dos Componentes do BootStrap !-->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
