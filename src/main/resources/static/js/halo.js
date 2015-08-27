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

myApp.controller('KaryawanController', ['$scope', '$http', function($scope, $http){
    $scope.updateTabelKaryawan = function(){
        var promise = $http.get('/karyawan/list');
        promise.then(
            function(responSukses){
                $scope.daftarKaryawan = responSukses.data;
            }, 
            function(responGagal){
                alert("Error Status : "+responGagal.status);
            }
        );
    };
}]);