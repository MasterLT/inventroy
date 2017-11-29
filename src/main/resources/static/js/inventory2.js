/**
 * Created by Administrator on 2017/2/10.
 */
var table;
var table1;

$(function () {

    $('.menu .dept').parent().addClass('active');
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
            "url": "/dept/getSeeDate",
            "dataSrc": "aaData",
            "data": function (d) {
                var state = $('#state').val();
                //添加额外的参数传给服务器
                d.extra_search = state;
            }
        },
        "columns": [
            {"data": "id"},
            {"data": "deptName"},
            {"data": "assetNum"},
            {"data": "deptUserName"},
            {"data": "startTime"},
            {"data": "endTime"},
            {"data": "days"},
            {"data": "state"},
            {"data": "completeNum"},
            {"data": "noNum"},
            {"data": "inventoryNum"},
        ],
        "columnDefs": [{
            // 定义操作列,######以下是重点########
            "targets": 11,//操作按钮目标列
            "data": null,
            "render": function (data, type, row) {
                var html;
                html = '<a onclick="getDetail(' + row.id + ')" style="margin-right: 20px">详情</a>'
                if (row.state == "盘点中")
                    html += '<a onclick="inventoryFinish(' + row.id + ')" >盘点完成</a>'
                else html += '<a onclick="toastr.info(\'数据导出中，请稍候！\')" href="outExl?id=' + row.id + '" >导出结果</a>'
                return html;
            }
        }]
    });

    table1 = $('#td1').DataTable({
        "pagingType": "simple_numbers",//设置分页控件的模式
        searching: false,//屏蔽datatales的查询框
        aLengthMenu: [10],//设置一页展示10条记录
        "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
        "ordering": false,
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
            "url": "/dept/getInventoryDate",
            "dataSrc": "aaData",
            "data": function (d) {
                var inventoryId = $('#inventoryId').val();
                var assetcode = $('#assetcode').val();
                var state1 = $('#state1').val();
                //添加额外的参数传给服务器
                d.extra_search = inventoryId + "@" + state1 + "@" + assetcode;
            }
        },
        "columns": [
            {"data": "id"},
            {"data": "inventoryId"},
            {"data": "deptname"},
            {"data": "assetcode"},
            {"data": "name"},
            {"data": "inventoryUser"},
            {"data": "inventorystate"},
            {"data": "inventoryRemark"},
            {"data": "option"}
        ],
        "columnDefs": [{
            // 定义操作列,######以下是重点########
            "targets": 0,//操作按钮目标列
            "data": null,
            "render": function (data, type, row) {
                var html = '<input type="checkbox" class="checkboxs" value="' + row.id + '"/>';
                return html;
            }
        }, {
            // 定义操作列,######以下是重点########
            "targets": 8,//操作按钮目标列
            "data": null,
            "render": function (data, type, row) {
                var html;
                if (row.inventorystate == "未盘点") {
                    html = '<a onclick="moveInventory(\'' + row.assetcode + '\')" data-toggle="modal" data-target="#myModal" style="margin-right: 20px">盘点移交</a>'
                    html += '<a onclick="noInventory(\'' + row.assetcode + '\')" data-toggle="modal" data-target="#myModal2">不盘点</a>'
                } else if (row.inventorystate == "已完成") {
                    html = '<a onclick="showImg(\'' + row.photo + '\')" data-toggle="modal" data-target="#myModal3" style="margin-right: 20px">盘点图片预览</a>'
                    html += '<a onclick="showPosition(' + row.longitude + ',' + row.latitude + ')" data-toggle="modal" data-target="#myModal6">盘点位置预览</a>'
                } else {
                    html = '无'
                }
                return html;
            }
        }]
    });

    $('#search').click(function () {
        table.ajax.reload();
    })

    $('#nobactsubmit').click(function () {
        var ids = new Array();
        $('.checkboxs:checked').each(function () {
            ids.push($(this).val());
        })
        var remark = $('#myModal5 #remark').val();
        $.ajax({
            url: "/dept/noInventoryBach",
            type: "post",
            dataType: "json",
            traditional: true,
            data: {
                "ids": ids,
                "remark": remark
            },
            success: function (data) {
                if (data != 0) {
                    toastr.info("提交成功");
                    $("#myModal5").modal("hide");
                    $("#myModal5 #remark").val("");
                    table1.ajax.reload();
                } else {
                    toastr.error("提交失败");
                }
            }
        });
    })

    $('#movebactsubmit').click(function () {
        var ids = new Array();
        $('.checkboxs:checked').each(function () {
            ids.push($(this).val());
        })
        var user = $("#myModal4 #user").val();
        $.ajax({
            url: "/dept/moveInventoryBach",
            type: "post",
            dataType: "json",
            traditional: true,
            data: {
                "ids": ids,
                "user": user
            },
            success: function (data) {
                if (data != 0) {
                    toastr.info("提交成功");
                    $("#myModal4").modal("hide");
                    table1.ajax.reload();
                } else {
                    toastr.error("提交失败");
                }
            }
        });
    })

    $('#search1').click(function () {
        table1.ajax.reload();
    })

    $('#return').click(function () {
        table.ajax.reload();
        $("#t").show();
        $("#t1").hide();
    })

    //不盘点
    $('#myModal2 #submit').click(function () {
        var inventoryId = $("#inventoryId").val();
        var assetcode = $("#myModal2 #assetcode2").val();
        var remark = $("#myModal2 #remark").val();
        $.ajax({
            url: "/dept/noInventory",
            type: "post",
            dataType: "json",
            data: {
                "inventoryId": inventoryId,
                "assetcode": assetcode,
                "remark": remark,
            },
            success: function (data) {
                if (data != 0) {
                    toastr.info("提交成功");
                    $("#myModal2").modal("hide");
                    $("#myModal2 #remark").val("");
                    table1.ajax.reload();
                } else {
                    toastr.error("提交失败");
                }
            }
        });
    })

    //移交
    $('#myModal #submit').click(function () {
        var inventoryId = $("#inventoryId").val();
        var assetcode = $("#myModal #assetcode2").val();
        var user = $("#myModal #user").val();
        $.ajax({
            url: "/dept/moveInventory",
            type: "post",
            dataType: "json",
            data: {
                "inventoryId": inventoryId,
                "assetcode": assetcode,
                "user": user,
            },
            success: function (data) {
                if (data != 0) {
                    toastr.info("提交成功");
                    $("#myModal").modal("hide");
                    table1.ajax.reload();
                } else {
                    toastr.error("提交失败");
                }
            }
        });
    })

});

