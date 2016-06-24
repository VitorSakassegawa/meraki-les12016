<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                    <h1>Método de Pagamento</h1>

                    <!-- breadcrumbs -->
                    <ol class="breadcrumb">
                        <li><a href="login.jsp">Home</a></li>
                        <li><a href="Produtos.jsp">Produtos</a></li>
                        <li class="active">Método de Pagamento</li>
                    </ol><!-- /breadcrumbs -->

                </div>    
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Metodo de Pagamento</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
        <script src="js/limpar.js"></script>
    </head>
    <body style="background-color: threedshadow">
        <div class="container">
            <div class="panel panel-info" style="margin-top: 1%">
                <div class="panel panel-heading text-center">
                    <div class="form-horizontal">
                        <label>
                            <h4>
                                <span class="glyphicon glyphicon-barcode"></span>
                                <strong>Boleto Bancario</strong>
                            </h4>
                        </label>
                    </div>
                </div>
                <div class="panel panel-body">
                    <h2><p class="text-primary text-center"><strong>Ganhe até 15% de desconto!</strong></p></h2>
                    <p>
                        Este é o método mais prático para quem deseja pagar à vista. Você poderá efetuar o pagamento do boleto em qualquer 
                        Banco ou Casa Lotérica em qualquer lugar do Brasil, sem necessidade de confirmação do pagamento. Além disso, é a 
                        forma de pagamento que recebe o maior desconto para cliente final sob o valor total da compra.
                    </p>
                    <h4>
                        Total da sua compra:  
                        <strong class="text-primary">
                            R$
                            <f:formatNumber pattern="###,###.##">
                                ${sessionScope.carrinho.ValorTotal()}
                            </f:formatNumber>
                        </strong>
                        . Por esse método, você vai pagar 
                        <strong class="text-primary">
                            R$
                            <f:formatNumber pattern="###,###.##">
                                ${sessionScope.carrinho.valorTotalDesconto(0.15)}
                            </f:formatNumber>
                        </strong>
                    </h4>
                    <form method="post" action="PagamentoBoleto">
                        <input type="text" value="Salvar" name="operacao" hidden="true"/>
                        <button type="submit" class="btn btn-success btn-lg btn-info" style="float: right">
                            <span class="glyphicon glyphicon-barcode"></span>
                            Pagar <strong>Com</strong> Boleto
                        </button>
                    </form>
                </div>
            </div>

            <!-- ===================== Formulário do pagamento do cartao de credito  ========================= -->
            <div class="panel panel-success" style="margin-top: 1%">
                <div class="panel panel-heading text-center">
                    <div class="form-horizontal">
                        <label>
                            <h4>
                                <span class="glyphicon glyphicon-credit-card"></span>
                                <strong>Cartão de Crédito</strong>
                            </h4>
                        </label>
                    </div>
                </div>
                <div class="panel panel-body">
                    <div style="margin-bottom: 2%">
                        <h3 class="text-center text-primary"><strong>À vista com 10% de desconto, 2x ou 3x com até 5% de desconto ou tudo em 12x sem juros</strong></h3>
                    </div>
                    <form method="post" action="PagamentoCartao">
                        <input type="text" value="Salvar" name="operacao" hidden="true"/>
                        <div class="form-horizontal" style="margin-left: 1%;margin-bottom: 1%">
                            <div class="form-inline" style="margin-bottom: 1%">
                                <p class="col-sm-3">Forma de Pagamento*: </p>
                                <select class="form-control" name="txtFormaPagamento">
                                    <option>1x desconto de 10% - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.valorTotalDesconto(0.1)}</f:formatNumber> </option>
                                    <option>2x desconto 5% - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.valorTotalDesconto(0.05) / 2}</f:formatNumber> </option>
                                    <option>3x desconto 5% - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.valorTotalDesconto(0.05) / 3}</f:formatNumber></option>
                                    <option>4x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 4}</f:formatNumber> </option>
                                    <option>5x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 5}</f:formatNumber> </option>
                                    <option>6x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 6}</f:formatNumber> </option>
                                    <option>7x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 7}</f:formatNumber> </option>
                                    <option>8x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 8}</f:formatNumber> </option>
                                    <option>9x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 9}</f:formatNumber> </option>
                                    <option>10x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 10}</f:formatNumber> </option>
                                    <option>11x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 11}</f:formatNumber> </option>
                                    <option>12x sem juros - R$ <f:formatNumber pattern="###,###.##">${sessionScope.carrinho.ValorTotal() / 12}</f:formatNumber> </option>
                                    </select>
                                </div>
                                <div class="form-inline" style="margin-bottom: 1%">
                                    <p class="col-sm-3">Nome (escrito no cartão):* </p>
                                    <input type="text" class="form-control" name="txtNomeCartao" required="required"/>
                                </div>
                                <div class="form-inline" style="margin-bottom: 1%">
                                    <p class="col-sm-3">Número do Cartão:*</p>
                                    <input type="text" name="txtNumeroCartao" class="form-control" required="required"/>
                                </div>
                                <div class="form-inline" style="margin-bottom: 1%">
                                    <p class="col-sm-3">Validade (mm/aa):*</p>
                                    <select name="txtMes" class="form-control">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                        <option>11</option>
                                        <option>12</option>
                                    </select>

                                    <select name="txtAno" class="form-control">
                                        <option>15</option>
                                        <option>16</option>
                                        <option>17</option>
                                        <option>18</option>
                                        <option>19</option>
                                        <option>20</option>
                                        <option>21</option>
                                        <option>22</option>
                                        <option>23</option>
                                        <option>24</option>
                                    </select>
                                </div>
                                <div class="form-inline" style="margin-bottom: 1%">
                                    <p class="col-sm-3">Código de Segurança:*</p>
                                    <input type="text" name="txtCodSeguranca" class="form-control" placeholder="ex. 111" required="required" pattern="[0-9]{3}" title="Codigo de Segurança deve conter 3 digitos"/>
                                </div>
                                <div class="form-inline" style="margin-bottom: 1%">
                                    <p class="col-sm-3">CPF do proprietário:</p>
                                    <input type="text" name="txtCPF" placeholder="111.111.111-11" class="form-control" required="required"/>
                                </div>
                                <div class="form-inline" style="margin-bottom: 1%">
                                    <p class="col-sm-3">Data de Nascimento:</p>
                                    <input type="date" class="form-control" name="txtDataNasc" required="required"/> 
                                </div>
                                <div class="form-inline" style="margin-bottom: 1%">
                                    <button class="col-sm-3 btn btn-success btn-lg" type="submit" style="float: right">
                                        <span class="glyphicon glyphicon-credit-card"></span>
                                        Pagar <strong>Com</strong> Cartão
                                    </button>
                                </div>
                            </div>
                        </form>
                    <c:if test="${requestScope.msgErroCartao ne null}">
                        <div class="alert alert-danger" style="margin-top: 2%">${requestScope.msgErroCartao}</div>
                    </c:if>
                </div>
                <div class="panel panel-footer">
                    <div class="form-inline">
                        <div class="media media-left">
                            <img class="media-object" src="images/visa.ico">
                        </div>
                        <div class="media media-left">
                            <img class="media-object" src="images/master.png">
                        </div>
                        <div class="media media-left">
                            <img class="media-object" src="images/barcode.png">
                        </div>
                    </div>
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
    </body>
    <!-- JavaScript dos componentes BootStrap !-->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
