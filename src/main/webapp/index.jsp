<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>app store</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css">

</head>
<body ng-app="store">
<div class="container">

    <div class="row" ng-controller="appListCtrl">
        <div class="span12">

            <div class="list-group">

                <h1 class="page-header">Top 10 Apps</h1>
                <div class="list-group-item" ng-repeat="app in appList track by $index">
                    <div class="row">
                        <div class="col-md-3">
                            <img alt="app_img" class="img-thumbnail" ng-src="{{app.thumbnailUrl}}"/>
                        </div>
                        <div class="col-md-9">
                            <ul>
                                <li>{{app.title}}</li>
                                <li>
                                    intor:{{app.intro}}
                                </li>
                                <li>rate:{{app.score}}/10</li>
                                <li>
                                    <button class="btn btn-primary" type="button" ng-click="selectApp(app)">GET
                                    </button>
                                </li>
                            </ul>

                        </div>
                    </div>

                </div>

            </div>


        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/bower_components/angular/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/app.js"></script>
</body>
</html>