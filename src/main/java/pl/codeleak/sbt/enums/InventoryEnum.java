package pl.codeleak.sbt.enums;

/**
 * Created by Administrator on 2017/11/7.
 */
public enum InventoryEnum {

    FINISH(1,"已完成"),
    INVENTORY(0,"盘点中");


    private int code;
    private String message;

    private InventoryEnum(int code, String message) {
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
            for (InventoryEnum loanStates : InventoryEnum.values()) {
                if (loanStates.getCode() == code)
                    return loanStates.getMessage();
            }
        }
        return "";
    }
}
