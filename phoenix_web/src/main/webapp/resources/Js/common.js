/**
 * Created with phoenixframe.
 * User: mfy
 * Date: 14-8-28
 * Time: 下午4:44
 */
function U() {
    var url = arguments[0] || [];
    var param = arguments[1] || {};
    var url_arr = url.split('/');

    if (!$.isArray(url_arr) || url_arr.length < 2 || url_arr.length > 3) {
        return '';
    }

    if (url_arr.length == 2)
        url_arr.unshift(_GROUP_);

    var pre_arr = ['g', 'm', 'a'];

    var arr = [];
    for (d in pre_arr)
        arr.push(pre_arr[d] + '=' + url_arr[d]);

    for (d in param)
        arr.push(d + '=' + param[d]);

    return _APP_+'?'+arr.join('&');
}

function start(url){
	var myDialog = 	art.dialog({
		icon : 'face-smile',
		title : '提示',
		drag : true,
		resize : false,
		content : '正在处理请求....',
		ok : true,
	});

		 JSer.url(url).ajax({
		    method:"POST", 
		    success:function(d){
		    	obj = JSON.parse(d);//字符串转为json对象
		    	myDialog.content(obj.msg);
		    },
		});
  }
