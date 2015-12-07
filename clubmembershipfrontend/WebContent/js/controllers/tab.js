angular.module('app', ['ui.bootstrap'])
    .controller('TabsDemoCtrl', function($scope) {
        $scope.tabs = [{
            title: 'Dynamic Title 1',
            content: 'Dynamic content 1'
        }];
    });
