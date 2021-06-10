package com.bumao.model.yuquesdk.exception;

import com.google.common.base.Joiner;
import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

@Data
public class YuqueException extends Exception {
    private static final long serialVersionUID = 2214381471513460742L;
    /* 返回的http code */
    private Integer httpCode;
    /* 返回的http 响应头 那一行*/
    private String httpStatusLine;
    /* 返回的http body */
    private String origContent;
    /*自定义error*/
    private String customErrorMsg;
    /*语雀返回的原始json->message*/
    private String origMsg;
    /*语雀返回的原始json->code*/
    private String origCode;
    /*语雀返回的原始json->status*/
    private Integer origStatus;

    public YuqueException(String errMsg) {
        super(errMsg);
        this.customErrorMsg = errMsg;
    }

    public YuqueException(Integer httpCode, String httpStatusLine, String origContent, String origMsg, String origCode, Integer origStatus){
        super(origMsg);
        this.httpCode = httpCode;
        this.httpStatusLine = httpStatusLine;
        this.origContent = origContent;
        this.origCode = origCode;
        this.origMsg = origMsg;
        this.origStatus = origStatus;
    }

    public YuqueException(String errMsg, Throwable tr) {
        super(errMsg, tr);
        this.customErrorMsg = customErrorMsg;
    }
    private YuqueException(YuqueException.Builder builder) {
        super(builder.buildErrorMsg());
        this.httpCode = builder.httpCode;
        this.httpStatusLine = builder.httpStatusLine;
        this.origContent = builder.origContent;
        this.customErrorMsg = builder.customErrorMsg;
    }

    public static final class Builder {
        /* 返回的http code */
        private Integer httpCode;
        /* 返回的http 响应头 那一行*/
        private String httpStatusLine;
        /* 返回的http body */
        private String origContent;
        /*自定义error*/
        private String customErrorMsg;
        /*语雀返回的原始json->message*/
        private String origMsg;
        /*语雀返回的原始json->code*/
        private String origCode;
        /*语雀返回的原始json->status*/
        private Integer origStatus;

        public Builder() {
        }

        public YuqueException.Builder setHttpCode(Integer httpCode) {
            this.httpCode = httpCode;
            return this;
        }

        public YuqueException.Builder setHttpStatusLine(String httpStatusLine) {
            this.httpStatusLine = httpStatusLine;
            return this;
        }

        public YuqueException.Builder setOrigContent(String origContent) {
            this.origContent = origContent;
            return this;
        }

        public YuqueException.Builder setCustomErrorMsg(String customErrorMsg) {
            this.customErrorMsg = customErrorMsg;
            return this;
        }

        public YuqueException.Builder setOrigMsg(String origMsg){
            this.origMsg = origMsg;
            return this;
        }

        public YuqueException.Builder setOrigCode(String origCode){
            this.origCode = origCode;
            return this;
        }

        public YuqueException.Builder setOrigStatus(Integer origStatus){
            this.origStatus = origStatus;
            return this;
        }

        public YuqueException build() {
            return new YuqueException(this);
        }

        public String buildErrorMsg() {
            return Joiner.on("，").skipNulls().join(
                    this.httpCode == null ? null : String.format("返回代码：[%s]", this.httpCode),
                    this.httpStatusLine == null ? null : String.format("返回信息：[%s]", this.httpStatusLine),
                    this.origContent == null ? null : String.format("原始内容：[%s]",this.origContent),
                    this.customErrorMsg == null ? null : String.format("自定义错误：[%s]",this.customErrorMsg),
                    this.origMsg == null ? null : String.format("原始json->message：[%s]",this.origMsg),
                    this.origCode == null ? null : String.format("原始json->code：[%s]",this.origCode),
                    this.origStatus == null ? null : String.format("原始json->status：[%s]",this.origStatus)
            );
        }
    }
}
