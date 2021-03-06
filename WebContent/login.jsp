<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
	<head>
		<meta charset="utf-8" />
		<title>Meraki - E-Commerce</title>
		<meta name="keywords" content="HTML5,CSS3,Template" />
		<meta name="description" content="" />
		<meta name="Author" content="Vitor Sakassegawa" />

		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />
		<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->

		<!-- WEB FONTS : use %7C instead of | (pipe) -->
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400%7CRaleway:300,400,500,600,700%7CLato:300,400,400italic,600,700" rel="stylesheet" type="text/css" />

		<!-- CORE CSS -->
		<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="assets/css/layout.css" rel="stylesheet" type="text/css" />

		<!-- PAGE LEVEL SCRIPTS -->
		<link href="assets/css/header-1.css" rel="stylesheet" type="text/css" />
		<link href="assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />
	</head>

	<!--
		AVAILABLE BODY CLASSES:
		
		smoothscroll 			= create a browser smooth scroll
		enable-animation		= enable WOW animations

		bg-grey					= grey background
		grain-grey				= grey grain background
		grain-blue				= blue grain background
		grain-green				= green grain background
		grain-blue				= blue grain background
		grain-orange			= orange grain background
		grain-yellow			= yellow grain background
		
		boxed 					= boxed layout
		pattern1 ... patern11	= pattern background
		menu-vertical-hide		= hidden, open on click
		
		BACKGROUND IMAGE [together with .boxed class]
		data-background="assets/images/boxed_background/1.jpg"
	-->
	<body class="smoothscroll enable-animation">

		<!-- SLIDE TOP -->
		<div id="slidetop">

			<div class="container">
				
				<div class="row">

					<div class="col-md-4">
						<h6><i class="icon-heart"></i> WHY SMARTY?</h6>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas metus nulla, commodo a sodales sed, dignissim pretium nunc. Nam et lacus neque. Ut enim massa, sodales tempor convallis et, iaculis ac massa. </p>
					</div>

					<div class="col-md-4">
						<h6><i class="icon-attachment"></i> RECENTLY VISITED</h6>
						<ul class="list-unstyled">
							<li><a href="#"><i class="fa fa-angle-right"></i> Consectetur adipiscing elit amet</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i> This is a very long text, very very very very very very very very very very very very </a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i> Lorem ipsum dolor sit amet</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i> Dolor sit amet,consectetur adipiscing elit amet</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i> Consectetur adipiscing elit amet,consectetur adipiscing elit</a></li>
						</ul>
					</div>

					<div class="col-md-4">
						<h6><i class="icon-envelope"></i>CONTATO</h6>
						<ul class="list-unstyled">
							<li><b>Address:</b> R. Carlos Barattino, 908, Vila Nova Mogilar, <br /> Mogi das Cruzes - SP,  Brasil</li>
							<li><b>Phone:</b> +55 11 4699-2799</li>
							<li><b>Email:</b> <a href="mailto:suporte@meraki.com.br">support@meraki.com.br</a></li>
						</ul>
					</div>

				</div>

			</div>

			<a class="slidetop-toggle" href="#"><!-- toggle button --></a>

		</div>
		<!-- /SLIDE TOP -->


		<!-- wrapper -->
		<div id="wrapper">

			<!-- Top Bar -->
			<div id="topBar">
				<div class="container">

					<!-- right -->
					<ul class="top-links list-inline pull-right">
						<li class="text-welcome">Bem vindo a Meraki, <strong>Vitor Sakassegawa</strong></li>
						<li>
							<a class="dropdown-toggle no-text-underline" data-toggle="dropdown" href="#"><i class="fa fa-user hidden-xs"></i> MINHA CONTA</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="#"><i class="fa fa-history"></i> ORDER HISTORY</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="#"><i class="fa fa-bookmark"></i> MY WISHLIST</a></li>
								<li><a tabindex="-1" href="#"><i class="fa fa-edit"></i> MY REVIEWS</a></li>
								<li><a tabindex="-1" href="#"><i class="fa fa-cog"></i> MY SETTINGS</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="#"><i class="glyphicon glyphicon-off"></i> LOGOUT</a></li>
							</ul>
						</li>
						<li><a href="page-login-register.html">ENTRAR</a></li>
						<li><a href="page-login-register.html">REGISTRAR</a></li>
					</ul>

					<!-- left -->
					<ul class="top-links list-inline">
						<li><a href="page-faq-1.html">FAQ</a></li>
						<li>
							<a class="dropdown-toggle no-text-underline" data-toggle="dropdown" href="#"><img class="flag-lang" src="assets/images/flags/br.png" width="16" height="11" alt="lang" /> PORTUGUESE</a>
							<ul class="dropdown-langs dropdown-menu">
								<li><a tabindex="-1" href="#"><img class="flag-lang" src="assets/images/flags/br.png" width="16" height="11" alt="lang" /> PORTUGUESE</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="#"><img  class="flag-lang" src="assets/images/flags/us.png" width="16" height="11" alt="lang" /> ENGLISH</a></li>
							</ul>
						</li>
					</ul>

				</div>
			</div>
			<!-- /Top Bar -->



			<!-- 
				AVAILABLE HEADER CLASSES

				Default nav height: 96px
				.header-md 		= 70px nav height
				.header-sm 		= 60px nav height

				.noborder 		= remove bottom border (only with transparent use)
				.transparent	= transparent header
				.translucent	= translucent header
				.sticky			= sticky header
				.static			= static header
				.dark			= dark header
				.bottom			= header on bottom
				
				shadow-before-1 = shadow 1 header top
				shadow-after-1 	= shadow 1 header bottom
				shadow-before-2 = shadow 2 header top
				shadow-after-2 	= shadow 2 header bottom
				shadow-before-3 = shadow 3 header top
				shadow-after-3 	= shadow 3 header bottom

				.clearfix		= required for mobile menu, do not remove!

				Example Usage:  class="clearfix sticky header-sm transparent noborder"
			-->
			<div id="header" class="sticky clearfix">

				<!-- TOP NAV -->
				<header id="topNav">
					<div class="container">

						<!-- Mobile Menu Button -->
						<button class="btn btn-mobile" data-toggle="collapse" data-target=".nav-main-collapse">
							<i class="fa fa-bars"></i>
						</button>

						<!-- BUTTONS -->
						<ul class="pull-right nav nav-pills nav-second-main">

							<!-- SEARCH -->
							<li class="search">
								<a href="javascript:;">
									<i class="fa fa-search"></i>
								</a>
								<div class="search-box">
									<form action="page-search-result-1.html" method="get">
										<div class="input-group">
											<input type="text" name="src" placeholder="Search" class="form-control" />
											<span class="input-group-btn">
												<button class="btn btn-primary" type="submit">Search</button>
											</span>
										</div>
									</form>
								</div> 
							</li>
							<!-- /SEARCH -->

							<!-- QUICK SHOP CART -->
							<li class="quick-cart">
								<a href="#">
									<span class="badge badge-aqua btn-xs badge-corner">2</span>
									<i class="fa fa-shopping-cart"></i> 
								</a>
								<div class="quick-cart-box">
									<h4>Shop Cart</h4>

									<div class="quick-cart-wrapper">

										<a href="#"><!-- cart item -->
											<img src="assets/images/demo/people/300x300/4-min.jpg" width="45" height="45" alt="" />
											<h6><span>2x</span> RED BAG WITH HUGE POCKETS</h6>
											<small>$37.21</small>
										</a><!-- /cart item -->

										<a href="#"><!-- cart item -->
											<img src="assets/images/demo/people/300x300/5-min.jpg" width="45" height="45" alt="" />
											<h6><span>2x</span> THIS IS A VERY LONG TEXT AND WILL BE TRUNCATED</h6>
											<small>$17.18</small>
										</a><!-- /cart item -->

										<!-- cart no items example -->
										<!--
										<a class="text-center" href="#">
											<h6>0 ITEMS ON YOUR CART</h6>
										</a>
										-->

									</div>

									<!-- quick cart footer -->
									<div class="quick-cart-footer clearfix">
										<a href="shop-cart.html" class="btn btn-primary btn-xs pull-right">VIEW CART</a>
										<span class="pull-left"><strong>TOTAL:</strong> $54.39</span>
									</div>
									<!-- /quick cart footer -->

								</div>
							</li>
							<!-- /QUICK SHOP CART -->

						</ul>
						<!-- /BUTTONS -->

						<!-- Logo -->
						<a class="logo pull-left" href="index.html">
							<img src="assets/images/meraki-letter2.png" alt="" />
						</a>

						<!-- 
							Top Nav 
							
							AVAILABLE CLASSES:
							submenu-dark = dark sub menu
						-->
						<div class="navbar-collapse pull-left nav-main-collapse collapse">
							<nav class="nav-main">

								<!--
									NOTE
									
									For a regular link, remove "dropdown" class from LI tag and "dropdown-toggle" class from the href.
									Direct Link Example: 

									<li>
										<a href="#">HOME</a>
									</li>
								-->
								<ul id="topMain" class="nav nav-pills nav-main">
                                    <li>
										<a href="page-video.html">HOME</a>
									</li>
                                    
                            		<li>
										<a href="Produtos.jsp">PRODUTOS</a>
									</li>
                                    
                                    <li>
										<a href="#">CLIENTES</a>
									</li>
                            
                                    <li>
										<a href="#">FAQ</a>
									</li>
                                    
                                    <li>
										<a href="#">CONTATO</a>
									</li>
									
                                    <li>
										<a href="#">SOBRE N�S</a>
									</li>
                                    
                                </ul>
                            </nav>
						</div>
				</header>
				<!-- /Top Nav -->
			</div>

			<!-- 
				PAGE HEADER 
				
				CLASSES:
					.page-header-xs	= 20px margins
					.page-header-md	= 50px margins
					.page-header-lg	= 80px margins
					.page-header-xlg= 130px margins
					.dark			= dark page header

					.shadow-before-1 	= shadow 1 header top
					.shadow-after-1 	= shadow 1 header bottom
					.shadow-before-2 	= shadow 2 header top
					.shadow-after-2 	= shadow 2 header bottom
					.shadow-before-3 	= shadow 3 header top
					.shadow-after-3 	= shadow 3 header bottom
			-->
			<section class="page-header">
				<div class="container">

					<h1>LOGIN</h1>

					<!-- breadcrumbs -->
					<ol class="breadcrumb">
						<li><a href="login.jsp">Home</a></li>
						<li><a href="Produtos.jsp">Produtos</a></li>
						<li class="active">Login</li>
					</ol><!-- /breadcrumbs -->

				</div>
			</section>
			<!-- /PAGE HEADER -->




			<!-- -->
			<section>
				<div class="container">

					<div class="row">
						<div class="col-md-6 col-md-offset-3">

							<div class="toggle toggle-transparent toggle-accordion toggle-noicon">

								<div class="toggle active">
									<label class="size-20"><i class="fa fa-leaf"></i> &nbsp; J� sou cliente Meraki</label>
									<div class="toggle-content">

										<!-- ALERT -->
							            <c:if test="${msgLoginErro ne null}">
							                <div style="margin-top: 2%" class="alert alert-danger">${msgLoginErro}</div>
							            </c:if>							
										
										<form class="sky-form" method="post" action="ValidaUsuario" autocomplete="off">
											<div class="clearfix">

												<!-- Email -->
												<div class="form-group">
													<label>Email</label>
													<label class="input margin-bottom-10">
														<i class="ico-append fa fa-envelope"></i>
														<input required="" type="email" name="txtEmail">
														<b class="tooltip tooltip-bottom-right">Digite seu email</b>
													</label>
												</div>

												<!-- Password -->
												<div class="form-group">
													<label>Senha</label>
													<label class="input margin-bottom-10">
														<i class="ico-append fa fa-lock"></i>
														<input required="" type="password" name="txtSenha">
														<b class="tooltip tooltip-bottom-right">Digite sua senha</b>
													</label>
												</div>

											</div>

											<div class="row">

												<div class="col-md-6 col-sm-6 col-xs-6">
												</div>

												<div class="col-md-6 col-sm-6 col-xs-6 text-right">

													<button class="btn btn-primary"><i class="fa fa-check"></i> LOGIN</button>

												</div>

											</div>

										</form>


									</div>
								</div>

								<div class="toggle">
									<label class="size-20"><i class="glyphicon glyphicon-user"></i> &nbsp; Quero me cadastrar</label>
									<div class="toggle-content">

											<fieldset>

												<hr />

												<label class="checkbox nomargin"><input class="checked-agree" type="checkbox" name="checkbox"><i></i>Eu estou de acordo com os <a href="#" data-toggle="modal" data-target="#termsModal">Termos de Servi�o</a></label>

											</fieldset>

											<div class="row">
												<div class="col-md-12">
												  	<a class="btn" role="button" aria-expanded="false" href="page-register.jsp">Cadastre-se</a>
												</div>
											</div>

										</form>



									</div>
								</div>

							</div>

						</div>
					</div>

				</div>
			</section>
			<!-- / -->




			<!-- MODAL -->
			<div class="modal fade" id="termsModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModal">Termos &amp; Condi��es</h4>
						</div>

						<div class="modal-body modal-short">
							<h4><b>Introdu��o</b></h4>
							<p>O Grupo VS est� comprometido com a conscientiza��o de seus clientes em rela��o �s suas informa��es coletadas e utilizadas para proporcionar uma melhor experi�ncia de navega��o. Por isso, apresentamos nossa pol�tica de privacidade, que tem por objetivo esclarecer o uso dessas informa��es. Ao visitar a www.meraki.com.br, voc� aceita as pr�ticas descritas nesta pol�tica de privacidade.</p>

							<h4><strong>Licen�a para usar o site</strong></h4>
							<p>Salvo disposi��o contr�ria, MERAKI e/ou de seus licenciadores det�m os direitos de propriedade intelectual no site e material no site. Sujeito � licen�a abaixo, todos esses direitos de propriedade intelectual s�o reservados.</p>
							<p>Voc� pode visualizar, baixar apenas para fins de armazenamento em cache, e imprimir p�ginas a partir do site para o seu pr�prio uso pessoal, sujeito �s restri��es definidas abaixo e em outros lugares nestes termos e condi��es.</p>
							<p>Voc� n�o deve:</p>
                            <ul>
                                <Li>republicar material deste site (incluindo republica��o em outro site); </ li>
                                <Li>reproduzir, duplicar, copiar ou explorar o material neste site para fins comerciais;] </ li>
                                <Li>[editar ou modificar qualquer material no site; ou] </ li>
                                <Li>[redistribuir material deste site [exceto para os conte�dos especificamente e expressamente disponibilizado para redistribui��o].] </ Li>
                            </ul>

							<h4><strong>Uso</strong></h4>
                            <p>Voc� n�o deve usar o site em qualquer forma que cause ou possa causar, danos ou comprometimento da disponibilidade ou a acessibilidade; ou de qualquer forma que seja il�cita, ilegal, fraudulenta ou prejudicial, ou em conex�o com qualquer finalidade ou atividade il�cita, ilegal, fraudulenta ou prejudicial.</p>
                            <p>Voc� n�o deve usar este site para copiar, armazenar, hospedar, transmitir, enviar, usar, publicar ou distribuir qualquer material que consiste de (ou est� ligado a) qualquer spyware, v�rus de computador, cavalos de tr�ia, worms, keystroke logger, rootkit ou outro software malicioso.</p>
                            <p>Voc� n�o deve realizar quaisquer actividades de recolha de dados sistem�ticos ou automatizados (incluindo, sem limita��o scraping, minera��o de dados, extra��o de dados e colheita de dados) ou em rela��o a MERAKI sem expresso consentimento por escrito.</p>
                            <p>Voc� n�o deve usar este site para transmitir ou enviar comunica��es comerciais n�o solicitadas.</p>
                            <p>Voc� n�o deve usar este site para quaisquer fins relacionados ao marketing, sem expresso consentimento por escrito.</p>
                    
							<h4><strong>Acesso restrito</strong></h4>
							<p>O acesso a determinadas �reas deste site � restrito. A MERAKI reserva o direito de restringir o acesso aS �reas deste site, ou mesmo todo este site.</p>
							<p>Se a MERAKI fornece-lhe um ID de usu�rio e senha para que voc� possa acessar �reas restritas ou outro conte�do e servi�os, voc� deve garantir que o ID de usu�rio e senha s�o mantidos em sigilo.</p>
							<p>A MERAKI pode desativar o seu ID de usu�rio e senha em exclusivo crit�rio, sem aviso ou explica��o.</p>
                            
							<h4><strong>O conte�do do usu�rio</strong></h4>
							<p>Nestes termos e condi��es, "o conte�do do usu�rio" significa materiais (incluindo, sem texto limita��o, imagens, material de �udio, material v�deo e material �udio-visual) que voc� enviar a este site, para qualquer fim.</p>
							<p>Voc� concede � MERAKI uma licen�a mundial, irrevog�vel, n�o-exclusiva, e livre de royalties para utilizar, reproduzir, adaptar, publicar, traduzir e distribuir o seu conte�do do usu�rio em qualquer m�dia existentes ou futuros. Voc� tamb�m concede a MERAKI o direito de sub-licenciar esses direitos, bem como o direito de intentar uma a��o por viola��o desses direitos.</p>
							<p>Seu conte�do do usu�rio n�o deve ser ilegal ou il�cito, n�o devem violar os direitos legais de terceiros, e n�o deve ser capaz de dar origem a uma a��o judicial contra voc�, A MERAKI ou de um terceiro (em cada caso, sob qualquer lei aplic�vel).</p>
							<p>Voc� n�o deve apresentar qualquer conte�do do usu�rio para o site que � ou j� sido objecto de qualquer processo judicial real ou em amea�a ou outra queixa similar.</p>
							<p>A MERAKI reserva-se o direito de editar ou remover qualquer material enviado a este site, armazenados em servidores, hospedado ou publicado sobre este site.</p>
                            
							<h4><strong>Nenhuma garantia</strong></h4>
							<p>Este site � fornecido "como est�" sem quaisquer representa��es ou garantias, expressas ou impl�citas. A MERAKI n�o faz representa��es ou garantias em rela��o a este site ou a informa��es e materiais fornecidos neste site.</p>
							<p>Sem preju�zo da generalidade do par�grafo anterior, A MERAKI n�o garante que:</p>
							<ul>
								<li>este site ser� constantemente dispon�veis, ou dispon�veis em todos; ou</li>
								<li>a informa��o neste site � completa, verdadeira, precisa ou n�o enganosa.</li>
							</ul>
							<p>Nada neste site constitui ou se destina a constituir, assessoria de qualquer tipo. Se voc� precisar de aconselhamento em rela��o a qualquer [jur�dica, financeira ou m�dica] voc� deve consultar um profissional.</p>
                            
							<h4><strong>Limita��es de responsabilidade</strong></h4>							
                            <p> A MERAKI n�o ser� responsabilizada por voc� (seja sob a lei de contato, a lei da responsabilidade civil ou de outra forma) em rela��o ao conte�do de, ou a utiliza��o de, ou em conex�o com, este site: </p>
                            <ul>
                                <li>na medida em que o site � fornecido gratuitamente de encargos, por qualquer perda direta;</li>
                                <li>por qualquer perda indireta, especial ou consequente; ou</li>
                                <li>por quaisquer perdas de neg�cios, perda de receita, receitas, lucros ou poupan�as antecipadas, perda de contratos ou rela��es de neg�cios, perda de reputa��o ou de boa vontade, ou a perda ou corrup��o de dados ou informa��es.</li>
                            </ul>
                            <p>Estas limita��es de responsabilidade aplicam-se mesmo que a MERAKI tenha sido expressamente avisados da perda potencial. </p>
                            
							<h4><strong>Razoabilidade</strong></h4>
							<p>Ao utilizar este site, voc� concorda que as exclus�es e limita��es de responsabilidade estabelecidas no presente site s�o razo�veis.</p>
							<p>Se voc� n�o acho que eles s�o razo�veis, voc� n�o deve usar este site.</p>

							<h4><strong>Outras partes</strong></h4>
							<p>Voc� aceita que, como uma entidade de responsabilidade limitada, A MERAKI tem interesse em limitar a responsabilidade pessoal de seus diretores e empregados. Voc� concorda que voc� n�o vai trazer qualquer reclama��o pessoalmente contra os administradores ou empregados da MERAKI em rela��o a quaisquer perdas que sofrem em conex�o com o site.</p>
							<p>Sem preju�zo do par�grafo anterior, voc� concorda que as limita��es de garantias e responsabilidades estabelecidas no presente site sobre aviso legal ir� proteger os diretores, funcion�rios, agentes, subsidi�rias, sucessores, cession�rios e sub-empreiteiros, bem como MERAKI.</p>
                            
							<h4><strong>Indeniza��o</strong></h4>
							<p>Voc� decide indenizar a MERAKI e se comprometer a manter a MERAKI indenizados por quaisquer perdas, danos, custos, responsabilidades e despesas (incluindo, sem limita��o, despesas legais e de quaisquer montantes pagos por a MERAKI para um terceiro na liquida��o de um reivindica��o ou disputa no conselho dos consultores jur�dicos), ocorrido ou sofrido pela MERAKI decorrente de qualquer viola��o por voc� de qualquer disposi��o destes termos e condi��es ou decorrentes de qualquer alega��o de que voc� tenha violado qualquer disposi��o dos estes termos e condi��es.</p>

							<h4><strong>Viola��es desses termos e condi��es</strong></h4>
							<p>Sem preju�zo da MERAKI e outros direitos sob estes termos e condi��es, se voc� violar estes termos e condi��es de qualquer forma, a MERAKI pode tomar as medidas consideradas apropriada para lidar com a viola��o, incluindo a suspens�o do seu o acesso ao site, proibindo-o de acessar o site, bloqueando computadores usando o seu endere�o IP de acessar o site, entrar em contato com o seu provedor de servi�os de internet para solicitar que eles bloqueiam o seu acesso ao site e/ou entrar em processo judicial.</p>

							<h4><strong>Varia��o</strong></h4>
							<p>A MERAKI pode rever estes termos e condi��es de tempos em tempos. Termos e condi��es revistas se aplicar�o ao uso deste site a partir da data da publica��o dos termos e condi��es revistos. Por favor, verifique esta p�gina regularmente para garantir que est�o familiarizados com a vers�o atual.</p>
                            
							<h4><strong>Atribui��es</strong></h4>
							<p>A MERAKI pode transferir, subcontratar ou de outra forma lidar com os direitos e/ou obriga��es sob estes termos e condi��es, sem notific�-lo ou obter o seu consentimento.</p>
							<p>Voc� n�o pode transferir, subcontratar ou negociar com os seus direitos e/ou obriga��es sob estes termos e condi��es.</p>

							<h4><strong>Autonomia</strong></h4>
							<p>Se uma disposi��o destes termos e condi��es � determinada por qualquer tribunal ou outra autoridade competente para ser ilegal e/ou ineficaz, as restantes disposi��es continuar�o em vigor. Se qualquer disposi��o ilegal e/ou n�o aplic�vel seria legal ou execut�rio se parte dela for exclu�do, que parte ser� considerada a ser exclu�do, e o resto da disposi��o continuar� em vigor.</p>

							<h4><strong>Acordo total</strong></h4>
							<p> Estes termos e condi��es, juntamente com documentos, constituem a totalidade do acordo entre voc� e a MERAKI em rela��o ao seu uso deste site, e substituem todos os acordos anteriores relativamente a seu uso deste site.</p>

							<h4><strong>Lei e jurisdi��o</strong></h4>
							<p>Estes termos e condi��es ser�o regidos e interpretados de acordo com a LEI, e quaisquer disputas relacionadas a estes termos e condi��es estar�o sujeitos � n�o exclusiva jurisdi��o dos tribunais.</p>

							<h4><strong>MERAKI'S detalhes</strong></h4>
							<p>O nome completo da MERAKI � VS.COM Internet S.A.</p>
                        
							<p class="margin-top30">
								<strong>Ao aceitar este documento, voc� concorda com os
                                <a href="#">termos e condi��es</a> estabelecidas em 
								<a href="#">meraki.com.br</a>.
							</p>

						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
							<button type="button" class="btn btn-primary" id="terms-agree"><i class="fa fa-check"></i>Aceitar</button>
							
							<a href="page-print-terms.html" target="_blank" rel="nofollow" class="btn btn-danger pull-left"><i class="fa fa-print"></i><span class="hidden-xs"> Imprimir</span></a>
						</div>

					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div>
			<!-- /MODAL -->





			<!-- FOOTER -->
			<footer id="footer">
				<div class="container">

					<div class="row">
						
						<div class="col-md-3">
							<!-- Footer Logo -->
							<img class="footer-logo" src="assets/images/logoo.png" alt="" />

							<!-- Small Description -->
							<p>Seu esporte, nossa paix�o</p>

							<!-- Contact Address -->
							<address>
								<ul class="list-unstyled">
									<li class="footer-sprite address">
										R. Carlos Barattino, N� 908<br>
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
						&copy; All Rights Reserved, Company LTD
					</div>
				</div>
			</footer>
			<!-- /FOOTER -->

		</div>
		<!-- /wrapper -->


		<!-- SCROLL TO TOP -->
		<a href="#" id="toTop"></a>


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