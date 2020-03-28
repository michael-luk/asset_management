﻿var app = angular.module('AuthBackendApp', ['tm.pagination', 'ui.grid', 'ui.grid.resizeColumns', 'ui.grid.selection', 'ui.grid.edit', 'angularFileUpload', 'fundoo.services', 'simditor', 'ui.grid.autoFitColumns']);

var checkboxTemplateAuth = '<div><input type="checkbox" ng-model="MODEL_COL_FIELD" ng-click="grid.appScope.updateEntity(col, row)" /></div>';
//var friendButtonTemplateAuth = '<div align="center" style="height:26px;line-height:24px"><a href="#" data-toggle="tooltip" title="跳转"><button class="btn btn-xs btn-primary" ng-click="grid.appScope.goToFriendPage(row.entity.id)"><i class="icon-share icon-white"></i></button></a></div>';
var friendButtonTemplateAuthUser = '<div align="center" style="height:26px;line-height:24px"><a href="#" data-toggle="tooltip" title="弹窗显示"><button class="btn btn-xs btn-success" ng-click="grid.appScope.popFriendUser(row.entity)"><i class="icon-list-alt icon-white"></i></button></a> <a href="#" data-toggle="tooltip" title="跳转"><button class="btn btn-xs btn-primary" ng-click="grid.appScope.goToFriendPageUser(row.entity.id)"><i class="icon-share icon-white"></i></button></a></div>';
var readonlyImageTemplateAuth = '<div><div ng-repeat="imageName in MODEL_COL_FIELD.split(\',\')"><div ng-show="imageName"><a class="fancybox" target="_blank" data-fancybox-group="gallery" fancybox ng-href="/showImage/{{imageName}}"><img ng-src="/showimg/thumb/{{imageName}}" style="width:50px;height:50px;float:left"></a></div></div></div>';
var readonlyCheckboxTemplateAuth = '<div><input type="checkbox" ng-model="MODEL_COL_FIELD" disabled="disabled" /></div>';

app.filter('safehtml', function ($sce) {
    return function (htmlString) {
        return $sce.trustAsHtml(htmlString);
    }
});


