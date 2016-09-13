define(['app'], function(app) {
    "use strict";

    app.directive('cleartab',function () {
        return{
            restrict:'A',
            link:function (scope,element,attrs) {
                $('.clearWuli').click(function () {
                    $('#wuli input').each(function () {
                        $(this).val("");
                    })
                })
                $('.clearHuaxue').click(function () {
                    $('#huaxue input').each(function () {
                        $(this).val("");
                    })

                })

            }
        }
    })

    app.directive('clear',function () {
        return{
            restrict:'A',
            link:function(scope,element,attrs){
                $(element).click(function(){
                    window.location.reload()
                })
            }
        }
    })

});

