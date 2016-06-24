<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	
/*   	google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawVisualization2); */

    
    function drawVisualization2() {
    	<%
		String saidaGrafico2 = (String) request.getSession().getAttribute("saidaGrafico2");
		%>
		var data2 = new google.visualization.DataTable();
		<%=saidaGrafico2%>        
        
		var options2 = {
		    title : 'Grafico',
		    vAxis: {title: 'Quantidade'},
		    hAxis: {title: 'Genero'},
		    seriesType: 'line',
		    series: {0: {type: 'bars'}},
		    pointSize: 5,
		    colors: ['gray', 'red', 'blue'],
		    width:900,
		    height:500
		};
		
		var chart2 = new google.visualization.ComboChart(document.getElementById('chart_div2'));
		chart2.draw(data2, options2);
		}

    $(document).ready(function(){ 
        setTimeout(function(){ 
/*             google.load("visualization", "1",{"callback" : drawVisualization2});  
 */
 			google.load('visualization', '1.0', {'packages':['corechart']});
 		    google.charts.setOnLoadCallback(drawVisualization2);
       }, 100); 
    }); 
     
    </script>
</body>
</html>