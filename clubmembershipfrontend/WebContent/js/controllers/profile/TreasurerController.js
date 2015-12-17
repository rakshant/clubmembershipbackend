var app = angular.module("profile");


app.controller('MyController', function($scope, $http, $state, url) {
	
	$http({
		method : 'GET',
		url : url + 'users' + "/" + 'treasurer',
	}).success(function(data) {
	$scope.myDataSource = {
			   chart: {
			       caption: "People's Club Funds",
			       subCaption: "Total funds in the club by revenue",
			       numberPrefix: "$",
			       theme: "fint",
			       showHoverEffect :"1",
			       usePlotGradientColor:"1",
			       plotGradientColor:"#ffffff",
			       xAxisName: "Facilites",
			       yAxisName: "Amount (In USD)",
			      /* yAxisMaxValue: "5",
			       yAxisMinValue: "0"*/
			      
			   },
			   data:[{
			       label: data.indoor[0].type,
			       value: data.indoor[0].price
			   },
			   {
			       label:data.indoor[1].type,
			       value: data.indoor[1].price
			   },
			   {
				   label:data.indoor[2].type,
			       value: data.indoor[2].price
			   },
			   {
				   label:data.indoor[3].type,
			       value: data.indoor[3].price
			   }, 
			   {
				   label:data.outdoor[0].type,
			       value: data.outdoor[0].price
			   }, 
			   {
				   label:data.outdoor[1].type,
			       value: data.outdoor[1].price
			   },
			   {
				   label:data.outdoor[2].type,
			       value: data.outdoor[2].price
			   },
			   {
				   label:data.outdoor[3].type,
			       value: data.outdoor[3].price
			   },			   
			   {
				   label:data.leisure[0].type,
			       value: data.leisure[0].price
			   },			   
			   {
				   label:data.leisure[1].type,
			       value: data.leisure[1].price
			   },
			   {
				   label:data.leisure[2].type,
			       value: data.leisure[2].price
			   },
			   {
				   label:data.leisure[3].type,
			       value: data.leisure[3].price
			   }]};
	
	  $scope.dataSource=$scope.myDataSource;
	    FusionCharts.ready(function() {
	    
	        var conversionChart = new FusionCharts({
	          type: 'column2d',
	          renderAt: 'chart-container',
	          width: "100%",
	          dataFormat: 'json',
	          dataSource: $scope.dataSource
	        });
	        conversionChart.render();
	      });

	
	})


	
});