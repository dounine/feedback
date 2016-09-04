<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>未处理</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/public/lib/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/public/app/custom/css/untreated.css">
    <script src="${ctx}/public/lib/jquery/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/public/lib/bootstrap/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<header>
    <div class="logo container">
        <a href="${ctx}/custom/index"><img src="${ctx}/public/app/custom/imgs/Feedbacklogo.png" class="pull-left"/></a>
        <p class="pull-right"><a href="${ctx}/logout">退出账号</a></p>
    </div>

</header>
<nav>
    <ul class="container">
        <li><a href="${ctx}/custom/index">添加</a></li>
        <li><a href="${ctx}/custom/untreated/index">未处理</a></li>
        <li style="background: #fff; "><a href="jivascript:;" style="color:rgba(40,184,244,1);">处理中</a></li>
        <li><a href="${ctx}/custom/proced/index">处理完毕</a></li>
    </ul>
</nav>
<div class="untreabody container">
    <div class="srea pull-right"><input type="text" placeholder="输入关键字"/>
        <button class="btn btn-info">搜索</button>
    </div>
    <div class="untretable">
        <table class="table table-bordered">
            <tr>
                <th>日期</th>
                <th>状态</th>
                <th>客户信息</th>
                <th>样品名称</th>
                <th>送样日期</th>
                <th>备注</th>
            </tr>
            <tr>
                <td>2016/09/04</td>
                <td>未处理</td>
                <td>曾丹丹</td>
                <td>物理电池</td>
                <td>2016/09/04</td>
                <td>备注</td>
            </tr>
            <tr>
                <td>2016/09/04</td>
                <td>未处理</td>
                <td>曾丹丹</td>
                <td>物理电池</td>
                <td>2016/09/04</td>
                <td>备注</td>
            </tr>
            <tr>
                <td>2016/09/04</td>
                <td>未处理</td>
                <td>曾丹丹</td>
                <td>物理电池</td>
                <td>2016/09/04</td>
                <td>备注</td>
            </tr>
            <tr>
                <td>2016/09/04</td>
                <td>未处理</td>
                <td>曾丹丹</td>
                <td>物理电池</td>
                <td>2016/09/04</td>
                <td>备注</td>
            </tr>
            <tr>
                <td>2016/09/04</td>
                <td>未处理</td>
                <td>曾丹丹</td>
                <td>物理电池</td>
                <td>2016/09/04</td>
                <td>备注</td>
            </tr>
            <tr>
                <td>2016/09/04</td>
                <td>未处理</td>
                <td>曾丹丹</td>
                <td>物理电池</td>
                <td>2016/09/04</td>
                <td>备注</td>
            </tr>
        </table>

    </div>
    <div class="fenye">
        <a href="javascript:;" class="prey"></a>
        <ul class="pagination">
            <li class="active"><a href="javascript:;">1</a></li>
            <li><a href="javascript:;">2</a></li>
            <li><a href="javascript:;">3</a></li>
            <li><a href="javascript:;">4</a></li>
            <li><a href="javascript:;">5</a></li>
        </ul>
        <a href="javascript:;" class="next2"></a>
        <span class="text_go">
				跳转到：<input type="text" size="5"/> <button>GO</button>
			</span>

    </div>

</div>
</body>
<script type="text/javascript">
    $(function () {
        $('.fenye ul li').click(function () {
            $(this).addClass('active').siblings('li').removeClass('active')
        })
    })
</script>
</html>
