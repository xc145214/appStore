<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>app store</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css">

</head>
<body ng-app="store">
<div class="container" ng-controller="RootCtrl">


    <div ui-view></div>

</div>
<script src="${pageContext.request.contextPath}/bower_components/angular/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
<script src="${pageContext.request.contextPath}/bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/app.js"></script>
</body>
</html>