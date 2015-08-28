var myApp = angular.module('halo', []);

myApp.controller('TopbarController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
        $scope.logout = function () {
            alert('Logout');
            $http.post('logout', {}).success(function () {
                $location.path("/");
            }).error(function (data) {
                alert("Error : "+data);
            });
        };
    }]);

myApp.controller('EmailController', ['$scope', function ($scope) {
        $scope.daftarEmail = [
            "endy.muhardin@gmail.com"
        ];

        $scope.tambahEmail = function () {
            console.log("Tambah Email : " + $scope.email);
            $scope.daftarEmail.push($scope.email);
            $scope.email = "";
        };

        $scope.hapusEmail = function (x) {
            var indexOfX = $scope.daftarEmail.indexOf(x);
            $scope.daftarEmail.splice(indexOfX, 1);
        };
    }]);

myApp.controller('KaryawanController', ['$scope', '$http', function ($scope, $http) {
        $scope.updateTabelKaryawan = function () {
            var promise = $http.get('/karyawan/list');
            promise.then(
                    function (responSukses) {
                        $scope.daftarKaryawan = responSukses.data;
                    },
                    function (responGagal) {
                        alert("Error Status : " + responGagal.status);
                    }
            );
        };

        $scope.simpanKaryawan = function () {
            console.log("simpan karyawan");
            console.log($scope.karyawan);

            var promise;

            $scope.error = function (name) {
                var s = $scope.form[name];
                return s.$invalid && s.$dirty ? "error" : "";
            };

            // bila ada id, update
            if ($scope.karyawan.id) {
                // bila idempotent, gunakan put
                // idempotent = aman untuk diulang2 tanpa mengubah kondisi akhir
                promise = $http.put('/karyawan/' + $scope.karyawan.id, $scope.karyawan);
            } else { // tidak ada id, create 
                promise = $http.post('/karyawan/', $scope.karyawan);
            }


            $scope.clearForm();

            promise.then(
                    function (responSukses) {
                        $scope.updateTabelKaryawan();
                    },
                    function (responGagal) {
                        alert("Error Status : " + responGagal.status);
                    }
            );
        };

        $scope.hapusKaryawan = function (k) {
            var promise = $http.delete('/karyawan/' + k.id);
            promise.then(
                    function (responSukses) {
                        $scope.updateTabelKaryawan();
                    },
                    function (responGagal) {
                        alert("Error Status : " + responGagal.status);
                    }
            );
        };

        $scope.editKaryawan = function (k) {
            $scope.karyawan = k;
        };

        $scope.clearForm = function () {
            $scope.karyawan = {};
        };
    }]);