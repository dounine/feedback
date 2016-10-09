package dnn.common.exception;

import dnn.enums.RepExceptionType;

/**
 * Created by lgq on 16-10-6.
 */
public class RepException extends RuntimeException {
    private RepExceptionType type=RepExceptionType.UNDEFINE;

    private RepException repException;

    public RepException(RepExceptionType repExceptionType,String msg){
        super(msg);
        this.type = repExceptionType;
    }

    public RepException getRepException() {
        return repException;
    }

    public void setRepException(RepException repException) {
        this.repException = repException;
    }

    public RepExceptionType getType() {
        return type;
    }

    public void setType(RepExceptionType type) {
        this.type = type;
    }
}
