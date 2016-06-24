<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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

                    <h1>Cadastro de Cliente</h1>

                    <!-- breadcrumbs -->
                    <ol class="breadcrumb">
                        <li><a href="login.jsp">Home</a></li>
                        <li><a href="Produtos.jsp">Produtos</a></li>
                        <li class="active">Cadastro de Cliente</li>
                    </ol><!-- /breadcrumbs -->

                </div>
            </section>
            <!-- /PAGE HEADER -->
        <title>Salvar Cliente</title>
        <!-- BootStrap !-->
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
        <script type="text/javascript" src="js/jquery.maskedinput-1.1.4.pack.js"/></script>
    <script>
        function buscaCep()
        {
            var form = document.getElementById("form");

            form.setAttribute("action", "BuscaCep");

            form.submit();
        }
    </script>
</head>
<body style="background-color: whitesmoke">  
    <c:choose>
        <c:when test="${sessionScope.user ne null}">
            <form method="post" action="AtualizarCliente" id="form">
                <input type="text" hidden="true" value="Atualizar" name="operacao"/>
            </c:when>
            <c:otherwise>
                <form method="post" action="SalvarCliente" id="form">
                    <input type="text" hidden="true" value="Salvar" name="operacao"/>
                </c:otherwise>
            </c:choose>

            <div class="container">
                <div class="form-inline"> <!-- DIV PRINCIPAL INLINE -->
                    <div class="panel panel-success col-md-6"> <!-- FORMULARIO DE DADOS DO CLIENTE -->
                        <div class="panel panel-heading"><strong>Cadastre-se - Informe seus dados</strong></div>
                        <c:if test="${requestScope.MsgSucessCliente ne null}">
                            <div class="alert alert-success">${requestScope.MsgSucessCliente}</div>
                        </c:if>
                        <c:if test="${requestScope.MsgErrorCliente ne null}">
                            <c:forEach var="erro" items="${requestScope.MsgErrorCliente}">
                                <div class="alert alert-danger">${erro}</div>
                            </c:forEach>
                        </c:if>

                        <!-- Mensagem de erro para atualizacao de clientes -->
                        <c:if test="${requestScope.sucessAtualizaCliente ne null}">
                            <div class="alert alert-success">${requestScope.sucessAtualizaCliente}</div>
                        </c:if>
                        <c:if test="${requestScope.erroAtualizaCliente ne null}">
                            <c:forEach var="erro" items="${requestScope.erroAtualizaCliente}">
                                <div class="alert alert-danger">${erro}</div>
                            </c:forEach>
                        </c:if>
                        <div class="panel panel-body">
                            <c:if test="${sessionScope.user eq null}">
                                <h3>Login</h3>
                                <div class="input-group col-sm-7">
                                    <span class="input-group-addon">Email</span>
                                    <input type="email" name="txtEmail" class="form-control" placeholder="email@provedor.com" value="${requestScope.user.email}"/>
                                </div>

                                <div class="input-group col-sm-7" style="margin-top: 2%">
                                    <span class="input-group-addon">Senha</span>
                                    <input type="password" name="txtSenha" class="form-control" placeholder="******" value="${requestScope.user.senha}"/>
                                </div>
                            </c:if>
                            <!--<div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">Confirmar Senha</span>
                                <input type="password" name="txtConfirmarSenha" class="form-control"/>
                            </div> -->
                            <h3>Dados pessoais</h3>
                            <div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">Nome</span>
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="text" name="txtNome" class="form-control" placeholder="Nome Sobrenome" value="${sessionScope.user.nome}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="txtNome" class="form-control" placeholder="Nome Sobrenome" value="${requestScope.user.nome}"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">CPF</span>
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="text" name="txtCpf" class="form-control" placeholder="000.000.000-00" id="cpf" value="${sessionScope.user.cpf}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="txtCpf" class="form-control" placeholder="000.000.000-00" id="cpf" value="${requestScope.user.cpf}"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">Nascimento</span>
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="date" name="txtDataNasc" class="form-control" placeholder="yyyy-MM-dd" value="<f:formatDate pattern="yyyy-MM-dd" value="${sessionScope.user.dataNascimento.getTime()}"></f:formatDate>" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="date" name="txtDataNasc" class="form-control" placeholder="yyyy-MM-dd" value="<f:formatDate pattern="yyyy-MM-dd" value="${requestScope.user.dataNascimento.getTime()}"></f:formatDate>" />
                                    </c:otherwise>
                                </c:choose>
                                </div>

                                <div class="input-group col-sm-7" style="margin-top: 2%">
                                    <span class="input-group-addon">Sexo</span>
                                    <select class="form-control" name="selSexo">
                                        <option>
                                            M
                                        </option>
                                        <option>
                                            F
                                        </option>
                                    </select>
                                </div>

                                <div class="input-group col-sm-7" style="margin-top: 2%">
                                    <span class="input-group-addon">Telefone</span>
                                    <c:choose>
                                        <c:when test="${sessionScope.user ne null}">
                                            <input type="tel" name="txtTelefone" class="form-control" placeholder="(00) 0000-0000" value="${sessionScope.user.telefone}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="tel" name="txtTelefone" class="form-control" placeholder="(00) 0000-0000" value="${requestScope.user.telefone}"/>
                                        </c:otherwise>
                                    </c:choose>
                            </div>
                            <h4>Endereço Residêncial</h4>

                            <div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">CEP</span>
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="text" name="txtCep" class="form-control" placeholder="00000-000" value="${sessionScope.user.endereco.CEP}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="txtCep" class="form-control" placeholder="00000-000" value="${requestScope.endereco.cep}"/>
                                    </c:otherwise>
                                </c:choose>
                                <span class="input-group-btn">
                                    <input type="button" class="btn btn-success" value="Go" onclick="buscaCep()"/>
                                </span>
                                
                            </div>

                            <c:if test="${requestScope.msgEndereco ne null}">
                                <div class="alert alert-danger col-sm-7" style="margin-top: 2%">
                                    <strong>${requestScope.msgEndereco}</strong>
                                </div>
                            </c:if>


                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Logradouro</span>
                                        <input type="text" name="txtLogradouro" class="form-control" value="${sessionScope.user.endereco.logradouro}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Logradouro</span>
                                        <input type="text" name="txtLogradouro" class="form-control" value="${requestScope.endereco.logradouro}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>


                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Número</span>
                                        <input type="text" name="txtNumero" class="form-control" value="${sessionScope.user.endereco.numero}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Número</span>
                                        <input type="text" name="txtNumero" class="form-control" value="${requestScope.endereco.numero}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Bairro</span>
                                        <input type="text" name="txtBairro" class="form-control" value="${sessionScope.user.endereco.bairro}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Bairro</span>
                                        <input type="text" name="txtBairro" class="form-control" value="${requestScope.endereco.bairro}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Cidade</span>
                                        <input type="text" name="txtCidade" class="form-control" value="${sessionScope.user.endereco.cidade}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Cidade</span>
                                        <input type="text" name="txtCidade" class="form-control" value="${requestScope.endereco.cidade}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Estado</span>
                                        <input type="text" name="txtEstado" class="form-control" value="${sessionScope.user.endereco.estado}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Estado</span>
                                        <input type="text" name="txtEstado" class="form-control" value="${requestScope.endereco.estado}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="panel panel-footer"> 
                            <div class="form-inline">
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="submit" class="btn btn-success" value="Atualizar"/>
                                        <a href="Produtos.jsp" class="btn btn-primary">Voltar</a>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" class="btn btn-success" value="Cadastrar"/>
                                        <a href="Produtos.jsp" class="btn btn-primary">Voltar</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div> <!-- FORMULARIO DE DADOS DO CLIENTE -->
                </div> <!-- DIV PRINCIPAL INLINE -->
            </div>
        </form>
        <!-- JavaScript Includes -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/tests/vendor/jquery.min.js"></script>
        <script src="js/dropdown.js"></script>
</body>

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
        <!-- /wrapper -->


        <!-- PRELOADER -->
        <div id="preloader">
            <div class="inner">
                <span class="loader"></span>
            </div>
        </div><!-- /PRELOADER -->


        <!-- JAVASCRIPT FILES -->
        <script type="text/javascript">var plugin_path = 'assets/plugins/';</script>
        <script type="text/javascript" src="assets/plugins/jquery/jquery-2.1.4.min.js"></script>

        <script type="text/javascript" src="assets/js/scripts.js"></script>
        
        <!-- STYLESWITCHER - REMOVE -->
        <script async type="text/javascript" src="assets/plugins/styleswitcher/styleswitcher.js"></script>

        <!-- PAGE LEVEL SCRIPTS -->
        <script type="text/javascript">

            /**
                Checkbox on "I agree" modal Clicked!
            **/
            jQuery("#terms-agree").click(function(){
                jQuery('#termsModal').modal('toggle');

                // Check Terms and Conditions checkbox if not already checked!
                if(!jQuery("#checked-agree").checked) {
                    jQuery("input.checked-agree").prop('checked', true);
                }
                
            });
        </script>
    </body>
</html>
