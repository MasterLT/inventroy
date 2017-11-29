/**
 * Created by Administrator on 2017/2/10.
 */
$(function () {
    $('.menu .finance').parent().addClass('active');
    $('.menu .inventory').parent().addClass('active');

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
            "url": "/finance/getInventoryDate",
            "dataSrc": "aaData",
            "data": function (d) {
                var dept = $('#dept').val();
                //添加额外的参数传给服务器
                d.extra_search = dept;
            }
        },
        "columns": [
            {"data": "deptName"},
            {"data": "deptNum"},
            {"data": "deptUser"},
            {"data": "lastTime"},
            // {"data": "rentalStatus"},
            // {"data": "isReview"},
            // {
            //     "data": "isshow",
            //     render: function (data, type, row) {
            //         if (data == 0) {
            //             return "否";
            //         } else {
            //             return "是";
            //         }
            //
            //     }
            // }
        ],
        "columnDefs": [{
            // 定义操作列,######以下是重点########
            "targets": 4,//操作按钮目标列
            "data": null,
            "render": function (data, type, row) {
                var html;
                html = '<a onclick="getInventory(' + row.id + ')" data-toggle="modal" data-target="#myModal">发起盘点</a>'
                return html;
            }
        }]
    });

    $('#search').click(function () {
        table.ajax.reload();
    })

    $('#submit').click(function () {
        toastr.info("提交中请稍候！！！");
        $.ajax({
            url: "/finance/addInventory",
            type: "post",
            dataType: "json",
            data: {
                "departmentId": $("#deptId").val(),
                "assetNum": $("#invNum").val(),
            },
            success: function (data) {
                if (data != 0) {
                    toastr.info("提交成功");
                    $("#myModal").modal("hide");
                    table.ajax.reload();
                } else {
                    toastr.error("提交失败");
                }
            }
        });
    })
});

function getInventory(id) {
    $("#deptId").val(id);

    $.ajax({
        url: "/finance/detail",
        type: "post",
        dataType: "json",
        data: {
            "id": id
        },
        success: function (data) {
            $("#deptNum").val(data.deptName);
            $("#invNum").val(data.deptNum);
        }
    });
}