package dnn.enums;

/**
 * Created by ike on 16-9-10.
 * 工单确认流程状态
 */

public enum OperatorStatus {
    INIT(0),//初始状态
    MANAGERCONFIRM(1),//管理员工单修改确认
    CUSTOMCONFIRM(2),//客户工单修改确认
    CUSTOMFINALCONFIRM(3),//客户修改最终确认
    CHARGECONFIRM(4),//费用确认
    CHARGENOT(5),//费用未确认
    HANDLECONFIRM(6);//管理员最终受理确认
    private int code;

    OperatorStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
