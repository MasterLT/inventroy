package pl.codeleak.sbt.enums;

/**
 * Created by Administrator on 2017/11/7.
 */
public enum RoleEnum {

    ADMIN(2,"超级管理员"),
    DEPTADMIN(1,"部门资产管理员");


    private int code;
    private String message;

    private RoleEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getNameByType(Integer code) {
        if (code != null) {
            for (RoleEnum loanStates : RoleEnum.values()) {
                if (loanStates.getCode() == code)
                    return loanStates.getMessage();
            }
        }
        return "";
    }
}
