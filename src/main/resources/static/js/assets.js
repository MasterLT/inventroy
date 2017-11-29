/**
 * Created by Administrator on 2017/2/10.
 */
$(function () {
    $('.menu .dept').parent().addClass('active');
    $('.menu .assets').parent().addClass('active');

    table = $('#td').DataTable({
        "pagingType": "simple_numbers",//设置分页控件的模式
        searching: false,//屏蔽datatales的查询框
        aLengthMenu: [10],//设置一页展示10条记录
        "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
        "oLanguage": {  //对表格国际化
            "sLengthMenu": "每页显示 _MENU_条",
            "sZeroRecords": "没有找到符合条件的数据",
            //  "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
            "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
            "sInfoEmpty": "木有记录",
            "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
            "sSearch": "搜索：",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"

            }
        },
        "processing": true, //打开数据加载时的等待效果
        "serverSide": true,//打开后台分页
        "ajax": {
            "url": "/dept/getAssetDate",
            "dataSrc": "aaData",
            "data": function (d) {
                var dept = $('#dept').val();
                var assetcode = $('#assetcode').val();
                //添加额外的参数传给服务器
                d.extra_search = dept+"@"+assetcode;
            }
        },
        "columns": [
            {"data": "assetcode"},
            {"data": "name"},
            {"data": "place"},
            {"data": "inventoryUser"},
            {"data": "inventorystate"},
            {"data": "inventoryRemark"},
        ],
        "columnDefs": [{
            // 定义操作列,######以下是重点########
            "targets": 6,//操作按钮目标列
            "data": null,
            "render": function (data, type, row) {
                var html;
                html = '<a onclick="getDetail(\'' + row.assetcode + '\')" data-toggle="modal" data-target="#myModal">详情</a>'
                return html;
            }
        }]
    });

    $('#search').click(function () {
        table.ajax.reload();
    })

});

function getDetail(assetcode) {
    $.ajax({
        url: "/dept/detail",
        type: "post",
        dataType: "json",
        data: {
            "assetcode": assetcode,
        },
        success: function (data) {
            $("#assetcode1").html(data.assetcode);
            $("#assetsortname").html(data.assetsortname);
            $("#detailedlocation").html(data.detailedlocation);
            // $("#photo").html(data.photo);
            $("#name").html(data.name);
            $("#norms").html(data.norms);
            $("#unit").html(data.unit);
            $("#numbers").html(data.numbers);
            $("#price").html(data.price);
            $("#state").html(data.state);
            $("#deptname").html(data.deptname);
            $("#place").html(data.place);
            $("#manufacturer").html(data.manufacturer);
            $("#buydate").html(data.buydate);
            $("#projectid").html(data.projectid);
            $("#projectname").html(data.projectname);
            $("#valid").html(data.valid);
            $("#remark").html(data.remark);
            $("#guarantee").html(data.guarantee);
            $("#lifeperiods").html(data.lifeperiods);
            $("#periodsused").html(data.periodsused);
            $("#address").html(data.address);
            $("#inventorydate").html(data.inventorydate);
            $("#inventorystate").html(data.inventorystate);
            $("#longitude").html(data.longitude);
            $("#latitude").html(data.latitude);
            $("#inventoryRemark").html(data.inventoryRemark);
            $("#inventoryUser").html(data.inventoryUser);
        }
    });
}