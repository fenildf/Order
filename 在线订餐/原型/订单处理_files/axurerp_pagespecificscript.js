﻿
var PageName = '订单处理';
var PageId = 'f5fafb0ed5ea4f15874672ccce03a262'
var PageUrl = '订单处理.html'
document.title = '订单处理';
var PageNotes = 
{
"pageName":"订单处理",
"showNotesNames":"False"}
var $OnLoadVariable = '';

var $CSUM;

var hasQuery = false;
var query = window.location.hash.substring(1);
if (query.length > 0) hasQuery = true;
var vars = query.split("&");
for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0].length > 0) eval("$" + pair[0] + " = decodeURIComponent(pair[1]);");
} 

if (hasQuery && $CSUM != 1) {
alert('Prototype Warning: The variable values were too long to pass to this page.\nIf you are using IE, using Firefox will support more data.');
}

function GetQuerystring() {
    return '#OnLoadVariable=' + encodeURIComponent($OnLoadVariable) + '&CSUM=1';
}

function PopulateVariables(value) {
    var d = new Date();
  value = value.replace(/\[\[OnLoadVariable\]\]/g, $OnLoadVariable);
  value = value.replace(/\[\[PageName\]\]/g, PageName);
  value = value.replace(/\[\[GenDay\]\]/g, '4');
  value = value.replace(/\[\[GenMonth\]\]/g, '3');
  value = value.replace(/\[\[GenMonthName\]\]/g, '三月');
  value = value.replace(/\[\[GenDayOfWeek\]\]/g, '星期日');
  value = value.replace(/\[\[GenYear\]\]/g, '2012');
  value = value.replace(/\[\[Day\]\]/g, d.getDate());
  value = value.replace(/\[\[Month\]\]/g, d.getMonth() + 1);
  value = value.replace(/\[\[MonthName\]\]/g, GetMonthString(d.getMonth()));
  value = value.replace(/\[\[DayOfWeek\]\]/g, GetDayString(d.getDay()));
  value = value.replace(/\[\[Year\]\]/g, d.getFullYear());
  return value;
}

function OnLoad(e) {

}

var u21 = document.getElementById('u21');

var u86 = document.getElementById('u86');
gv_vAlignTable['u86'] = 'top';
var u51 = document.getElementById('u51');

var u25 = document.getElementById('u25');
gv_vAlignTable['u25'] = 'center';
var u16 = document.getElementById('u16');
gv_vAlignTable['u16'] = 'center';
var u55 = document.getElementById('u55');

var u46 = document.getElementById('u46');
gv_vAlignTable['u46'] = 'top';
var u76 = document.getElementById('u76');
gv_vAlignTable['u76'] = 'top';
var u49 = document.getElementById('u49');

var u102 = document.getElementById('u102');

var u93 = document.getElementById('u93');

var u107 = document.getElementById('u107');

var u8 = document.getElementById('u8');
gv_vAlignTable['u8'] = 'center';
var u32 = document.getElementById('u32');

var u23 = document.getElementById('u23');

var u62 = document.getElementById('u62');
gv_vAlignTable['u62'] = 'top';
var u53 = document.getElementById('u53');

var u87 = document.getElementById('u87');

var u38 = document.getElementById('u38');
gv_vAlignTable['u38'] = 'top';
var u48 = document.getElementById('u48');
gv_vAlignTable['u48'] = 'top';
var u4 = document.getElementById('u4');

var u68 = document.getElementById('u68');
gv_vAlignTable['u68'] = 'top';
var u84 = document.getElementById('u84');
gv_vAlignTable['u84'] = 'top';
var u104 = document.getElementById('u104');
gv_vAlignTable['u104'] = 'top';
var u30 = document.getElementById('u30');
gv_vAlignTable['u30'] = 'center';
var u60 = document.getElementById('u60');
gv_vAlignTable['u60'] = 'top';
var u89 = document.getElementById('u89');

var u34 = document.getElementById('u34');

var u64 = document.getElementById('u64');
gv_vAlignTable['u64'] = 'top';
var u100 = document.getElementById('u100');
gv_vAlignTable['u100'] = 'top';
var u19 = document.getElementById('u19');
gv_vAlignTable['u19'] = 'center';
var u6 = document.getElementById('u6');

var u103 = document.getElementById('u103');

var u79 = document.getElementById('u79');

var u1 = document.getElementById('u1');

var u81 = document.getElementById('u81');

var u97 = document.getElementById('u97');

var u85 = document.getElementById('u85');

var u11 = document.getElementById('u11');

var u41 = document.getElementById('u41');

