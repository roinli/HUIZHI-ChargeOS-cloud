// 本地
var api = 'http://127.0.0.1:10020/jingli-inter/outer';
// var api = 'https://dev.ptdn.com/jingli-inter/outer';
var appid = 'wxe36b8934277efd6c';

function toDecimal2(x) {
	var f = parseFloat(x);
	if (isNaN(f)) {
		return '0.00';
	}
	var f = Math.round(x * 100) / 100;
	var s = f.toString();
	var rs = s.indexOf('.');
	if (rs < 0) {
		rs = s.length;
		s += '.';
	}
	while (s.length <= rs + 2) {
		s += '0';
	}
	return s;
}
export default {
	api,
	appid,
	toDecimal2
};
