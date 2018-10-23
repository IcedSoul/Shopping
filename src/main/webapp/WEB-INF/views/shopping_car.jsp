<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>购物+</title>
	  <link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
	  <link href="${cp}/css/style.css" rel="stylesheet">

	  <script src="${cp}/js/jquery.min.js" type="text/javascript"></script>
	  <script src="${cp}/js/bootstrap.min.js" type="text/javascript"></script>
	  <script src="${cp}/js/layer.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
      <script src="${cp}/js/html5shiv.min.js"></script>
      <script src="${cp}/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <!--导航栏部分-->
	<jsp:include page="include/header.jsp"/>

	<!-- 中间内容 -->
	<div class="container-fluid bigHead">
		<div class="row">
			<div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
				<div class="jumbotron">
					<h1>欢迎来到购物车</h1>
					<p>您的购物车清单为</p>
				</div>
			</div>
			<div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
				<table class="table table-hover center" id="shoppingCarTable">
				</table>

				<hr/>
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4"></div>
					<button type="button" class="btn btn-danger btn-lg col-lg-4 col-md-4 col-sm-4" onclick="confirmPre()">确认购买</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 尾部 -->
	<jsp:include page="include/foot.jsp"/>

	<script type="text/javascript">
		updateShoppingCars();

		function updateShoppingCars() {
			var shoppingCarTable = document.getElementById("shoppingCarTable");
			var allShoppingCars = getShoppingCars();
			shoppingCarTable.innerHTML = "";
			var html = '<tr>'+
							'<th>是否购买</th>'+
							'<th>商品名称</th>'+
							'<th>商品单价</th>'+
							'<th>商品数量</th>'+
						'</tr>';
			for(var i=0;i<allShoppingCars.length;i++){
				var product = getProductById(allShoppingCars[i].productId);
				html  += '<tr>'+
						'<td>'+
						'<div class="checkbox">'+
						'<label>'+
						'<input type="checkbox" id="checkbox'+allShoppingCars[i].productId+'" value="option1">'+
						'</label>'+
						'</div>'+
						'</td>'+
						'<td>'+product.name+'</td>'+
						'<td>'+product.price+'</td>'+
						'<td>'+allShoppingCars[i].counts+'</td>'+
						'</tr>';
			}
			shoppingCarTable.innerHTML += html;

		}

		function getShoppingCars() {
			judgeIsLogin();
			var shoppingCarProducts = "";
			var user = {};
			user.userId = ${currentUser.id};
			$.ajax({
				async : false, //设置同步
				type : 'POST',
				url : '${cp}/getShoppingCars',
				data : user,
				dataType : 'json',
				success : function(result) {
					shoppingCarProducts = result.result;
				},
				error : function(result) {
					layer.alert('查询错误');
				}
			});
			shoppingCarProducts = eval("("+shoppingCarProducts+")");
			return shoppingCarProducts;
		}

		function confirmPre() {
			var allShoppingCars = getShoppingCars();
			var buyProducts = new Array;
			var buyProductsCounts = new Array;
			var buyCounts = 0;
			for(var i=0;i<allShoppingCars.length;i++){
				var checkBox = document.getElementById("checkbox"+allShoppingCars[i].productId);
				if(checkBox.checked){
					buyProducts[buyCounts] = allShoppingCars[i].productId;
					buyProductsCounts[buyCounts] = allShoppingCars[i].counts;
					buyCounts++;
				}
			}
			if(buyCounts == 0){
				layer.msg("未选中商品",{icon:2});
			}
			else{
				buyConfirm(buyProducts,buyProductsCounts);
			}
		}

		function getProductById(id) {
			var productResult = "";
			var product = {};
			product.id = id;
			$.ajax({
				async : false, //设置同步
				type : 'POST',
				url : '${cp}/getProductById',
				data : product,
				dataType : 'json',
				success : function(result) {
					productResult = result.result;
				},
				error : function(result) {
					layer.alert('查询错误');
				}
			});
			productResult = JSON.parse(productResult);
			return productResult;
		}

        function judgeIsLogin() {
            if("${currentUser.id}" == null || "${currentUser.id}" == undefined || "${currentUser.id}" ==""){
                window.location.href = "${cp}/login";
            }
        }

		function buyConfirm(productsId,productsCounts) {
			var address = getUserAddress(${currentUser.id});
			var phoneNumber = getUserPhoneNumber(${currentUser.id});
			var totalPrice = 0;

			var html = '<div class="col-sm-1 col-md-1 col-lg-1"></div>'+
					'<div class="col-sm-10 col-md-10 col-lg-10">'+
					'<table class="table confirm-margin">';
			for(var i=0;i<productsId.length;i++){
				var product = getProductById(productsId[i]);
				html +=	'<tr>'+
					'<th>商品'+(i+1)+'名称：</th>'+
					'<td>'+product.name+'</td>'+
					'</tr>'+
					'<tr>'+
					'<th>商品单价：</th>'+
					'<td>'+product.price+'</td>'+
					'</tr>'+
					'<tr>'+
					'<th>购买数量：</th>'+
					'<td>'+productsCounts[i]+'</td>'+
					'</tr>'+
					'<tr>';
				totalPrice+=product.price*productsCounts[i];
			}
			html +='<th>总金额：</th>'+
					'<td>'+totalPrice+'</td>'+
					'</tr>'+
					'<tr>'+
					'<th>收货地址：</th>'+
					'<td>'+address+'</td>'+
					'</tr>'+
					'<tr>'+
					'<th>联系电话：</th>'+
					'<td>'+phoneNumber+'</td>'+
					'</tr>'+
					'</table>'+
					'<div class="row">'+
					'<div class="col-sm-4 col-md-4 col-lg-4"></div>'+
					'<button class="btn btn-danger col-sm-4 col-md-4 col-lg-4" onclick="addToShoppingRecordsPre(['+productsId+'],['+productsCounts+'])">确认购买</button>'+
					'</div>'+
					'</div>';
			layer.open({
				type:1,
				title:'请确认订单信息：',
				content:html,
				area:['650px','350px'],
			});
		}


		function getUserAddress(id) {
			var address = "";
			var user = {};
			user.id = id;
			$.ajax({
				async : false, //设置同步
				type : 'POST',
				url : '${cp}/getUserAddressAndPhoneNumber',
				data : user,
				dataType : 'json',
				success : function(result) {
					address = result.address;
				},
				error : function(result) {
					layer.alert('查询错误');
				}
			});
			return address;
		}


		function getUserPhoneNumber(id) {
			var phoneNumber = "";
			var user = {};
			user.id = id;
			$.ajax({
				async : false, //设置同步
				type : 'POST',
				url : '${cp}/getUserAddressAndPhoneNumber',
				data : user,
				dataType : 'json',
				success : function(result) {
					phoneNumber = result.phoneNumber;
				},
				error : function(result) {
					layer.alert('查询错误');
				}
			});
			return phoneNumber;
		}

		function addToShoppingRecordsPre(productsId,productsCounts) {
			for(var i=0;i<productsId.length;i++){
				addToShoppingRecords(productsId[i],productsCounts[i]);
			}
			layer.confirm('前往订单状态？', {icon: 1, title:'购买成功',btn:['前往订单','继续购买']},
					function(){
						window.location.href = "${cp}/shopping_record";
					},
					function(index){
                        window.location.href = "${cp}/shopping_car";
					}
			);
		}

		function addToShoppingRecords(productId,productCounts) {
			judgeIsLogin();
			var shoppingRecord = {};
			shoppingRecord.userId = ${currentUser.id};
			shoppingRecord.productId = productId;
			shoppingRecord.counts = productCounts;
			var buyResult = "";
			$.ajax({
				async : false,
				type : 'POST',
				url : '${cp}/addShoppingRecord',
				data : shoppingRecord,
				dataType : 'json',
				success : function(result) {
					buyResult = result.result;
				},
				error : function(result) {
					layer.alert('购买错误');
				}
			});
			var product = getProductById(productId);
			if(buyResult == "success") {
                deleteShoppingCar(productId);
				layer.msg("商品 "+product.name+" 购买成功",{icon:1});
			}
			else if(buyResult == "unEnough"){
				layer.alert("商品 "+product.name+" 库存不足，购买失败")
			}
		}

		function deleteShoppingCar(productId) {
            var shoppingCar = {};
            shoppingCar.userId = ${currentUser.id};
            shoppingCar.productId = productId;
            var deleteResult = "";
            $.ajax({
                async : false,
                type : 'POST',
                url : '${cp}/deleteShoppingCar',
                data : shoppingCar,
                dataType : 'json',
                success : function(result) {
                    deleteResult = result.result;
                },
                error : function(result) {
                    layer.alert('查询用户错误');
                }
            });
        }
	</script>
  </body>
</html>