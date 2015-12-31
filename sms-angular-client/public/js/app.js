angular.module('sms-ng-app', ['controllers', 'ngRoute', 'directives']).
    config(function ($routeProvider) {
        $routeProvider.when('/profiles', {
            templateUrl: 'views/partials/manageStudent.html'
        }).when('/new', {
            templateUrl: 'views/partials/addStudent.html'
        }).otherwise({
            redirectTo: '/profiles'
        });
    });