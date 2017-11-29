package pl.codeleak.sbt.enums;

/**
 * Created by Administrator on 2017/11/7.
 */
public enum AssetStateEnum {

    USING(1,"使用中"),
    FIXING(2,"维修中"),
    BORROEWING(3,"借用中"),
    SAVE(4,"库存"),
    BAD(5,"报废"),
    EMPTY(6,"闲置");


    private int code;
    private String message;

    private AssetStateEnum(int code, String message) {
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
            for (AssetStateEnum loanStates : AssetStateEnum.values()) {
                if (loanStates.getCode() == code)
                    return loanStates.getMessage();
            }
        }
        return "";
    }
}
