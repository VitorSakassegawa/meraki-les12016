<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<!DOCTYPE html>
<html>
    <head>
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
                            <img src="assets/images/meraki-letter2.png" alt="" />
                        </a>
            </div>

            <!-- 
                PAGE HEADER 
            -->
            <section class="page-header">
                <div class="container">

                    <h1>Informações do Produto</h1>

                    <!-- breadcrumbs -->
                    <ol class="breadcrumb">
                        <li><a href="login.jsp">Home</a></li>
                        <li><a href="Produtos.jsp">Produtos</a></li>
                        <li class="active">Informações do Produto</li>
                    </ol><!-- /breadcrumbs -->

                </div>
            </section>
            <!-- /PAGE HEADER -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Produto!</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="Carrinho">
            <input type="text" name="txtTitulo" hidden="true" value="${requestScope.produto.titulo}"/>
            <input type="text" name="txtValor" hidden="true" value="${requestScope.produto.valor}"/>
            <input type="text" name="txtCompraId" hidden="true" value="${requestScope.produto.id}"/>

            <div class="container form-group">
                <!--<div class="row" style="background-color: red; width: 600px"> !-->
                <div class="col-xs-6 col-md-6 form-horizontal" style="margin-top: 10px;">
                    <div class="thumbnail" style="background-color: silver">
                        <img src="${requestScope.produto.image}">
                        <div class="caption text-center">
                            <h2> <span class="label label-default">${requestScope.produto.titulo}</span> </h2>
                        </div>
                        <p><b>Descrição:</b></p>
                        <p>${requestScope.produto.descricao}</p>
                    </div>
                </div>
                <div class="form-group form-inline" style="margin-left: 10px">
                    <div>
                        <h3><span class="label label-primary col-lg-2" style="margin-right: 10px"> Genero</span> ${requestScope.produto.genero}</h3>
                    </div>
                    <div>
                        <h3><span class="label label-primary col-lg-2" style="margin-right: 10px"> Numeracao 
                            </span> ${requestScope.produto.numeracao}</h3>
                    </div>
                    <div>
                        <h3><span class="label label-primary col-lg-2" style="margin-right: 10px"> Ano Lançamento
                            </span> ${requestScope.produto.ano}</h3>
                    </div>
                    <div>
                        <h3>
                            <span class="label label-primary col-lg-2" style="margin-right: 10px">
                                Valor
                            </span> 
                            R$
                            <f:formatNumber value="${requestScope.produto.valor}" pattern="###.##"/>
                        </h3>
                    </div>
                    <div>
                        <h3>
                            <c:choose>
                                <c:when test="${requestScope.produto.origem eq 'S'}">
                                    <span class="label label-primary col-lg-2" style="margin-right: 10px"> Importado    
                                    </span>Sim
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-primary col-lg-2" style="margin-right: 10px"> Importado    
                                    </span>Não
                                </c:otherwise>
                            </c:choose>
                        </h3>
                    </div>
                    <div>
                        <h3>
                            <c:choose>
                                <c:when test="${requestScope.produto.material eq 'S'}"> 
                                    <span class="label label-primary col-lg-2" style="margin-right: 10px"> Sintetico
                                    </span> Sim
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-primary col-lg-2" style="margin-right: 10px"> Sintetico
                                    </span>Não
                                </c:otherwise>
                            </c:choose>
                        </h3>
                    </div>
                    <!-- <div>
                        <h3><span class="label label-primary col-lg-2" style="margin-right: 10px"> Quantidade</span></h3>
                        <input type="text" class="form-control form-group" name="txtQuantidade"/>
                    </div> !-->
                    <br/><br/>
                    <input class="btn btn-success" value="Comprar" type="submit"/>
                    <a href="Produtos.jsp">
                        <button class="btn btn-default" type="button">
                            Voltar
                        </button>
                    </a>
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
    </form>
</body>
<!-- JavaScript dos componentes BootStrap !-->
<script src="js/bootstrap.min.js"></script>
<script src="js/tests/vendor/jquery.min.js"></script>
<script src="js/dropdown.js"></script>
</html>
