define(['angular','services'], function(angular) {
    "use strict";

    var app = angular.module(
        "app.controllers.customer",//定义的模块名称
        [
            "app.services.customer"
        ]);

    app.controller('customer',['$scope','$rootScope','$http','$location',ctl]);

    function ctl($scope, $rootScope,$http,$location) {
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
        vm.tiji = {
            'title': "确定提交信息",
            'yes' : "确定",
            'no' : "取消，继续编辑"
        }
        vm.subForm = function(){
            var data = {
                details: {
                    /****化学电池数据****/
                    chemistry:{
                        chemicalCell:{
                            sampleName:vm.sampleName,
                            ts:vm.ts,
                            trademark:vm.trademark,
                            amount:vm.amount,
                            chemicalCellSubmitDate:vm.chemicalCellSubmitDate,
                            size:vm.size,
                            weight:vm.weight,
                            parameter:vm.parameter,
                            'packTypes.carton':'vm.packTypes.carton',
                            'packTypes.wooden':'vm.packTypes.wooden',
                            'packTypes.otherbox':'vm.packTypes.otherbox',
                            damage:vm.damage
                        }

                    },

                    physics:{
                    /*****物理电池数据****/
                    physicalEnergy:{
                        sampleName:vm.sampleName,
                        ts:vm.ts,
                        trademark:vm.trademark,
                        amount:vm.amount,
                        physicalEnergySubmitDate:vm.physicalEnergySubmitDate,
                        size:vm.size,
                        weight:vm.weight,
                        serialNumber:vm.serialNumber,

                        'materialTypes.a':'vm.materialTypes.a',
                        'materialTypes.m':'vm.materialTypes.m',
                        'materialTypes.n':'vm.materialTypes.n',
                        'materialTypes.p':'vm.materialTypes.p',
                        'materialTypes.o':'vm.materialTypes.o',
                        monomerSize:vm.monomerSize,
                        monomerThickness:vm.monomerThickness,
                        monomerAcreage:vm.monomerAcreage,
                        monomerType:vm.monomerType,
                        'tempered.yes':'vm.tempered.yes',
                        'tempered.no':'vm.tempered.no',
                        temperedThickness:vm.temperedThickness,
                        'seriesBattery.18':'vm.seriesBattery.18',
                        'seriesBattery.36':'vm.seriesBattery.36',
                        'seriesBattery.54':'vm.seriesBattery.54',
                        'seriesBattery.72':'vm.seriesBattery.72',
                        'parallelBattery.1':'vm.parallelBattery.1',
                        'parallelBattery.2':'vm.parallelBattery.2',
                        'parallelBattery.3':'vm.parallelBattery.3',
                        'parallelBattery.4':'vm.parallelBattery.4',
                        'parallelBattery.5':'vm.parallelBattery.5',
                        voltage:vm.voltage,
                        parameter:vm.parameter,
                        identifying:vm.identifying,
                        'packTypes.carton':'vm.packTypes.carton',
                        'packTypes.wooden':'vm.packTypes.wooden',
                        'packTypes.otherbox':'vm.packTypes.otherbox',
                        damage:vm.damage

                    }


                },
                    invoiceInfo:{
                        name:vm.name,
                        identifyNumber:vm.identifyNumber,
                        address:vm.address,
                        telephone:vm.telephone,
                        bank:vm.bank,
                        card:vm.card

                    },

                    detectionInfo:{
                        basis:vm.basis,
                        testItem:vm.testItem,
                        disposeType:vm.disposeType,
                        remarks:vm.remarks
                    }

                }

            }
            if(vm.powerType === 'chemistry'){
                data.details.physics=null
            }else if(vm.powerType==='physics'){
                data.details.chemistry=null
            }
            //console.info(JSON.stringify(data))
            console.info(data);



        }

        vm.list = function () {
            if(vm.tiji.no!='取消，继续编辑'){
                setTimeout(function(){
                    $rootScope.$state.go("unpro");
                },500)
            }
        }

        vm.queding = function () {

            if(vm.tiji.yes=='继续添加信息'){
                $('#confirm').modal('hide');

            }

            vm.tiji = {
                'title': "提交成功！",
                'yes' : "继续添加信息",
                'no' : "查看状态",
            };

        }

    }

});

