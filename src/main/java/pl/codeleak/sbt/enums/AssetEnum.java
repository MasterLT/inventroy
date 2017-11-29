package pl.codeleak.sbt.enums;

/**
 * Created by Administrator on 2017/11/7.
 */
public enum AssetEnum {

    FINIDH(1,"已完成"),
    UN(0,"盘点审核中"),
    INVENTORY(-1,"未盘点"),
    NOINVENTORY(-2,"不盘点");


    private int code;
    private String message;

    private AssetEnum(int code, String message) {
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
            for (AssetEnum loanStates : AssetEnum.values()) {
                if (loanStates.getCode() == code)
                    return loanStates.getMessage();
            }
        }
        return "";
    }
}
