
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
//function listForm(prefix,deArr,objs) {
//	if(!objs)return null;
//	var f = function(name,o,dx) {
//		for(var i in o){
//			var v = o[i];
//			if((typeof v)==='object')
//				f(i,v,dx);
//			else
//				deArr.push(prefix+'['+dx+'].'+name+'.'+i+'='+(v || ''));
//			
//		}
//	}
//	for(var dx in objs){
//		var ob = objs[dx];
//		for(var i in ob){
//			var v = ob[i];
//			if((typeof v)==='object')
//				f(i,v,dx);
//			else
//				deArr.push(prefix+'['+dx+'].'+i+'='+v);
//			
//		}
//	}
//	return deArr.join('&');
//}
//
//var objs = [{name:'haha',age:21},{name:'nihao',age:23,info:{name:1,age:null}}];
//var deArr = [];
//console.log(listForm('users',deArr,objs));
//console.log(deArr.join('&'));
