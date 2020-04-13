package life.chen.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在，请换个问题试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"请登陆后再操作！"),
    SYS_ERROR(2004,"服务器爆满了，稍后再试试吧妹妹"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NO_FOUND(2006,"回复的评论不存在"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"通知读取错误"),
    NOTIFICATION_NOT_FOUND(2009,"通知神秘失踪"),
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code = code;
    }
}
