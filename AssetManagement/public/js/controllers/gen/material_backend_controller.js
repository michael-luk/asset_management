var app = angular.module('MaterialBackendApp', ['tm.pagination', 'ui.grid', 'ui.grid.resizeColumns', 'ui.grid.selection', 'ui.grid.edit', 'angularFileUpload', 'fundoo.services', 'simditor', 'ui.grid.autoFitColumns']);

var checkboxTemplateMaterial = '<div><input type="checkbox" ng-model="MODEL_COL_FIELD" ng-click="grid.appScope.updateEntity(col, row)" /></div>';
var childButtonTemplateMaterialPurchase = '<div align="center" style="height:26px;line-height:24px"><a href="#" data-toggle="tooltip" title="弹窗显示"><button class="btn btn-xs btn-success" ng-click="grid.appScope.popChildPurchase(row.entity)"><i class="icon-list-alt icon-white"></i></button></a> <a href="#" data-toggle="tooltip" title="跳转"><button class="btn btn-xs btn-primary" ng-click="grid.appScope.goToChildPagePurchase(row.entity.id)"><i class="icon-share icon-white"></i></button></a></div>';
var childButtonTemplateMaterialUseRequest = '<div align="center" style="height:26px;line-height:24px"><a href="#" data-toggle="tooltip" title="弹窗显示"><button class="btn btn-xs btn-success" ng-click="grid.appScope.popChildUseRequest(row.entity)"><i class="icon-list-alt icon-white"></i></button></a> <a href="#" data-toggle="tooltip" title="跳转"><button class="btn btn-xs btn-primary" ng-click="grid.appScope.goToChildPageUseRequest(row.entity.id)"><i class="icon-share icon-white"></i></button></a></div>';
//var friendButtonTemplateMaterial = '<div align="center" style="height:26px;line-height:24px"><a href="#" data-toggle="tooltip" title="跳转"><button class="btn btn-xs btn-primary" ng-click="grid.appScope.goToFriendPage(row.entity.id)"><i class="icon-share icon-white"></i></button></a></div>';
var friendButtonTemplateMaterialDepart = '<div align="center" style="height:26px;line-height:24px"><a href="#" data-toggle="tooltip" title="弹窗显示"><button class="btn btn-xs btn-success" ng-click="grid.appScope.popFriendDepart(row.entity)"><i class="icon-list-alt icon-white"></i></button></a> <a href="#" data-toggle="tooltip" title="跳转"><button class="btn btn-xs btn-primary" ng-click="grid.appScope.goToFriendPageDepart(row.entity.id)"><i class="icon-share icon-white"></i></button></a></div>';
var readonlyImageTemplateMaterial = '<div><div ng-repeat="imageName in MODEL_COL_FIELD.split(\',\')"><div ng-show="imageName"><a class="fancybox" target="_blank" data-fancybox-group="gallery" fancybox ng-href="/showImage/{{imageName}}"><img ng-src="/showimg/thumb/{{imageName}}" style="width:50px;height:50px;float:left"></a></div></div></div>';
var readonlyCheckboxTemplateMaterial = '<div><input type="checkbox" ng-model="MODEL_COL_FIELD" disabled="disabled" /></div>';

app.filter('safehtml', function ($sce) {
    return function (htmlString) {
        return $sce.trustAsHtml(htmlString);
    }
});


