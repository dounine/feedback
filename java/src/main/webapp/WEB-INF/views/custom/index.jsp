<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>工单</title>

    <link rel="stylesheet" type="text/css" href="${ctx}/public/lib/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/public/app/custom/css/form.css">
    <script src="${ctx}/public/lib/jquery/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/public/lib/bootstrap/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/public/lib/angular/angular.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/public/app/custom/js/app.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/public/app/custom/js/feedback.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/public/app/custom/js/form.js" type="text/javascript" charset="utf-8"></script>
</head>
<body ng-app="myapp">
<header>
    <div class="logo container">
        <a href="${ctx}/custom/index"><img src="${ctx}/public/app/custom/imgs/Feedbacklogo.png" class="pull-left"/></a>
        <p class="pull-right"><a href="${ctx}/logout">退出账号</a></p>
    </div>

</header>
<nav>
    <ul class="container">
        <li style="background: #fff; "><a href="javascript:;" style="color:rgba(40,184,244,1);">添加</a></li>
        <li><a href="${ctx}/custom/untreated/index">未处理</a></li>
        <li><a href="procing.html">处理中</a></li>
        <li><a href="proced.html">处理完毕</a></li>
    </ul>
</nav>



<div class="mainbody container">
    <div class="user">
        <h5>客户信息</h5>
        <div class="usercon">
            <ul>
                <li>
                    <span>单位名称(公章)：</span>
                    <input type="text" size="30"/>
                </li>
                <li>
                    <span>通讯地址：</span>
                    <input type="text" size="30"/>
                </li>
                <li>
                    <span>邮编：</span>
                    <input type="text" size="15"/>
                </li>
                <li>
                    <span>联系人：</span>
                    <input type="text" size="15"/>
                </li>
                <li>
                    <span>电话：</span>
                    <input type="text" size="15"/>
                </li>
                <li>
                    <span>传真：</span>
                    <input type="text" size="15"/>
                </li>
                <li>
                    <span>E-mail：</span>
                    <input type="text" size="15"/>
                </li>
                <li>
                    <span>填表日期：</span>
                    <input type="date" size="15"/>
                </li>
            </ul>

        </div>
    </div>
  <form method="post" id="add_from">
    <div class="product">
        <h5>样品信息</h5>
        <ul id="myTab" class="nav nav-tabs" role="tablist" style="border: none;">
            <li class="active">
                <a href="#huaxue" data-toggle="tab" class="clearWuli">化学电源(蓄电池)</a>
            </li>
            <li><a href="#wuli" data-toggle="tab" class="clearhuaxue">物理电源（太阳电池组件）</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">

            <ul class="huaxue tab-pane fade in active" id="huaxue">
                <li>
                    <span class="wid15">样品名称</span>
                    <span><input type="text" size="116" name="chemicalCell.sampleName"/></span>
                </li>
                <li>
                    <span class="wid15">型号规格</span>
                    <span><input type="text" size="116" name="chemicalCell.ts"/></span>
                </li>
                <li>
                    <span class="wid15">注册商标</span>
                    <span class=""><input type="text" name="chemicalCell.trademark"/></span>
                    <span class="wid15">样品数量</span>
                    <span><input type="text" name="chemicalCell.amount"/></span>
                    <span class="wid15">送样日期</span>
                    <span class=""><input type="text"name="chemicalCell.submitDate"/></span>
                </li>
                <li>
                    <span class="wid15">外形尺寸</span>
                    <span style="width: 50%;"><input type="text" name="chemicalCell.size"/></span>
                    <span class="wid15">重量</span>
                    <span><input type="text" name="chemicalCell.weight"/></span>
                </li>
                <li>
                    <span class="wid15">充放电参数</span>
                    <span><input type="text" size="116" name="chemicalCell.parameter"/></span>
                </li>
                <li>
                    <span class="wid15">包装类别</span>
                    <span style="width: 50%;">
								<input type="checkbox" name="packTypes"/>纸箱
								<input type="checkbox" name="packTypes"/>木箱
								<input type="checkbox" name="packTypes"/>无包装
							</span>
                    <span class="wid15">样品状态（装箱前后）</span>
                    <span>
								<input type="radio" name="damage" value="true"/>完整
								<input type="radio" name="damage" value="false"/>破损

							</span>

                </li>
            </ul>


            <ul class="wuli tab-pane fade" id="wuli">
                <li>
                    <span class="wid15">样品名称</span>
                    <span><input type="text" size="116" name="physicalEnergy.sampleName"/></span>
                </li>
                <li>
                    <span class="wid15">型号规格</span>
                    <span><input type="text" size="116" name="physicalEnergy.ts"/></span>
                </li>
                <li>
                    <span class="wid15">注册商标</span>
                    <span class=""><input type="text" name="physicalEnergy.trademark"/></span>
                    <span class="wid15">样品数量</span>
                    <span><input type="text" name="physicalEnergy.amount"/></span>
                    <span class="wid15">送样日期</span>
                    <span class=""><input type="text" name="physicalEnergy.submitDate"/></span>
                </li>
                <li>
                    <span class="wid15">外形尺寸</span>
                    <span style="width: 50%;"><input type="text" name="physicalEnergy.size"/></span>
                    <span class="wid15">重量</span>
                    <span><input type="text" name="physicalEnergy.weight"/></span>
                </li>
                <li>
                    <span class="wid15">样品序列号</span>
                    <span><input type="text" size="116" name="physicalEnergy.serialNumber"/></span>
                </li>
                <li>
                    <span class="wid15">单体电池材料</span>
                    <span>
								<input type="checkbox" name="physicalEnergy.materialTypes" value="单晶硅"/>单晶硅
								<input type="checkbox" name="physicalEnergy.materialTypes" value="多晶硅"/>多晶硅
								<input type="checkbox" name="physicalEnergy.materialTypes" value="N型"/>N型
								<input type="checkbox" name="physicalEnergy.materialTypes" value="P型"/>P型
								<input type="checkbox" name="physicalEnergy.materialTypes" value="其他"/>其他
							</span>
                </li>
                <li>
                    <span class="wid15">单体相关参数</span>
                    <span style="width:7%;text-align: center;">尺寸</span>
                    <span><input type="text" size="10" name="physicalEnergy.monomer_Size"/></span>
                    <span style="width:7%;text-align: center;">厚度</span>
                    <span><input type="text" size="10" name="physicalEnergy.monomer_thickness"/></span>
                    <span style="width:7%;text-align: center;">面积</span>
                    <span><input type="text" size="10" name="physicalEnergy.monomer_acreage"/></span>
                    <span>减反射膜类型</span>
                    <span><input type="text" size="27" name="physicalEnergy.monomer_type"/></span>
                </li>
                <li>
                    <span class="wid15">玻璃类型</span>
                    <span>
								<input type="checkbox" name="physicalEnergy.tempered " value="钢化"/>钢化
								<input type="checkbox" name="physicalEnergy.tempered" value="非钢化"/>非钢化
							</span>
                    <span>玻璃厚度</span>
                    <span><input type="text"/></span>
                </li>
                <li>
                    <span class="wid15">串联电池数</span>
                    <span>
								<input type="checkbox" name="physicalEnergy.series_battery" value="18"/>18
								<input type="checkbox" name="physicalEnergy.series_battery" value="36"/>36
								<input type="checkbox" name="physicalEnergy.series_battery" value="54"/>54
								<input type="checkbox" name="physicalEnergy.series_battery" value="72"/>72

							</span>
                    <span class="wid15">并联电池数</span>
                    <span>
								<input type="checkbox" name="physicalEnergy.parallel_battery" value="1"/>1
								<input type="checkbox" name="physicalEnergy.parallel_battery" value="2"/>2
								<input type="checkbox" name="physicalEnergy.parallel_battery" value="3"/>3
								<input type="checkbox" name="physicalEnergy.parallel_battery" value="4"/>4
								<input type="checkbox" name="physicalEnergy.parallel_battery" value="5"/>5
							</span>
                </li>
                <li>
                    <span class="wid15">系统标称电压</span>
                    <span><input type="text" size="116" name="physicalEnergy.voltage"/></span>
                </li>wwwwwwwwwww
                <li id="teshuli">
                    <span class="wid15 " style="">客户提供配件和资料</span>
                    <span style="margin-left: 10px;" class="sp2">
									<div><span>①组件应有接线盒和引出线，并另附一套测试用转接线（必备）。</span></div>
									<div><span>②电器原理图、二极管型号、参数</span><span class="sp3"><input type="radio"
                                                                                              name="type6"/>有<input
                                            type="radio" name="type6"/>无</span></div>
									<div><span>③标识，包括：Voc、Isc、Vmp、Imp、Pmax等信息</span><span class="sp3"><input
                                            type="radio" name="type7"/>有<input type="radio"
                                                                               name="type7"/>无</span></span>
        </div>
        </span>
        </li>
        <li>
            <span class="wid15">包装类别</span>
            <span>
								<input type="checkbox" name="type8"/>纸箱
								<input type="checkbox" name="type8"/>木箱
								<input type="checkbox" name="type8"/>无包装
							</span>
            <span style="margin-left: 60px;width: 20%;text-align: center;">样品状态前后</span>
            <span><input type="radio" name="type8"/>完整
								<input type="radio" name="type8"/>破损</span>
        </li>
        </ul>

    </div>