app.controller('AuthBackendController', ['$scope', '$http', '$upload', 'createDialog', '$log', function ($scope, $http, $upload, createDialogService, $log) {
	$scope.isAuth = true;
    
    if(GetQueryString('relate') && GetQueryString('relateValue')) {
        $scope.relate = GetQueryString('relate')
        $scope.relateValue = GetQueryString('relateValue')
    }
    
    var rowLowHeight = 26
    var rowHighHeight = 100 
    
    $scope.objFieldInfo = objFieldInfo
    $scope.objEnumInfo = objEnumInfo   
    
    $scope.mySelections = [];
    $scope.gridOptions = {
        data: 'list',
        rowHeight: hasImageField ? rowHighHeight : rowLowHeight,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function (rows) {
                $scope.mySelections = gridApi.selection.getSelectedRows();
            });
        }
    };

    $scope.gridOptions.columnDefs = [        
        {field: 'id', displayName: 'Id', width: '40', headerTooltip: '点击表头可进行排序', enableCellEdit: false},
        {field: 'isDefault', displayName: objFieldInfo.Auth.isDefault, headerTooltip: '点击表头可进行排序, 通过直接勾选操作来更新数据', enableCellEdit: false, cellTemplate: checkboxTemplateAuth},
        {field: 'name', displayName: objFieldInfo.Auth.name, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'code', displayName: objFieldInfo.Auth.code, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'lastUpdateTime', displayName: objFieldInfo.Auth.lastUpdateTime, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'createdAt', displayName: objFieldInfo.Auth.createdAt, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'comment', displayName: objFieldInfo.Auth.comment, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'friendUser', displayName: objFieldInfo.Auth.users, width: '80', headerTooltip: '弹窗/跳转显示', enableCellEdit: false, cellTemplate: friendButtonTemplateAuthUser},

    ];
    
    $scope.goToFriendPageUser = function(pid) { location = '/admin/user?relate=auths.id&relateValue=' + pid }
    
    $scope.friends4gridUser = []
    $scope.pageInfo4FriendUser = {}
    $scope.searchFieldNameUser = searchFieldNameUser
    $scope.searchFieldNameUserComment = searchFieldNameUserComment

    $scope.$watch('paginationConf4FriendUser.itemsPerPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithFriendsUser();
        }
    }, false);

    $scope.$watch('paginationConf4FriendUser.currentPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithFriendsUser();
        }
    }, false);

    $scope.paginationConf4FriendUser = {
        currentPage: 1, //首页
        itemsPerPage: 10, //每页显示数量
        pagesLength: 5,  //显示多少个页数格子
        perPageOptions: [1, 2, 5, 10, 20, 50, 100],//选择每页显示数量
        rememberPerPage: 'itemsPerPage4FriendUser'
    };
    
    function fillGridWithFriendsUser() {
        url = '/base/User/all?page=' 
                    + $scope.paginationConf4FriendUser.currentPage 
                    + '&size=' + $scope.paginationConf4FriendUser.itemsPerPage
                    
        if ($scope.currentObj.queryKeyword4User)
            url += '&searchOn=' + $scope.searchFieldNameUser + '&kw=' + $scope.currentObj.queryKeyword4User
            
        $http.get(url)
            .success(function (data, status, headers, config) {
            if (data.flag) {
                $scope.pageInfo4FriendUser = data.page;
                $scope.paginationConf4FriendUser.totalItems = data.page.total;
                
                if ($scope.currentObj.id) {
                    var allFriends = data.data;
                    
                    //用于比较, 全取不分页
                    $http.get('/auth/' + $scope.currentObj.id + '/users?page=1&size=1000000')
                    .success(function (data, status, headers, config) {
                        if (data.flag){
                            for (x in allFriends) {
                                allFriends[x].refFriend = false
                                for (y in data.data) {
                                    if (allFriends[x].id === data.data[y].id) {
                                        allFriends[x].refFriend = true
                                        break
                                    }
                                }
                            }
                        }
                        $scope.friends4gridUser = allFriends;
                    })
                }
                else {
                    $scope.friends4gridUser = data.data;
                }
            }
            else {
                $scope.parentUsers4grid = [];
                //showAlert('错误: ', data.message, 'danger')
            }
        });
    }
    
    $scope.myFriendSelectionsUser = [];
    $scope.gridFriendsUser = {
        data: 'friends4gridUser',
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: true,
        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function (rows) {
                $scope.myFriendSelectionsUser = gridApi.selection.getSelectedRows();
            });
        },
        isRowSelectable: function(row){
            if(row.entity.refFriend == true){
                row.grid.api.selection.selectRow(row.entity);
            }
        },
        columnDefs: [        
            {field: 'id', displayName: 'Id', width: '30', enableCellEdit: false},
            {field: 'name', displayName: '名称', width: '45%', enableCellEdit: true},
            {field: 'createdAt', displayName: '创建时间', width: '45%', enableCellEdit: true},
        ]
    };

    $scope.searchContent4User = function(){
        if($scope.paginationConf4FriendUser.currentPage != 1){
            $scope.paginationConf4FriendUser.currentPage = 1
        }
        else{
            fillGridWithFriendsUser()
        }
    }
    
    $scope.currentObj = {}
    $scope.list = []
    $scope.pageInfo = {}
    $scope.queryKeyword = ''
    $scope.startTime = ''
    $scope.endTime = ''
    
    $scope.$watch('paginationConf.itemsPerPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            refreshData(false);
        }
    }, false);

    $scope.$watch('paginationConf.currentPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            refreshData(false);
        }
    }, false);

    $scope.paginationConf = {
        currentPage: 1, //首页
        itemsPerPage: 20, //每页显示数量
        pagesLength: 10,  //显示多少个页数格子
        perPageOptions: [1, 2, 5, 10, 20, 50, 100],//选择每页显示数量
        rememberPerPage: 'itemsPerPage'
    };
    
    if (!GetQueryString('relate')) {
        refreshData(false);
    } 
    else {
        refreshData(true);
    }
    
    function getQueryUrl() {
        var url = 'startTime=' + $scope.startTime + '&endTime=' + $scope.endTime
                    
        
        if ($scope.relate) {
            url += '&fieldOn=' + $scope.relate + '&fieldValue=' + $scope.relateValue
        }
        
        if ($scope.queryKeyword)
            url += '&searchOn=' + searchFieldName + '&kw=' + $scope.queryKeyword
            
        return url
    }

    function refreshData(showMsg){
        var url = '/base/Auth/all?page=' 
                    + $scope.paginationConf.currentPage 
                    + '&size=' + $scope.paginationConf.itemsPerPage
                    + "&" + getQueryUrl();

        $http.get(url).success(function (data, status, headers, config) {
            if (data.flag) {
                $scope.list = data.data;
                $scope.pageInfo = data.page;
                $scope.paginationConf.totalItems = data.page.total;
            }
            else {
                if (showMsg) {
                    $scope.list = [];
                    showAlert('错误: ', data.message, 'danger')
                }
            }
        });
    }
    

    // 当前行更新字段 (only for checkbox & dropdownlist)
    $scope.updateEntity = function(column, row) {
        $scope.currentObj = row.entity;
        $scope.saveContent();
    };

    // 新建或更新对象
    $scope.saveContent = function() {
        var content = $scope.currentObj;
        if ($scope.myFriendSelectionsUser.length > 0) content.users = $scope.myFriendSelectionsUser
        
        var isNew = !content.id
        var url = '/auth'
        if(isNew){
        	var http_method = "POST";
        }else{
        	var http_method = "PUT";
        	url += '/' + content.id
        }
        
        $http({method: http_method, url: url, data:content}).success(function(data, status, headers, config) {
            if(data.flag){
                if(isNew){
                    $scope.list.unshift(data.data);
                    showAlert('操作成功: ', '', 'success')
                }else{
                    showAlert('操作成功', '', 'success')
                    gridApi.core.notifyDataChange(uiGridConstants.dataChange.ALL)
                }
            }else{
                if (data.message)
                    showAlert('错误: ', data.message, 'danger')
                else {
                    if(data.indexOf('<html') > 0){
                        showAlert('错误: ', '您没有修改权限, 请以超级管理员登录系统.', 'danger');
                        refreshData(false)
                        return
                    }
                }
            }
        });
    };

    $scope.deleteContent = function(){
        var items = $scope.mySelections;
        if(items.length == 0){
            showAlert('错误: ', '请至少选择一个对象', 'warning');
        }else{
            var content = items[0];
            if(content.id){
                bootbox.confirm("您确定要删除这个对象吗?", function(result) {
                    if(result) {
                        var deleteUrl = '/auth/' + content.id
                        $http.delete(deleteUrl).success(function(data, status, headers, config) {
                            if (data.flag) {
                            	var index = $scope.list.indexOf(content);
                                $scope.list.splice(index, 1);
                                $scope.mySelections = [];
                                showAlert('操作成功', '', 'success');
                            }
                            else {
                                if (data.message)
                                    showAlert('错误: ', data.message, 'danger')
                                else {
                                    if(data.indexOf('<html') > 0){
                                        showAlert('错误: ', '您没有修改权限, 请以超级管理员登录系统.', 'danger');
                                        refreshData(false)
                                        return
                                    }
                                }
                            }
                        });
                    }
                });
            }
        }
    };

    $scope.formSave = function(formOk){
    	if(!formOk){
            showAlert('错误: ', '输入项验证有误, 请重试', 'warning');
            return
    	}
        $scope.saveContent();
        $scope.$modalClose();
    };

    $scope.dialogClose = function(){
        $scope.$modalClose();
        refreshData(false)
    };
    
    $scope.addContent = function(){
        $http.get('/new/auth')
            .success(function (data, status, headers, config) {
                if (data.flag) {
                    $scope.currentObj = data.data;
                    
                    fillGridWithFriendsUser();
                    
                    createDialogService("/assets/html/edit_templates/auth.html",{
                        id: 'editor',
                        title: '新增',
                        scope: $scope,
                        footerTemplate: '<div></div>'
                    });
                }
            });
    };

    $scope.updateContent = function(){
        var items = $scope.mySelections;
        if(items.length == 0){
            showAlert('错误: ', '请至少选择一个对象', 'warning');
        }else{
            var content = items[0];
            if(content.id) {
                $scope.currentObj = items[0];
            }
        
            fillGridWithFriendsUser();

            createDialogService("/assets/html/edit_templates/auth.html",{
                id: 'editor',
                title: '编辑',
                scope: $scope,
                footerTemplate: '<div></div>'
            });
        }
    };
    
    $scope.searchContent = function(){
        if($scope.paginationConf.currentPage != 1){
            $scope.paginationConf.currentPage = 1
        }
        else{
            refreshData(true)
        }
    }
    
    $scope.report = function () {
        var notifyMsg = "将导出所有的数据, 确定吗?"
        if($scope.startTime && $scope.endTime) {
            notifyMsg = "将导出从 " + $scope.startTime + " 至 " + $scope.endTime + "之间的数据, 确定吗? (通过调整时间范围可以导出不同时间阶段的数据)"
        }
        bootbox.confirm(notifyMsg, function(result) {
            if(result) {
                location.href = '/report/auth?' + getQueryUrl()
            }
        });
    }
    
    $scope.refresh = function(){
        refreshData(true)
    }
    
    $.fn.datetimepicker.dates['zh-CN'] = {
        days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
        daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
        daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
        months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        today: "今天",
        suffix: [],
        meridiem: ["上午", "下午"]
    };

    $('#startTime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        minView: 'month',
        todayBtn: true,
        todayHighlight: true,
        autoclose: true
    });

    $('#endTime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        minView: 'month',
        todayBtn: true,
        todayHighlight: true,
        autoclose: true
    });

    
    ////////// friend User popup show //////////
    
    $scope.gridFriendUser = {
        data: 'friendUser4grid',
        rowHeight: rowLowHeight,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false
    };
    
    $scope.gridFriendUser.columnDefs = [        
        {field: 'id', displayName: 'Id', width: '40', enableCellEdit: false},
        {field: 'name', displayName: objFieldInfo.User.name, enableCellEdit: true},
        {field: 'loginName', displayName: objFieldInfo.User.loginName, enableCellEdit: true},
        {field: 'password', displayName: objFieldInfo.User.password, enableCellEdit: true},
        {field: 'headImage', displayName: objFieldInfo.User.headImage, enableCellEdit: true},
        {field: 'thirdId', displayName: objFieldInfo.User.thirdId, enableCellEdit: true},
        {field: 'email', displayName: objFieldInfo.User.email, enableCellEdit: true},
        {field: 'isEmailVerified', displayName: objFieldInfo.User.isEmailVerified, width: 120, enableCellEdit: false, cellTemplate: readonlyCheckboxTemplateAuth},
        {field: 'emailKey', displayName: objFieldInfo.User.emailKey, enableCellEdit: true},
        {field: 'emailOverTime', displayName: objFieldInfo.User.emailOverTime, enableCellEdit: true},
        {field: 'images', displayName: objFieldInfo.User.images, width: 170, enableCellEdit: false, cellTemplate: readonlyImageTemplateAuth},
        {field: 'sexEnum', displayName: objFieldInfo.User.sexEnum, enableCellEdit: false, cellTemplate: '<span ng-bind="grid.appScope.objEnumInfo.User.sexEnum[MODEL_COL_FIELD]"></span>'},
        {field: 'phone', displayName: objFieldInfo.User.phone, enableCellEdit: true},
        {field: 'cardNumber', displayName: objFieldInfo.User.cardNumber, enableCellEdit: true},
        {field: 'country', displayName: objFieldInfo.User.country, enableCellEdit: true},
        {field: 'province', displayName: objFieldInfo.User.province, enableCellEdit: true},
        {field: 'city', displayName: objFieldInfo.User.city, enableCellEdit: true},
        {field: 'zone', displayName: objFieldInfo.User.zone, enableCellEdit: true},
        {field: 'address', displayName: objFieldInfo.User.address, enableCellEdit: true},
        {field: 'lastUpdateTime', displayName: objFieldInfo.User.lastUpdateTime, enableCellEdit: true},
        {field: 'createdAt', displayName: objFieldInfo.User.createdAt, enableCellEdit: true},
        {field: 'lastLoginIp', displayName: objFieldInfo.User.lastLoginIp, enableCellEdit: true},
        {field: 'lastLoginTime', displayName: objFieldInfo.User.lastLoginTime, enableCellEdit: true},
        {field: 'lastLoginIpArea', displayName: objFieldInfo.User.lastLoginIpArea, enableCellEdit: true},
        {field: 'status', displayName: objFieldInfo.User.status, enableCellEdit: false, cellTemplate: '<span ng-bind="grid.appScope.objEnumInfo.User.status[MODEL_COL_FIELD]"></span>'},
        {field: 'comment', displayName: objFieldInfo.User.comment, enableCellEdit: true},
    ];

    $scope.popFriendUser = function (obj) {
        if (obj) {
            $scope.currentObj = obj;

            fillGridWithFriendUser()

            createDialogService("/assets/html/child_pop_templates/auth_2_user.html", {
                id: 'friend_user',
                title: '查看',
                scope: $scope,
                footerTemplate: '<div></div>'
            });
        } else {
            showAlert('错误: ', '数据不存在', 'danger');
        }
    };

    $scope.pageInfo4friendUser = {}

    $scope.$watch('paginationConf4FriendUser.itemsPerPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithFriendUser();
        }
    }, false);

    $scope.$watch('paginationConf4FriendUser.currentPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithFriendUser();
        }
    }, false);

    $scope.paginationConf4FriendUser = {
        currentPage: 1, //首页
        itemsPerPage: 10, //每页显示数量
        pagesLength: 5,  //显示多少个页数格子
        perPageOptions: [1, 2, 5, 10, 20, 50, 100],//选择每页显示数量
        rememberPerPage: 'itemsPerPage4friendUser'
    };

    function fillGridWithFriendUser() {
        $scope.friendUser4grid = []
        $http.get('/base/User/all?page='
        + $scope.paginationConf4FriendUser.currentPage
        + '&size=' + $scope.paginationConf4FriendUser.itemsPerPage
        + '&fieldOn=auths.id&fieldValue=' + $scope.currentObj.id)
            .success(function (data, status, headers, config) {
                if (data.flag) {
                    $scope.friendUser4grid = data.data;
                    $scope.pageInfo4friendUser = data.page;
                    $scope.paginationConf4FriendUser.totalItems = data.page.total;
                }
            });
    }
    
    ////////// web socket msg //////////
    var wsUri = ''
    var channelId = 'auth_backend_' + getNowFormatDate()
    $scope.websocketInit = function (isOn, host) {
        if (isOn) {
            wsUri = "ws://" + host + "/chat/connect";
            window.addEventListener("load", init, false);
        }
    }

    window.onbeforeunload = function () {
        websocket.send("close," + channelId)
    }

    function init() {
        websocket = new WebSocket(wsUri);
        websocket.onopen = function (evt) {
            onOpen(evt)
        };
        websocket.onclose = function (evt) {
            onClose(evt)
        };
        websocket.onmessage = function (evt) {
            onMessage(evt)
        };
        websocket.onerror = function (evt) {
            onError(evt)
        };
    }

    function onOpen(evt) {
        $scope.chatMsg = '即时通讯连接成功！'
        websocket.send("init," + channelId)
    }

    function onClose(evt) {
        $scope.chatMsg = '即时通讯关闭！'
    }

    function onMessage(evt) {
        if (evt.data.startsWith('update')) {
            $scope.chatMsg = "更新: " + evt.data + " / " + $scope.list.length + " - " + getNowFormatDate()
            refreshData(false)
        } else if (evt.data.startsWith('new')) {
            $scope.chatMsg = "新增: " + evt.data + " / " + $scope.list.length + " - " + getNowFormatDate()
            refreshData(false)
        } else if (evt.data.startsWith('delete')) {
            $scope.chatMsg = "删除: " + evt.data + " / " + $scope.list.length + " - " + getNowFormatDate()
            refreshData(false)
        }
        //if (evt.data == 'new') {
        //    $scope.chatMsg = "新增数据" + "(" + $scope.list.length + ")"
        //    refreshData(false)
        //} else {
        //    if (evt.data.indexOf("delete:") != -1) {
        //        var deleteId = evt.data.split(':')[1]
        //        for (x in $scope.list) {
        //            if ($scope.list[x].id.toString() == deleteId) {
        //                $scope.list.splice(x, 1)
        //                $scope.chatMsg = "删除: " + deleteId + "(" + $scope.list.length + ")"
        //                return
        //            }
        //        }
        //    } else {
        //        $scope.chatMsg = "更新: " + evt.data + "(" + $scope.list.length + ")"
        //        refreshData(false)
        //        //for (x in $scope.list) {
        //        //    if ($scope.list[x].id.toString() == evt.data) {
        //        //        $http.get('/base/Game/' + evt.data).success(function (data, status, headers, config) {
        //        //            if (data.flag) {
        //        //                $scope.list[x] = data.data
        //        //                $scope.chatMsg = "更新: " + evt.data + "(" + $scope.list.length + ")"
        //        //                return
        //        //            }
        //        //        });
        //        //    }
        //        //}
        //    }
        //}
    }

    function onError(evt) {
        $scope.chatMsg = '服务器报错: ' + evt.data
    }
}]);
