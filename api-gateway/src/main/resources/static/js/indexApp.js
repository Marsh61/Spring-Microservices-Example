
var app = angular.module('myApp', []);
app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.timeout = 20000;
}])
app.controller('myCtrl', function($scope, $http) {
    this.retrieve = function() {
    $http.get('http://127.0.0.1:80/api/stock-service/rest/stock/getStocks')
    .then(function (response) {
        console.log('inside'+ response);
        $scope.quotes = response.data;
    }, function (response) {
        console.log('came here');
    });
    }


    this.add = function() {
        var message = {
            quotes: [$scope.quote]
        }
        $http.post('http://127.0.0.1:80/api/db-service/rest/db/add', message)
            .then(function(response) {
                $scope.quotes = response.data;
            }, function(response) {
                console.log('error..');
            });
    }

    this.logout = function () {
        $http.post("logout", function() {});
        }
});