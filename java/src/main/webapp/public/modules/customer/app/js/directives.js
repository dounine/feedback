define(['app'], function(app) {
    "use strict";

    app.directive('cleartab',function () {
        return{
            restrict:'A',
            link:function (scope,element,attrs) {
                $('.clearWuli').click(function () {
                    $('#physics')[0].reset()
                })
                $('.clearHuaxue').click(function () {
                    $('#chemistry')[0].reset()

                })

            }
        }
    })

    app.directive('reset',function () {
        return{
            restrict:'A',
            link:function(scope,element,attrs){
                $(element).click(function(){
                    $('#chemistry')[0].reset();
                    $('#physics')[0].reset();
                    $('#cusinfo')[0].reset()
                })
            }
        }
    })

});

