var myApp = angular.module('halo',[]);

myApp.controller('EmailController', ['$scope', function($scope) {
  $scope.daftarEmail = [
      "endy.muhardin@gmail.com"
  ];
  
  $scope.tambahEmail = function(){
      console.log("Tambah Email : "+$scope.email);
      $scope.daftarEmail.push($scope.email);
      $scope.email = "";
  };
  
  $scope.hapusEmail = function(x){
      var indexOfX = $scope.daftarEmail.indexOf(x);
      $scope.daftarEmail.splice(indexOfX, 1);
  };
}]);