app.controller('MaterialBackendController', ['$scope', '$http', '$upload', 'createDialog', '$log', function ($scope, $http, $upload, createDialogService, $log) {
	$scope.isMaterial = true;
    
    if(GetQueryString('relate') && GetQueryString('relateValue')) {
        $scope.relate = GetQueryString('relate')
        $scope.relateValue = GetQueryString('relateValue')
    }
    
    var rowLowHeight = 26
    var rowHighHeight = 100 
    
    $scope.objFieldInfo = objFieldInfo
    $scope.objEnumInfo = objEnumInfo   
    
    $scope.status = [{"id": -100, "name": "全部"}]
    dropdownTemplateMaterialStatus = '<div>' +
        '<select ng-model="MODEL_COL_FIELD" ' +
        'ng-change="grid.appScope.updateEntity(col, row)" style="padding: 2px;">'
    for (var i = 0; i < Object.keys(objEnumInfo.Material.status).length; i++) {
        $scope.status.push({"id": i, "name": objEnumInfo.Material.status[i]})
        dropdownTemplateMaterialStatus += '<option ng-selected="MODEL_COL_FIELD==' + i
            + '" value=' + i + '>' + objEnumInfo.Material.status[i] + '</option>'
    }
    dropdownTemplateMaterialStatus += '</select></div>'

    // -100即选择"全部"
    $scope.selectedStatus = -100 
    $scope.$watch('selectedStatus', function (newValue, oldValue, scope) {
        if (newValue != oldValue) {
            if ($scope.list.length > 0) {
                if ($scope.paginationConf.currentPage != 1) {
                    $scope.paginationConf.currentPage = 1
                }
            }
            refreshData(true);
        }
    }, false);
    
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
        {field: 'name', displayName: objFieldInfo.Material.name, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'content', displayName: objFieldInfo.Material.content, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'quantity', displayName: objFieldInfo.Material.quantity, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'price', displayName: objFieldInfo.Material.price, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'status', displayName: objFieldInfo.Material.status, width: 120, headerTooltip: '点击表头可进行排序, 通过直接下拉选取操作来更新数据', enableCellEdit: false, cellTemplate: dropdownTemplateMaterialStatus},
        {field: 'lastUpdateTime', displayName: objFieldInfo.Material.lastUpdateTime, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'createdAt', displayName: objFieldInfo.Material.createdAt, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'comment', displayName: objFieldInfo.Material.comment, headerTooltip: '点击表头可进行排序', enableCellEdit: true},
        {field: 'childPurchase', displayName: objFieldInfo.Material.purchases, width: '80', headerTooltip: '弹窗/跳转显示', enableCellEdit: false, cellTemplate: childButtonTemplateMaterialPurchase},
        {field: 'childUseRequest', displayName: objFieldInfo.Material.useRequests, width: '80', headerTooltip: '弹窗/跳转显示', enableCellEdit: false, cellTemplate: childButtonTemplateMaterialUseRequest},
        {field: 'friendDepart', displayName: objFieldInfo.Material.departs, width: '80', headerTooltip: '弹窗/跳转显示', enableCellEdit: false, cellTemplate: friendButtonTemplateMaterialDepart},

    ];
    
    $scope.goToChildPagePurchase = function(pid) { location = '/admin/purchase?relate=material.id&relateValue=' + pid }
    $scope.goToChildPageUseRequest = function(pid) { location = '/admin/userequest?relate=material.id&relateValue=' + pid }
    $scope.goToFriendPageDepart = function(pid) { location = '/admin/depart?relate=materials.id&relateValue=' + pid }
    
    $scope.friends4gridDepart = []
    $scope.pageInfo4FriendDepart = {}
    $scope.searchFieldNameDepart = searchFieldNameDepart
    $scope.searchFieldNameDepartComment = searchFieldNameDepartComment

    $scope.$watch('paginationConf4FriendDepart.itemsPerPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithFriendsDepart();
        }
    }, false);

    $scope.$watch('paginationConf4FriendDepart.currentPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithFriendsDepart();
        }
    }, false);

    $scope.paginationConf4FriendDepart = {
        currentPage: 1, //首页
        itemsPerPage: 10, //每页显示数量
        pagesLength: 5,  //显示多少个页数格子
        perPageOptions: [1, 2, 5, 10, 20, 50, 100],//选择每页显示数量
        rememberPerPage: 'itemsPerPage4FriendDepart'
    };
    
    function fillGridWithFriendsDepart() {
        url = '/base/Depart/all?page=' 
                    + $scope.paginationConf4FriendDepart.currentPage 
                    + '&size=' + $scope.paginationConf4FriendDepart.itemsPerPage
                    
        if ($scope.currentObj.queryKeyword4Depart)
            url += '&searchOn=' + $scope.searchFieldNameDepart + '&kw=' + $scope.currentObj.queryKeyword4Depart
            
        $http.get(url)
            .success(function (data, status, headers, config) {
            if (data.flag) {
                $scope.pageInfo4FriendDepart = data.page;
                $scope.paginationConf4FriendDepart.totalItems = data.page.total;
                
                if ($scope.currentObj.id) {
                    var allFriends = data.data;
                    
                    //用于比较, 全取不分页
                    $http.get('/material/' + $scope.currentObj.id + '/departs?page=1&size=1000000')
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
                        $scope.friends4gridDepart = allFriends;
                    })
                }
                else {
                    $scope.friends4gridDepart = data.data;
                }
            }
            else {
                $scope.parentDeparts4grid = [];
                //showAlert('错误: ', data.message, 'danger')
            }
        });
    }
    
    $scope.myFriendSelectionsDepart = [];
    $scope.gridFriendsDepart = {
        data: 'friends4gridDepart',
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: true,
        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged($scope, function (rows) {
                $scope.myFriendSelectionsDepart = gridApi.selection.getSelectedRows();
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

    $scope.searchContent4Depart = function(){
        if($scope.paginationConf4FriendDepart.currentPage != 1){
            $scope.paginationConf4FriendDepart.currentPage = 1
        }
        else{
            fillGridWithFriendsDepart()
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
                    + '&status=' + $scope.selectedStatus
                    
        
        if ($scope.relate) {
            url += '&fieldOn=' + $scope.relate + '&fieldValue=' + $scope.relateValue
        }
        
        if ($scope.queryKeyword)
            url += '&searchOn=' + searchFieldName + '&kw=' + $scope.queryKeyword
            
        return url
    }

    function refreshData(showMsg){
        var url = '/base/Material/all?page=' 
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
        if ($scope.myFriendSelectionsDepart.length > 0) content.departs = $scope.myFriendSelectionsDepart
        
        var isNew = !content.id
        var url = '/material'
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
                        var deleteUrl = '/material/' + content.id
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
        $http.get('/new/material')
            .success(function (data, status, headers, config) {
                if (data.flag) {
                    $scope.currentObj = data.data;
                    
                    fillGridWithFriendsDepart();
                    
                    createDialogService("/assets/html/edit_templates/material.html",{
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
        
            fillGridWithFriendsDepart();

            createDialogService("/assets/html/edit_templates/material.html",{
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
                location.href = '/report/material?' + getQueryUrl()
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

    ////////// child Purchase popup show //////////
    
    $scope.gridChildPurchase = {
        data: 'childPurchase4grid',
        rowHeight: rowLowHeight,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false
    };
    
    $scope.gridChildPurchase.columnDefs = [        
        {field: 'id', displayName: 'Id', width: '40', enableCellEdit: false},
        {field: 'name', displayName: objFieldInfo.Purchase.name, enableCellEdit: true},
        {field: 'content', displayName: objFieldInfo.Purchase.content, enableCellEdit: true},
        {field: 'lastUpdateTime', displayName: objFieldInfo.Purchase.lastUpdateTime, enableCellEdit: true},
        {field: 'createdAt', displayName: objFieldInfo.Purchase.createdAt, enableCellEdit: true},
        {field: 'comment', displayName: objFieldInfo.Purchase.comment, enableCellEdit: true},
        {field: 'refMaterialId', displayName: objFieldInfo.Purchase.refMaterialId, enableCellEdit: true},
        {field: 'refUserId', displayName: objFieldInfo.Purchase.refUserId, enableCellEdit: true},
    ];

    $scope.popChildPurchase = function (obj) {
        if (obj) {
            $scope.currentObj = obj;

            fillGridWithChildPurchase()

            createDialogService("/assets/html/child_pop_templates/material_2_purchase.html", {
                id: 'child_purchase',
                title: '查看',
                scope: $scope,
                footerTemplate: '<div></div>'
            });
        } else {
            showAlert('错误: ', '数据不存在', 'danger');
        }
    };

    $scope.pageInfo4childPurchase = {}

    $scope.$watch('paginationConf4ChildPurchase.itemsPerPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithChildPurchase();
        }
    }, false);

    $scope.$watch('paginationConf4ChildPurchase.currentPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithChildPurchase();
        }
    }, false);

    $scope.paginationConf4ChildPurchase = {
        currentPage: 1, //首页
        itemsPerPage: 10, //每页显示数量
        pagesLength: 5,  //显示多少个页数格子
        perPageOptions: [1, 2, 5, 10, 20, 50, 100],//选择每页显示数量
        rememberPerPage: 'itemsPerPage4childPurchase'
    };

    function fillGridWithChildPurchase() {
        $scope.childPurchase4grid = []
        $http.get('/base/Purchase/all?page='
        + $scope.paginationConf4ChildPurchase.currentPage
        + '&size=' + $scope.paginationConf4ChildPurchase.itemsPerPage
        + '&fieldOn=material.id&fieldValue=' + $scope.currentObj.id)
            .success(function (data, status, headers, config) {
                if (data.flag) {
                    $scope.childPurchase4grid = data.data;
                    $scope.pageInfo4childPurchase = data.page;
                    $scope.paginationConf4ChildPurchase.totalItems = data.page.total;
                }
            });
    }
    ////////// child UseRequest popup show //////////
    
    $scope.gridChildUseRequest = {
        data: 'childUseRequest4grid',
        rowHeight: rowLowHeight,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false
    };
    
    $scope.gridChildUseRequest.columnDefs = [        
        {field: 'id', displayName: 'Id', width: '40', enableCellEdit: false},
        {field: 'name', displayName: objFieldInfo.UseRequest.name, enableCellEdit: true},
        {field: 'content', displayName: objFieldInfo.UseRequest.content, enableCellEdit: true},
        {field: 'status', displayName: objFieldInfo.UseRequest.status, enableCellEdit: false, cellTemplate: '<span ng-bind="grid.appScope.objEnumInfo.UseRequest.status[MODEL_COL_FIELD]"></span>'},
        {field: 'lastUpdateTime', displayName: objFieldInfo.UseRequest.lastUpdateTime, enableCellEdit: true},
        {field: 'createdAt', displayName: objFieldInfo.UseRequest.createdAt, enableCellEdit: true},
        {field: 'comment', displayName: objFieldInfo.UseRequest.comment, enableCellEdit: true},
        {field: 'refMaterialId', displayName: objFieldInfo.UseRequest.refMaterialId, enableCellEdit: true},
        {field: 'refUserId', displayName: objFieldInfo.UseRequest.refUserId, enableCellEdit: true},
    ];

    $scope.popChildUseRequest = function (obj) {
        if (obj) {
            $scope.currentObj = obj;

            fillGridWithChildUseRequest()

            createDialogService("/assets/html/child_pop_templates/material_2_use_request.html", {
                id: 'child_use_request',
                title: '查看',
                scope: $scope,
                footerTemplate: '<div></div>'
            });
        } else {
            showAlert('错误: ', '数据不存在', 'danger');
        }
    };

    $scope.pageInfo4childUseRequest = {}

    $scope.$watch('paginationConf4ChildUseRequest.itemsPerPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithChildUseRequest();
        }
    }, false);

    $scope.$watch('paginationConf4ChildUseRequest.currentPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithChildUseRequest();
        }
    }, false);

    $scope.paginationConf4ChildUseRequest = {
        currentPage: 1, //首页
        itemsPerPage: 10, //每页显示数量
        pagesLength: 5,  //显示多少个页数格子
        perPageOptions: [1, 2, 5, 10, 20, 50, 100],//选择每页显示数量
        rememberPerPage: 'itemsPerPage4childUseRequest'
    };

    function fillGridWithChildUseRequest() {
        $scope.childUseRequest4grid = []
        $http.get('/base/UseRequest/all?page='
        + $scope.paginationConf4ChildUseRequest.currentPage
        + '&size=' + $scope.paginationConf4ChildUseRequest.itemsPerPage
        + '&fieldOn=material.id&fieldValue=' + $scope.currentObj.id)
            .success(function (data, status, headers, config) {
                if (data.flag) {
                    $scope.childUseRequest4grid = data.data;
                    $scope.pageInfo4childUseRequest = data.page;
                    $scope.paginationConf4ChildUseRequest.totalItems = data.page.total;
                }
            });
    }
    
    ////////// friend Depart popup show //////////
    
    $scope.gridFriendDepart = {
        data: 'friendDepart4grid',
        rowHeight: rowLowHeight,
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false
    };
    
    $scope.gridFriendDepart.columnDefs = [        
        {field: 'id', displayName: 'Id', width: '40', enableCellEdit: false},
        {field: 'isDefault', displayName: objFieldInfo.Depart.isDefault, width: 120, enableCellEdit: false, cellTemplate: readonlyCheckboxTemplateMaterial},
        {field: 'name', displayName: objFieldInfo.Depart.name, enableCellEdit: true},
        {field: 'code', displayName: objFieldInfo.Depart.code, enableCellEdit: true},
        {field: 'lastUpdateTime', displayName: objFieldInfo.Depart.lastUpdateTime, enableCellEdit: true},
        {field: 'createdAt', displayName: objFieldInfo.Depart.createdAt, enableCellEdit: true},
        {field: 'comment', displayName: objFieldInfo.Depart.comment, enableCellEdit: true},
    ];

    $scope.popFriendDepart = function (obj) {
        if (obj) {
            $scope.currentObj = obj;

            fillGridWithFriendDepart()

            createDialogService("/assets/html/child_pop_templates/material_2_depart.html", {
                id: 'friend_depart',
                title: '查看',
                scope: $scope,
                footerTemplate: '<div></div>'
            });
        } else {
            showAlert('错误: ', '数据不存在', 'danger');
        }
    };

    $scope.pageInfo4friendDepart = {}

    $scope.$watch('paginationConf4FriendDepart.itemsPerPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithFriendDepart();
        }
    }, false);

    $scope.$watch('paginationConf4FriendDepart.currentPage', function(newValue, oldValue, scope){
        if (newValue != oldValue) {
            fillGridWithFriendDepart();
        }
    }, false);

    $scope.paginationConf4FriendDepart = {
        currentPage: 1, //首页
        itemsPerPage: 10, //每页显示数量
        pagesLength: 5,  //显示多少个页数格子
        perPageOptions: [1, 2, 5, 10, 20, 50, 100],//选择每页显示数量
        rememberPerPage: 'itemsPerPage4friendDepart'
    };

    function fillGridWithFriendDepart() {
        $scope.friendDepart4grid = []
        $http.get('/base/Depart/all?page='
        + $scope.paginationConf4FriendDepart.currentPage
        + '&size=' + $scope.paginationConf4FriendDepart.itemsPerPage
        + '&fieldOn=materials.id&fieldValue=' + $scope.currentObj.id)
            .success(function (data, status, headers, config) {
                if (data.flag) {
                    $scope.friendDepart4grid = data.data;
                    $scope.pageInfo4friendDepart = data.page;
                    $scope.paginationConf4FriendDepart.totalItems = data.page.total;
                }
            });
    }
    
    ////////// web socket msg //////////
    var wsUri = ''
    var channelId = 'material_backend_' + getNowFormatDate()
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