var u108 = document.getElementById('u108');
gv_vAlignTable['u108'] = 'top';
var u71 = document.getElementById('u71');

var u15 = document.getElementById('u15');

var u45 = document.getElementById('u45');

var u36 = document.getElementById('u36');
gv_vAlignTable['u36'] = 'top';
var u75 = document.getElementById('u75');

var u66 = document.getElementById('u66');
gv_vAlignTable['u66'] = 'top';
var u37 = document.getElementById('u37');

var u2 = document.getElementById('u2');

var u92 = document.getElementById('u92');
gv_vAlignTable['u92'] = 'top';
var u83 = document.getElementById('u83');

var u95 = document.getElementById('u95');

var u22 = document.getElementById('u22');
gv_vAlignTable['u22'] = 'center';
var u13 = document.getElementById('u13');
gv_vAlignTable['u13'] = 'center';
var u52 = document.getElementById('u52');
gv_vAlignTable['u52'] = 'top';
var u43 = document.getElementById('u43');

var u0 = document.getElementById('u0');

var u17 = document.getElementById('u17');

var u27 = document.getElementById('u27');
gv_vAlignTable['u27'] = 'center';
var u58 = document.getElementById('u58');
gv_vAlignTable['u58'] = 'top';
var u77 = document.getElementById('u77');

var u90 = document.getElementById('u90');
gv_vAlignTable['u90'] = 'top';
var u57 = document.getElementById('u57');

var u3 = document.getElementById('u3');
gv_vAlignTable['u3'] = 'center';
var u20 = document.getElementById('u20');

var u105 = document.getElementById('u105');

var u50 = document.getElementById('u50');
gv_vAlignTable['u50'] = 'top';
var u106 = document.getElementById('u106');
gv_vAlignTable['u106'] = 'top';
var u28 = document.getElementById('u28');

var u24 = document.getElementById('u24');

var u54 = document.getElementById('u54');
gv_vAlignTable['u54'] = 'top';
var u99 = document.getElementById('u99');

var u70 = document.getElementById('u70');
gv_vAlignTable['u70'] = 'top';
var u39 = document.getElementById('u39');

var u69 = document.getElementById('u69');

var u78 = document.getElementById('u78');
gv_vAlignTable['u78'] = 'top';
var u73 = document.getElementById('u73');

var u94 = document.getElementById('u94');
gv_vAlignTable['u94'] = 'top';
var u31 = document.getElementById('u31');

var u96 = document.getElementById('u96');
gv_vAlignTable['u96'] = 'top';
var u61 = document.getElementById('u61');

var u35 = document.getElementById('u35');

var u26 = document.getElementById('u26');

var u65 = document.getElementById('u65');

var u56 = document.getElementById('u56');
gv_vAlignTable['u56'] = 'top';
var u47 = document.getElementById('u47');

var u82 = document.getElementById('u82');
gv_vAlignTable['u82'] = 'top';
var u12 = document.getElementById('u12');

var u9 = document.getElementById('u9');

var u42 = document.getElementById('u42');
gv_vAlignTable['u42'] = 'top';
var u33 = document.getElementById('u33');
gv_vAlignTable['u33'] = 'center';
var u72 = document.getElementById('u72');
gv_vAlignTable['u72'] = 'top';
var u63 = document.getElementById('u63');

var u18 = document.getElementById('u18');

var u5 = document.getElementById('u5');
gv_vAlignTable['u5'] = 'center';
var u67 = document.getElementById('u67');

var u7 = document.getElementById('u7');

var u80 = document.getElementById('u80');
gv_vAlignTable['u80'] = 'top';
var u101 = document.getElementById('u101');

u101.style.cursor = 'pointer';
if (bIE) u101.attachEvent("onclick", Clicku101);
else u101.addEventListener("click", Clicku101, true);
function Clicku101(e)
{
windowEvent = e;


if (true) {

	self.location.href="创建订单.html" + GetQuerystring();

}

}

var u10 = document.getElementById('u10');
gv_vAlignTable['u10'] = 'center';
var u40 = document.getElementById('u40');
gv_vAlignTable['u40'] = 'top';
var u88 = document.getElementById('u88');
gv_vAlignTable['u88'] = 'top';
var u14 = document.getElementById('u14');

var u44 = document.getElementById('u44');
gv_vAlignTable['u44'] = 'top';
var u74 = document.getElementById('u74');
gv_vAlignTable['u74'] = 'top';
var u29 = document.getElementById('u29');

var u59 = document.getElementById('u59');

var u98 = document.getElementById('u98');
gv_vAlignTable['u98'] = 'top';
var u91 = document.getElementById('u91');

if (window.OnLoad) OnLoad();
