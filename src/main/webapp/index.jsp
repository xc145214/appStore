<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>app store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css">

</head>
<body ng-app="store">
<div class="container">

    <div class="row" ng-controller="appListCtrl">
        <div class="span12">

            <div class="list-group" >

                <a href="#" class="list-group-item active">Top 10 Apps</a>
                <button class="btn-info btn-default btn" type="button" ng-click="list()">get</button>
                <div class="list-group-item" ng-repeat="app in appList track by $index">
                    <table class="table list-group-item-text">
                        <tr>
                            <td id="show_appid" rowspan="2">{{app.appid}}</td>
                            <td id="show_img" rowspan="2"><img alt="app_img" ng-src="{{app.thumbnail_url}}"/>
                                {{app.thumbnail_url}}</td>
                            <td id="show_appname" colspan="2">{{app.title}}</td>
                            <td id="get_appdetail" rowspan="2">
                                <button class="btn btn-primary" type="button" ng-click="apps.selectApp(app)">GET
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td id="show_rate" colspan="2">rate:{{app.score}}/10</td>
                        </tr>
                    </table>
                </div>

            </div>


        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/bower_components/angular/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/app.js"></script>
</body>
</html>