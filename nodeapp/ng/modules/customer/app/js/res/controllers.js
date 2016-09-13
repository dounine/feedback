define(['angular','services'], function(angular,config) {
    "use strict";

    var app = angular.module(
        "app.controllers.customer",//定义的模块名称
        [
            "app.services.customer"
        ]);

    app.controller('customer',['$scope','$rootScope','config','$http','$location',ctl]);
    //app.controller('pagecontroller',['$scope','BusinessService',page]);

    function ctl($scope, $rootScope,config,$http,$location) {
        var vm = $scope;
        vm.addmenu = false;
        vm.addcla = false;
        vm.flex1 = function () {
            vm.addmenu = !vm.addmenu;
            vm.addcla =  !vm.addcla
        }

        vm.information = false;
        vm.infocla = false;
        vm.flex2 = function () {
            vm.information=!vm.information;
            vm.infocla = !vm.infocla
        }
        vm.subForm = function(){

            var data = {
                details: {
                    /****化学电池数据****/
                    chemistry:{
                        hx_product : vm.hx_product,
                        hx_spec : vm.hx_spec,
                        hx_trademark:vm.hx_trademark,
                        hx_number:vm.hx_number,
                        hx_senddate:vm.hx_senddate,
                        hx_size:vm.hx_size,
                        hx_weight:vm.hx_weight,
                        hx_parameter:vm.hx_parameter,
                        hx_carton:vm.hx_carton,
                        hx_wooden:vm.hx_wooden,
                        hx_otherbox:vm.hx_otherbox,
                        hx_complete:vm.hx_complete
                    },

                    physics:{
                    /*****物理电池数据****/
                    wl_product:vm.wl_product,
                    wl_spec:vm.wl_spec,
                    wl_trademark:vm.wl_trademark,
                    wl_number:vm.wl_number,
                    wl_senddate:vm.wl_senddate,
                    wl_size:vm.wl_size,
                    wl_weight:vm.wl_weight,
                    wl_serial:vm.wl_serial,
                    a_silicon:vm.a_silicon,
                    m_silicon:vm.m_silicon,
                    n_silicon:vm.n_silicon,
                    p_silicon:vm.p_silicon,
                    other_silicon:vm.other_silicon,
                    wl_monosize:vm.wl_monosize,
                    wl_monothi:vm.wl_monothi,
                    wl_monoarea:vm.wl_monoarea,
                    wl_monopro:vm.wl_monopro,
                    toughened:vm.toughened,
                    notoughened:vm.notoughened,
                    glaweight:vm.glaweight,
                    series18:vm.series18,
                    series36:vm.series36,
                    series54:vm.series54,
                    series72:vm.series72,
                    parallel1:vm.parallel1,
                    parallel2:vm.parallel2,
                    parallel3:vm.parallel3,
                    parallel4:vm.parallel4,
                    parallel5:vm.parallel5,
                    voltage:vm.voltage,
                    cusappl:vm.cusappl,
                    wl_cusmark:vm.wl_cusmark,
                    wl_carton:vm.wl_carton,
                    wl_wooden:vm.wl_wooden,
                    wl_otherbox:vm.wl_otherbox,
                    wl_complete:vm.wl_complete
                },


                    /**开票信息**/
                    billingname:vm.billingname,
                    identification:vm.identification,
                    address:vm.address,
                    account:vm.account,

                    /***检测***/
                    detectionbasis:vm.detectionbasis,
                    detectionproject:vm.detectionproject,
                    promana:vm.promana,
                    remarks:vm.remarks
                }

            }
            if(vm.powerType === 'chemistry'){
                data.details.physics=null
            }else if(vm.powerType==='physics'){
                data.details.chemistry=null
            }
            //console.info(JSON.stringify(data))
            console.info(data);
            vm.tiji = {
                'title': "确定提交信息",
                'yes' : "确定",
                'no' : "取消，继续编辑"
            }
            vm.queding = function () {
                var count = 0
                vm.tiji = {
                    'title': "提交成功！",
                    'yes' : "继续添加信息",
                    'no' : "查看状态",
                };


                $('#tijiBtn').click(function () {
                    setTimeout(function(){
                        $rootScope.$state.go("customer");
                    },500)
                    //count ++
                });

                $('#returnUnpro').click(function(){
                    //window.location.href="customer#/unpro"
                    setTimeout(function(){
                        $rootScope.$state.go("unpro");
                    },500)
                    //count ++
                })
            }

        }

    }

    function page($scope, BusinessService){
        //var GetAllEmployee = function () {
        //
        //    var postData = {
        //        pageIndex: $scope.paginationConf.currentPage,
        //        pageSize: $scope.paginationConf.itemsPerPage
        //    }
        //
        //    BusinessService.list(postData).success(function (response) {
        //        $scope.paginationConf.totalItems = response.count;
        //        $scope.persons = response.items;
        //    });
        //
        //}
        //
        ////配置分页基本参数
        //$scope.paginationConf = {
        //    currentPage: 1,
        //    itemsPerPage: 5
        //};
        //
        ///***************************************************************
        // 当页码和页面记录数发生变化时监控后台查询
        // 如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
        // ***************************************************************/
        //$scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', GetAllEmployee);

        $scope.paginationConf = {
            currentPage: 1,
            totalItems: 8000,
            itemsPerPage: 15,
            pagesLength: 15,
            perPageOptions: [10, 20, 30, 40, 50],
            onChange: function(){

            }
        };
    }

});