</div>

<div class="product">
    <h5>检测依据、检测项目与样品处理信息</h5>
    <ul>
        <li>
            <span class="wid15">检测依据</span>
            <span><input type="text" size="116"/></span>
        </li>
        <li>
            <span class="wid15">检测项目</span>
            <span><input type="text" size="116"/></span>
        </li>
        <li>
            <span class="wid15">样品处置</span>
            <span class="">
									<input type="radio" name="type9"/>字体样品
									<input type="radio" name="type9"/>退回样品（运费到付）
									<input type="radio" name="type9"/>不要求
							</span>

        </li>
        <li>
            <span class="wid15">备注</span>

            <span><input type="text" size="116"/></span>
        </li>


    </ul>
</div>
<div class="product">
    <h5>开票信息</h5>
    <ul>
        <li>
            <span class="wid15">名称</span>
            <span><input type="text" size="116"/></span>
        </li>
        <li>
            <span class="wid15">纳税人识别号</span>
            <span><input type="text" size="116"/></span>
        </li>
        <li>
            <span class="wid15">地址、电话</span>
            <span class="">
									<input type="text" size="116"/>

							</span>

        </li>
        <li>
            <span class="wid15">开户行、账户</span>
            <span><input type="text" size="116"/></span>
        </li>

    </ul>
</div>

<div class="container but clearfix">

    <button type="button" class="btn btn-info pull-right" data-toggle="modal" data-target=".bs-example-modal-sm">提交
    </button>
    <button type="button" class="btn btn-default pull-right" id="resetBtn">重置</button>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true" ng-controller="tijiao">
    <div class="modal-dialog modal-sm">
        <div class="modal-content tijiao">
            <h4>{{tiji.title}}</h4>
            <button class="btn btn-default" data-dismiss="modal">{{tiji.no}}</button>
            <button class="btn btn-danger" data-toggle="modal2" onclick="$feedback.save()" id="tijiBtn">{{tiji.yes}}</button>
        </div>

    </div>
</div>
</div>

<form>

<script type="text/javascript">
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    var $feedback = new Feedback();
    $feedback.feedbackUrl = '${ctx}/custom/feedbackInfo/';
    $feedback.userUrl = '${ctx}/custom/userUrl/';
    $feedback.addFrom = $("#add_from");
    $feedback.init_model($feedback.feedbackUrl);
</script>
</body>
</html>