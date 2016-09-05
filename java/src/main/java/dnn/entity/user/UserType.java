package dnn.entity.user;

/**
 * Created by huanghuanlai on 16/9/4.
 */
public enum UserType {
    MANAGER(0),CUSTOM(1);
    private int value;

    UserType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