function getDetail(id) {
    $("#inventoryId").val(id);
    table1.ajax.reload();
    $("#t").hide();
    $("#t1").show();
}

function inventoryFinish(id) {
    $.ajax({
        url: "/dept/inventoryFinish",
        type: "post",
        dataType: "json",
        data: {
            "id": id,
        },
        success: function (data) {
            if (data != 0) {
                toastr.info("提交成功");
                table.ajax.reload();
            } else {
                toastr.error("该次盘点还有有未盘点完成的资产！！");
            }
        }
    });
}

function moveInventory(assetcode) {
    $("#myModal #assetcode2").val(assetcode);
}

function noInventory(assetcode) {
    $("#myModal2 #assetcode2").val(assetcode);
}

function showImg(photo) {
    $("#myModal3 img").attr('src', photo);
}

function checkfk() {
    var allcheck = document.getElementById("checkAll");  //获取 全选check 的id
    var othercheck = document.getElementsByClassName("checkboxs"); //获取数组名称为fkcheck的 复选框
    for (var i = 0; i < othercheck.length; i++) {
        if (allcheck.checked) {
            othercheck[i].checked = true;
        } else {
            othercheck[i].checked = false;
        }
    }
}

function nobach() {
    var num = $('.checkboxs:checked').size();
    if (num > 0) {
        $("#myModal5 #nonum").val(num);
        $("#myModal5").modal();
    } else {
        toastr.error("请选中不盘点的资产！！");
    }
}

function movebach() {
    var num = $('.checkboxs:checked').size();
    if (num > 0) {
        $("#myModal4 #movenum").val(num);
        $("#myModal4").modal();
    } else {
        toastr.error("请选中需要转移的资产！！");
    }
}

/*高德地图*/
var map = new AMap.Map('map', {
    resizeEnable: true,
    zoom: 14,
    center: [116.480983, 40.0958]
});

var marker = new AMap.Marker({
    position: [116.480983, 39.989628],//marker所在的位置
    map: map//创建时直接赋予map属性
});

function showPosition(l,r) {
    map.setCenter([l,r]);
    map.setZoom(14);
    marker.setPosition([l,r]);
}