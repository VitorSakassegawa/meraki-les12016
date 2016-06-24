<%@ page contentType="text/html; charset=iso-8859-1" errorPage="" %>
<%@ taglib uri="/WEB-INF/cewolf-1.1.tld" prefix="cewolf"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
<title>Relatórios de Vendas por Idade (L. Bruto)</title>
</head>
<body>
<jsp:useBean id="graficoDS" class="meraki.com.br.domain.RelatorioClientesIdade"/>
<jsp:setProperty name="graficoDS" property="*"/>
		
<center><h3>GRÁFICOS DE LINHAS</h3></center>
		
	<!-- GRÁFICO LINHAS E COLUNA -->
		<cewolf:chart id="grafico1" title="Gastos por idade - Gráfico de Coluna H" type="stackedhorizontalbar" 
				xaxislabel="Idade" yaxislabel="Valor Gasto">
		    <cewolf:gradientpaint>
		        <cewolf:point x="0" y="0" color="#FBFBFB" />
		        <cewolf:point x="350" y="0" color="#F3F3F3" />
		    </cewolf:gradientpaint>
		    <cewolf:data>
		        <cewolf:producer id="graficoDS" />
			</cewolf:data>
		</cewolf:chart>
		<cewolf:img chartid="grafico1" renderer="/cewolf" width="710" height="380"/>
		
		<br/><br/>
		
		<!-- GRÁFICO COLUNA HORIZONTAL 3D -->
		<cewolf:chart id="grafico2" title="Gastos por idade - Gráfico de Coluna H" type="horizontalbar3d" 
				xaxislabel="Idade" yaxislabel="Valor Gasto">
		    <cewolf:gradientpaint>
		        <cewolf:point x="0" y="0" color="#FBFBFB" />
		        <cewolf:point x="350" y="0" color="#F3F3F3" />
		    </cewolf:gradientpaint>
		    <cewolf:data>
		        <cewolf:producer id="graficoDS" />
			</cewolf:data>
		</cewolf:chart>
		<cewolf:img chartid="grafico2" renderer="/cewolf" width="710" height="380"/>
		
		<br/><br/>
		
		<!-- GRÁFICO COLUNA 3D -->
		<cewolf:chart id="grafico3" title="Gastos por idade - Gráfico de Coluna V" type="verticalbar3d" 
				xaxislabel="Idade" yaxislabel="Valor Gasto">
		    <cewolf:gradientpaint>
		        <cewolf:point x="0" y="0" color="#FBFBFB" />
		        <cewolf:point x="350" y="0" color="#F3F3F3" />
		    </cewolf:gradientpaint>
		    <cewolf:data>
		        <cewolf:producer id="graficoDS" />
			</cewolf:data>
		</cewolf:chart>
		<cewolf:img chartid="grafico3" renderer="/cewolf" width="710" height="380"/>
		
		<br/><br/>
		
		<!-- GRÁFICO DE AREA -->
		<cewolf:chart id="grafico4" title="Gastos por idade - Area" type="area" 
				xaxislabel="Idade" yaxislabel="Valor Gasto">
		    <cewolf:gradientpaint>
		        <cewolf:point x="0" y="0" color="#FBFBFB" />
		        <cewolf:point x="350" y="0" color="#F3F3F3" />
		    </cewolf:gradientpaint>
		    <cewolf:data>
		        <cewolf:producer id="graficoDS" />
			</cewolf:data>
		</cewolf:chart>
		<cewolf:img chartid="grafico4" renderer="/cewolf" width="710" height="380"/>
		
		<br/><br/>
</body>
</html>
