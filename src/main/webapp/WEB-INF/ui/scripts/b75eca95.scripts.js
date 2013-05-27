"use strict";angular.module("services.config",[]).constant("configuration",{apiRootURL:"",deleteAllowed:"false"});var zenQueryServices=angular.module("zenQueryServices",["ngResource","services.config"]);zenQueryServices.factory("DatabaseConnection",["$resource","configuration",function(a,b){return a(b.apiRootURL+"/api/v1/databaseConnections/:databaseConnectionId",{},{findAll:{method:"GET",params:{databaseConnectionId:"findAll"},isArray:!0},get:{method:"GET"},create:{method:"POST"},update:{params:{databaseConnectionId:"@id"},method:"PUT"},"delete":{method:"DELETE"}})}]).factory("Query",["$resource","configuration",function(a,b){return a(b.apiRootURL+"/api/v1/queries/:queryId",{},{findAll:{method:"GET",params:{queryId:"findAll"},isArray:!0},get:{method:"GET"},create:{method:"POST"},update:{params:{queryId:"@id"},method:"PUT"},"delete":{method:"DELETE"}})}]).factory("QueryFinder",["$resource","configuration",function(a,b){return a(b.apiRootURL+"/api/v1/queries/:action/:databaseConnectionId",{},{findByDatabaseConnectionId:{method:"GET",params:{action:"findByDatabaseConnectionId"},isArray:!0}})}]).factory("QueryVersion",["$resource","configuration",function(a,b){return a(b.apiRootURL+"/api/v1/queryVersions/:queryVersionId",{},{findAll:{method:"GET",params:{queryVersionId:"findAll"},isArray:!0},get:{method:"GET"},create:{method:"POST"},update:{params:{queryVersionId:"@id"},method:"PUT"},"delete":{method:"DELETE"}})}]).factory("QueryVersionFinder",["$resource","configuration",function(a,b){return a(b.apiRootURL+"/api/v1/queryVersions/:action/:queryId",{},{findByQueryId:{method:"GET",params:{action:"findByQueryId"},isArray:!0},findPreviousVersionsByQueryId:{method:"GET",params:{action:"findPreviousVersionsByQueryId"},isArray:!0},findCurrentByQueryId:{method:"GET",params:{action:"findCurrentByQueryId"}}})}]).factory("ResultSet",["$resource","configuration",function(a,b){return a(b.apiRootURL+"/api/v1/resultSetForQuery/:queryId",{},{get:{method:"GET",isArray:!0}})}]).factory("ResultSetFinder",["$resource","configuration",function(a,b){return a(b.apiRootURL+"/api/v1/resultSetForQuery/:queryId/:version",{},{get:{method:"GET",isArray:!0}})}]),angular.module("zenQueryUiApp",["ngCookies","ngResource","ngSanitize","ngRoute","pascalprecht.translate","ui.select2","ui.bootstrap","chieffancypants.loadingBar","ngAnimate","services.config","zenQueryServices"]).config(["$routeProvider","$translateProvider",function(a,b){a.when("/",{templateUrl:"views/queries.html",controller:"QueriesCtrl"}).when("/databaseConnections",{templateUrl:"views/databaseConnections.html",controller:"DatabaseConnectionsCtrl"}).when("/queries",{templateUrl:"views/queries.html",controller:"QueriesCtrl"}).otherwise({redirectTo:"/"}),b.translations("en_UK",{NAME:"Name",URL:"URL",USERNAME:"Username",PASSWORD:"Password",TOTAL:"Total",HOME:"Home",CONTACT:"Contact",ABOUT:"About",DATABASE_CONNECTIONS:"Database connections",QUERIES:"Queries",VERSIONS:"Versions",RESULT_SETS:"Result sets",RESULT_SET:"Result set",DETAILS:"Details",CREATE:"Create",UPDATE:"Update",DELETE:"Delete",EXECUTE:"Execute",NEW:"New",KEY:"ID",QUERY:"Query",CONTENT:"Content",SELECT:"Please select",DATABASE_CONNECTION:"Database connection",PREVIOUS_VERSIONS:"Previous versions",PREVIOUS_VERSION:"Previous version"}),b.translations("de_DE",{NAME:"Name",URL:"URL",USERNAME:"Nutzername",PASSWORD:"Passwort",TOTAL:"Gesamt",HOME:"Home",CONTACT:"Impressum",ABOUT:"Infos",DATABASE_CONNECTIONS:"Datenbankverbindungen",QUERIES:"Abfragen",VERSIONS:"Versionen",RESULT_SETS:"Abfrageergebnisse",RESULT_SET:"Abfrageergebnis",DETAILS:"Details",CREATE:"Erstellen",UPDATE:"Aktualisieren",DELETE:"Löschen",EXECUTE:"Ausführen",NEW:"Neu",KEY:"ID",QUERY:"Abfrage",CONTENT:"Inhalt",SELECT:"Bitte auswählen",DATABASE_CONNECTION:"Datenbankverbindung",PREVIOUS_VERSIONS:"Vorherige Versionen",PREVIOUS_VERSION:"Vorherige Version"}),b.preferredLanguage("en_UK")}]),angular.module("zenQueryUiApp").controller("MainCtrl",function(){}),angular.module("zenQueryUiApp").controller("DatabaseConnectionsCtrl",["$scope","DatabaseConnection","configuration",function(a,b,c){var d=function(b){var c=(a.currentPage-1)*a.itemsPerPage,d=c+a.itemsPerPage;a.filteredDatabaseConnections=b.slice(c,d)},e=function(){a.databaseConnections=b.findAll(function(b){a.total=b.length,d(b)})};a.selectRow=function(b){a.selectedRow=b},a.showDetails=function(c){a.databaseConnection=b.get({databaseConnectionId:c})},a.numberOfPages=function(){return Math.ceil(a.databaseConnections.length/a.itemsPerPage)},a.selectPage=function(b){a.currentPage=b,d(a.databaseConnections)},a.new=function(){a.databaseConnection=null},a.create=function(){a.databaseConnection=b.create(a.databaseConnection,function(){e()})},a.update=function(){b.update(a.databaseConnection,function(){e()})},a.delete=function(c){a.databaseConnection=b.delete({databaseConnectionId:c},function(){e()})},a.configuration=c;var f=new Date;a.currentYear=f.getFullYear(),a.filteredDatabaseConnections=[],a.currentPage=1,a.itemsPerPage=5,a.maxSize=5,e()}]),angular.module("zenQueryUiApp").controller("QueriesCtrl",["$scope","Query","QueryVersion","QueryVersionFinder","DatabaseConnection","ResultSet","configuration",function(a,b,c,d,e,f,g){var h=function(){a.queries=b.findAll(function(b){a.total=b.length})},i=function(){a.databaseConnections=e.findAll()};a.selectRow=function(b){a.selectedRow=b},a.showDetails=function(c){a.query=b.get({queryId:c},function(b){a.queryVersion={},a.queryVersion.content=b.content,a.execute()})},a.execute=function(){a.resultSet=f.get({queryId:a.query.id},function(){var b=a.queryVersion.content;a.queryVersions=d.findPreviousVersionsByQueryId({queryId:a.query.id},function(){a.queryVersion.content=b})})},a.loadPreviousVersion=function(b){a.queryVersion.content=b},a.new=function(){a.query=null,a.queryVersion=null},a.create=function(){a.query=b.create(a.query,function(b){a.queryVersion.queryId=b.id,a.queryVersion.version=1,a.queryVersion.isCurrentVersion=!0,a.queryVersion=c.create(a.queryVersion,function(){h(),a.execute()})})},a.update=function(){a.query.content=a.queryVersion.content,b.update(a.query,function(){h(),a.execute()})},a.delete=function(c){a.query=b.delete({queryId:c},function(){a.new(),h()})},a.configuration=g;var j=new Date;a.currentYear=j.getFullYear(),h(),i()}]),angular.module("zenQueryUiApp").controller("QueryVersionCtrl",["$scope","QueryVersion",function(a,b){var c=function(){a.queryVersions=b.findAll(function(b){a.total=b.length})};a.selectRow=function(b){a.selectedRow=b},a.showDetails=function(c){a.queryVersion=b.get({queryVersionId:c})},a.new=function(){a.queryVersion=null},a.create=function(){a.queryVersion=b.create(a.queryVersion,function(){c()})},a.update=function(){b.update(a.queryVersion,function(){c()})},a.delete=function(d){a.queryVersion=b.delete({queryVersionId:d},function(){c()})};var d=new Date;a.currentYear=d.getFullYear(),c()}]),angular.module("zenQueryUiApp").controller("ResultSetsCtrl",["$scope",function(a){a.selectRow=function(b){a.selectedRow=b};var b=new Date;a.currentYear=b.getFullYear()}]);