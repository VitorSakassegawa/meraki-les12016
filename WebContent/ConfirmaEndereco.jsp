<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
            <head>
              <head>
                                                                       <!-- HEADER -->
                                                                       <!-- HEADER -->            
                                                                       <!-- HEADER -->
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

                    <h1>Endereço de entrega</h1>

                    <!-- breadcrumbs -->
                    <ol class="breadcrumb">
                        <li><a href="login.jsp">Home</a></li>
                        <li><a href="Produtos.jsp">Produtos</a></li>
                        <li class="active">Endereço de entrega</li>
                    </ol><!-- /breadcrumbs -->

                </div>
                
                                                                       <!-- HEADER -->
                                                                       <!-- HEADER -->            
                                                                       <!-- HEADER -->
                                                                       
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Endereco</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
        <script src="js/limpar.js"></script>

        <!-- ============ Script para setar URL do Form =============== -->
        <script>
            function changeAction(url)
            {
                var form = document.getElementById("formEndereco");

                if (url === "SalvarEnderecoEntrega")
                {
                    form.setAttribute("action", "SalvarEnderecoEntrega");
                    form.submit();
                }
                else if(url === "SelecionarEndereco")
                {
                    form.setAttribute("action","SelecionarEndereco");
                    form.submit();
                }
                else
                {
                    form.setAttribute("action", "CarregaCep");
                    form.submit();
                }
            }
        </script>
    </head>

    <body style="background-color: threedshadow">
        <form method="post" id="formEndereco">
            <input type="text" name="operacao" value="Salvar" hidden="true" />
            <div class="container">
                <div class="jumbotron" style="margin-top: 5%">
                    <h2>
                        <strong>
                            Olá, selecione um endereco para entrega: 
                            <div class="form form-inline">
                                <div>
                                    <select name="selEndereco" class="form-control col-sm-5">
                                        <c:forEach var="end" items="${sessionScope.user.enderecos}">
                                            <option>
                                                ${sessionScope.user.enderecos.indexOf(end)+1}
                                                -
                                                ${end.toString()}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <input type="button" class="btn btn-success" value="Selecionar" style="margin-left: 2%" onclick="changeAction('SelecionarEndereco')"/>
                                </div>
                            </div>
                        </strong>
                    </h2>
                    <!-- ================ inicio do formulario de Endereco =========== -->
                    <div class="form-inline">
                        <strong>Ou procure um novo Endereço aqui <span class="glyphicon glyphicon-arrow-right"></span></strong>
                        <div class="input-group">
                            <span class="input-group-addon">CEP</span>
                            <input type="text" placeholder="Ex. 11111-111" name="txtCep" class="form-control" value="${requestScope.endereco.cep}"/>
                        </div>

                        <button type="submit" class="btn btn-success" onclick="changeAction('CarregaCep')"> 
                            <span class="glyphicon glyphicon-refresh"></span>
                        </button>

                        <c:if test="${requestScope.msgEndereco ne null}">
                            <c:forEach var="erro" items="${requestScope.msgEndereco}">
                                <div class="alert alert-danger" style="margin-top: 1%">
                                    ${erro}
                                </div>
                            </c:forEach>
                        </c:if>

                        <c:if test="${requestScope.msgEnderecoSucess ne null}">
                            <div class="alert alert-success" style="margin-top: 1%">
                                <strong>
                                    ${requestScope.msgEnderecoSucess}
                                </strong>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-horizontal">
                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Logradouro</span>
                            <input type="text" value="${requestScope.endereco.logradouro}" name="txtLogradouro" class="form-control"/>
                        </div>

                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Numero</span>
                            <input type="text" value="${requestScope.endereco.numero}" name="txtNumero" class="form-control" placeholder="111 ou S/N"/>
                        </div>

                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Bairro</span>
                            <input type="text" value="${requestScope.endereco.bairro}" name="txtBairro" class="form-control"/>
                        </div>

                        <!--<div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">CEP</span>
                            <input type="text" value="${sessionScope.user.enderecoEntrega.cep}" name="txtCep" class="form-control" placeholder="Ex. 11111-111"/>
                        </div> -->

                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Cidade</span>
                            <input type="text" value="${requestScope.endereco.cidade}" name="txtCidade" class="form-control"/>
                        </div>

                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Estado</span>
                            <input type="text" value="${requestScope.endereco.estado}" name="txtEstado" class="form-control"/>
                        </div>

                        <div class="form-inline input-group" style="margin-top: 2%">
                            <a class="btn btn-success" onclick="changeAction('SalvarEnderecoEntrega')">
                                Salvar e continuar <strong>Compra</strong>
                            </a>

                            <!-- <input type="submit" class="btn btn-primary" value="Editar Endereço" style="margin-left: 2px" onclick="changeAction('AtualizarEndereco')"/> -->

                            <a href="Produtos.jsp" class="btn btn-danger" style="margin-left: 2px">
                                Cancelar Compra
                            </a>
                        </div>
                    </div>
                </div>
            </div>
                                                                       <!-- FOOTER -->
                                                                       <!-- FOOTER -->            
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
                                                                       <!-- FOOTER -->
                                                                       <!-- FOOTER -->            
                                                                       <!-- FOOTER -->
        </form>
    </body>

    <!-- JavaScript dos componentes BootStrap !-->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
