
module.exports = function (d) {
    if(!d)return null;
    var ds = '';
    var f = function(name,o) {
        for(var i in o){
            var v = o[i];
            if((typeof v)==='object'){
                f(i,v);
            }else{
                ds +=(name+'.'+i+'='+(v?v:'')+'&');
            }
        }
    }
    for(var i in d){
        var v = d[i];
        if((typeof v)==='object'){
            f(i,v);
        }else{
            ds += (i+'='+v+'&');
        }
    }
    return ds;
}
//function listForm(prefix,objs) {
//	if(!objs)return null;
//	var ds = '';
//	var f = function(name,o,dx) {
//		for(var i in o){
//			var v = o[i];
//			if((typeof v)==='object'){
//				f(i,v,dx);
//			}else{
//				ds +=(prefix+'['+dx+'].'+name+'.'+i+'='+(v?v:'')+'&');
//			}
//		}
//	}
//	for(var dx in objs){
//		for(var i in objs[dx]){
//			var v = objs[dx][i];
//			if((typeof v)==='object'){
//				f(i,v,dx);
//			}else{
//				ds += (prefix+'['+dx+'].'+i+'='+v+'&');
//			}
//		}
//	}
//	return ds;
//}

//var obj = {name:'nihao',age:23};
//var objs = [{name:'haha',age:21},{name:'nihao',age:23,info:{data:'123'}}];
//console.log(listForm('users',objs));
