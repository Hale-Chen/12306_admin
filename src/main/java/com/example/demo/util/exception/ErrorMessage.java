package com.example.demo.util.exception;

/**
 * @ClassName ErrorMessage
 * @Description TODO
 * @Author FeiChen
 * @Date 2018/6/5 16:49
 **/
public class ErrorMessage {

    public static final String RESPONSE_SUCCESS = "成功";
    public static final String UNKNOWN_ERROR = "未知错误";
    public static final String DATA_REPEAT_ERROR = "已经存在该条记录";
    public static final String ADMIN_ERROR = "该用户不是管理员";
    public static final String GET_VISITOR_INFO_ERROR = "获取访客信息出错";
    public static final String GET_SUGGESTION_INFO_ERROR = "获取回访意见出错";
    public static final String DO_DOWNLOAD_ERROR = "下载文件出错";
    public static final String NO_DATA_RETURN = "暂无数据返回";
    public static final String REQUEST_IO_ERROR = "获取请求参数出错";
    public static final String GET_WORKCODE_ERROR = "获取工号或月份错误";
    public static final String SEND_QW_MSG_ERROR = "发送企微消息出现异常";
    public static final String SEND_PHONE_MSG_ERROR = "发送手机短信出现异常";

    public static final String INPUT_PARAM_ERROR = "参数传入错误";
    public static final String DATA_EXIST_ERROR = "该类型已经存在，请选择其他类型";

    public static final String GENERATE_QRCODE_ERROR = "生成二维码异常";
    public static final String INSERT_FAIL_ERROR ="插入失败" ;
    public static final String UPDATE_FAIL_ERROR ="修改信息出错" ;
    public static final String DELETE_FAILE_ERROR ="删除失败" ;

    public static final String GET_NO_DATA_FROM_DB_ERROR = "从数据库查询不到记录";

    public static final String NO_DELETE_ERROR="该记录已被登记，暂时无法删除";

    public static final String WORKCODE_IS_NULL = "工号不能为空";

    public static final String IN_WHITE_LIST = "无需打卡";

    public static  final String GET_DEPARTMENT_ERROR = "获取用户部门失败";

    public static final String NOT_IN_TIME = "未到打卡时间";

}
