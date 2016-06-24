<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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

                    <h1>Troca de Produtos</h1>

                    <!-- breadcrumbs -->
                    <ol class="breadcrumb">
                        <li><a href="login.jsp">Home</a></li>
                        <li><a href="Produtos.jsp">Produtos</a></li>
                        <li class="active">Troca de Produtos</li>
                    </ol><!-- /breadcrumbs -->

                </div>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Listar Pedido</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="ConsultarPedido">
            <input type="text" value="Consultar" hidden="true" name="operacao"/>
            <div class="container">
                <!-- div contendo os campos de pesquisa !-->
                <div id="divPesquisa">
                    <div class="input-group form-inline" style="margin-top: 10px">
                        <span class="input-group-addon">Data do pedido</span>
                        <input type="date" placeholder="YYYY-MM-DD" class="form-control" name="txtDataPedido"/>
                        <span class="input-group-btn">
                            <input type="submit" value="Go!" class="btn btn-default" />
                        </span>
                    </div>
                </div>
            </div>
        </form>
        <div class="container">
            <c:if test="${pedidos.isEmpty() eq true}">
                <div class="alert alert-info" style="margin-top: 2%">Nenhum pedido foi encontrado nessa data!</div>
                <% request.setAttribute("pedidos", null); %>   <!--Removendo o atributo!-->
            </c:if>
            <c:if test="${msgPedido ne null}">
                <c:forEach var="erro" items="${requestScope.msgPedido}">
                    <div class="alert alert-danger" style="margin-top: 2%">${erro}</div>
                </c:forEach>
                <% request.setAttribute("msgPedido", null);%>   <!--Removendo o atributo!-->
            </c:if>
            <c:if test="${requestScope.SucessPedido ne null}">
                <div class="alert alert-success" style="margin-top: 2%">${requestScope.SucessPedido}</div>
            </c:if>
            <div id="divTablePedidos" style=" margin-top: 20px">
                <div class="panel panel-primary">
                    <div class="panel panel-heading">Pedidos da data</div>
                    <div class="panel panel-body">
                        <table class="table table-bordered text-center table-striped table-responsive">
                            <td style="background-color: #66afe9">N. Pedido</td>
                            <td style="background-color: #66afe9">Data/Hora</td>
                            <td style="background-color: #66afe9">Tipo Pagamento</td>
                            <td style="background-color: #66afe9">Posicao</td>
                            <td style="background-color: #66afe9">Troca</td>

                            <!-- Adicionando linhas da tabela !-->
                            <c:forEach var="pedido" items="${requestScope.pedidos}" >
                                <tr>
                                    <td>
                                        <form method="post" action="DetalhePedido">
                                            <input type="text" hidden="true" value="Consultar" name="operacao"/>
                                            <input type="text" hidden="true" name="txtId" value="${pedido.id}"/>
                                            <input type="text" hidden="true" name="txtFrete" value="${pedido.frete.valor}"/>
                                            <input type="text" hidden="true" name="txtTipoPagamento" value="${pedido.tipoPagamento}"/>
                                            <input type="text" hidden="true" name="txtStatus" value="${pedido.status}" />
                                            <input type="text" hidden="true" name="txtData" value="<f:formatDate pattern="dd/MM/yyyy" value="${pedido.dataPedido.getTime()}"></f:formatDate>"/>
                                            <input type="text" hidden="true" name="txtPosicao" value="${pedido.posicao}"/>
                                            <input type="text" hidden="true" name="txtIdEntrega" value="${pedido.entrega.id}" />
                                            <input type="submit" class="btn btn-link" value="${pedido.id}" title="Consultar Pedido"/>
                                        </form>
                                    </td>
                                    <td><f:formatDate pattern="dd/MM/yyyy" value="${pedido.dataPedido.getTime()}"></f:formatDate></td>
                                        <td>
                                        <c:choose>
                                            <c:when test="${pedido.tipoPagamento eq 'Boleto Bancario'}"> 
                                                <form method="post" action="SegundaViaBoleto">
                                                    <input type="text" name="txtIdEntrega" value="${pedido.entrega.id}" hidden="true" />
                                                    <input type="text" name="operacao" value="Consultar" hidden="true"/>
                                                    <input type="text" name="txtIdPedido" value="${pedido.id}" hidden="true"/>
                                                    <input type="submit" class="btn btn-link" value="${pedido.tipoPagamento}" title="Segunda via!" />
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                ${pedido.tipoPagamento}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${pedido.posicao eq 'Aguardando Pagamento'}">
                                                <form method="post" action="PagamentoDiretoBoleto">
                                                    <input type="text" name="txtIdEntrega" value="${pedido.entrega.id}" hidden="true" />
                                                    <input type="text" name="operacao" value="Atualizar" hidden="true"/>
                                                    <input type="text" name="txtIdPedido" value="${pedido.id}" hidden="true"/>
                                                    <input type="submit" class="btn btn-link" value="${pedido.posicao}"  title="Pagar Boleto!"/>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                ${pedido.posicao}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                     <td>
										<c:choose>
                                            <c:when test="${pedido.status eq 'Entrega Autorizada'}">
                                                <form method="post" action="TrocaProduto">
                                                    <input type="text" name="txtIdEntrega" value="${pedido.entrega.id}" hidden="true" />
                                                    <input type="text" name="operacao" value="Trocar" hidden="true"/>
                                                    <input type="text" name="txtIdPedido" value="${pedido.id}" hidden="true"/>
                                                    <input type="submit" class="btn btn-link" value="${pedido.status}"  title="Trocar Produto!"/>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                ${pedido.status}
                                            </c:otherwise>
                                        </c:choose>
                                     </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="panel panel-footer">
                        <a href="Produtos.jsp" class="btn btn-default">Voltar</a>
                    </div>
                </div>
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
        </div>
    </body>
    <!-- JavaScript Includes -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
