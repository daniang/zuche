package com.example.zuche.utils;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/1/19 10:52
 */

import com.example.zuche.exception.CustomizedErrorException;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BaseRequest implements Serializable {
    private static final int PAGE_SIZE = 10;
    @ApiModelProperty(
            value = "每页显示条数，默认10",
            example = "10"
    )
    private Integer size = 10;
    private Integer current = 1;
    @ApiModelProperty(
            value = "当前页，默认1",
            example = "1"
    )
    private Integer page = 1;

    public void setSize(Integer size) {
        if (size == null && size == 0) {
            this.size = 10;
        }

        if (size > 2000) {
            throw new CustomizedErrorException("请求条数超过限制，试试别的方法吧");
        } else {
            this.size = size;
        }
    }

    public Integer getSize() {
        return this.size;
    }

    public Integer getCurrent() {
        return this.current;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setCurrent(final Integer current) {
        this.current = current;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseRequest)) {
            return false;
        } else {
            BaseRequest other = (BaseRequest) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$size = this.getSize();
                    Object other$size = other.getSize();
                    if (this$size == null) {
                        if (other$size == null) {
                            break label47;
                        }
                    } else if (this$size.equals(other$size)) {
                        break label47;
                    }

                    return false;
                }

                Object this$current = this.getCurrent();
                Object other$current = other.getCurrent();
                if (this$current == null) {
                    if (other$current != null) {
                        return false;
                    }
                } else if (!this$current.equals(other$current)) {
                    return false;
                }

                Object this$page = this.getPage();
                Object other$page = other.getPage();
                if (this$page == null) {
                    if (other$page != null) {
                        return false;
                    }
                } else if (!this$page.equals(other$page)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseRequest;
    }

    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $size = this.getSize();
        result = result * 59 + ($size == null ? 43 : $size.hashCode());
        Object $current = this.getCurrent();
        result = result * 59 + ($current == null ? 43 : $current.hashCode());
        Object $page = this.getPage();
        result = result * 59 + ($page == null ? 43 : $page.hashCode());
        return result;
    }

    public String toString() {
        return "BaseRequest(size=" + this.getSize() + ", current=" + this.getCurrent() + ", page=" + this.getPage() + ")";
    }

    public BaseRequest() {
    }

    public BaseRequest(final Integer size, final Integer current, final Integer page) {
        this.size = size;
        this.current = current;
        this.page = page;
    }
}

