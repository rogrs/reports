angular.module('com.igumnov.seedproject', [ 'ui.bootstrap', 'ngResource' ])

.factory('City', [ '$resource', function($resource) {
    return $resource('/rest/city/:resourceId', {
	resourceId : '@id'
    }, {
	query : {
	    method : 'GET',
	    cache : false,
	    isArray : false
	}

    });
} ])

.factory('Country', [ '$resource', function($resource) {
    return $resource('/rest/city/:resourceId/country', {
	resourceId : '@id'
    }, {
	query : {
	    method : 'GET',
	    cache : false,
	    isArray : false
	}
    });
} ])

.factory('Report', [ '$resource', function($resource) {
    return $resource('/ajax/report', {}, {
	generate : {
	    method : 'POST',
	    cache : false,
	    isArray : true
	}
    });
} ])

.controller('CityCtrl', function($scope, $window, City, Country, Report) {
    City.query({}, function(data) {
	var j = 0;
	$scope.cities = data._embedded.all;
	var arrayLength = $scope.cities.length;
	for (var i = 0; i < arrayLength; i++) {
	    Country.query({
		resourceId : $scope.cities[i].name
	    }, function(data) {
		$scope.cities[j].countryName = data.name;
		++j;
	    });

	}
    });

    $scope.report = function() {
	Report.generate({}, $scope.cities, function(data) {
	    $window.location.href = '/jasper/report.pdf';
	}, function(err) {
	    alert(err.data.message);
	});
    }
});