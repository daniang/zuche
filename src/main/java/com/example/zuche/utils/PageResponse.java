package com.example.zuche.utils;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/1/19 10:55
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> {
    private PageResponse.PageInfo pageInfo;
    private List<T> pageData;
    private String info;
    private Integer status;

    public static PageResponse empty() {
        PageResponse response = new PageResponse();
        response.setPageData(new ArrayList());
        return response;
    }

    public PageResponse.PageInfo getPageInfo() {
        return this.pageInfo;
    }

    public List<T> getPageData() {
        return this.pageData;
    }

    public String getInfo() {
        return this.info;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setPageInfo(final PageResponse.PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setPageData(final List<T> pageData) {
        this.pageData = pageData;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageResponse)) {
            return false;
        } else {
            PageResponse<?> other = (PageResponse) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59:
                {
                    Object this$pageInfo = this.getPageInfo();
                    Object other$pageInfo = other.getPageInfo();
                    if (this$pageInfo == null) {
                        if (other$pageInfo == null) {
                            break label59;
                        }
                    } else if (this$pageInfo.equals(other$pageInfo)) {
                        break label59;
                    }

                    return false;
                }

                Object this$pageData = this.getPageData();
                Object other$pageData = other.getPageData();
                if (this$pageData == null) {
                    if (other$pageData != null) {
                        return false;
                    }
                } else if (!this$pageData.equals(other$pageData)) {
                    return false;
                }

                Object this$info = this.getInfo();
                Object other$info = other.getInfo();
                if (this$info == null) {
                    if (other$info != null) {
                        return false;
                    }
                } else if (!this$info.equals(other$info)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageResponse;
    }

    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $pageInfo = this.getPageInfo();
        result = result * 59 + ($pageInfo == null ? 43 : $pageInfo.hashCode());
        Object $pageData = this.getPageData();
        result = result * 59 + ($pageData == null ? 43 : $pageData.hashCode());
        Object $info = this.getInfo();
        result = result * 59 + ($info == null ? 43 : $info.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    public String toString() {
        return "PageResponse(pageInfo=" + this.getPageInfo() + ", pageData=" + this.getPageData() + ", info=" + this.getInfo() + ", status=" + this.getStatus() + ")";
    }

    public PageResponse() {
    }

    public PageResponse(final PageResponse.PageInfo pageInfo, final List<T> pageData, final String info, final Integer status) {
        this.pageInfo = pageInfo;
        this.pageData = pageData;
        this.info = info;
        this.status = status;
    }

    public static class PageInfo {
        private long size;
        private long page;
        private long total;
        private long totalPage;

        public long getSize() {
            return this.size;
        }

        public long getPage() {
            return this.page;
        }

        public long getTotal() {
            return this.total;
        }

        public long getTotalPage() {
            return this.totalPage;
        }

        public void setSize(final long size) {
            this.size = size;
        }

        public void setPage(final long page) {
            this.page = page;
        }

        public void setTotal(final long total) {
            this.total = total;
        }

        public void setTotalPage(final long totalPage) {
            this.totalPage = totalPage;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof PageResponse.PageInfo)) {
                return false;
            } else {
                PageResponse.PageInfo other = (PageResponse.PageInfo) o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.getSize() != other.getSize()) {
                    return false;
                } else if (this.getPage() != other.getPage()) {
                    return false;
                } else if (this.getTotal() != other.getTotal()) {
                    return false;
                } else {
                    return this.getTotalPage() == other.getTotalPage();
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof PageResponse.PageInfo;
        }

        public int hashCode() {
//            int PRIME = true;
            int result = 1;
            long $size = this.getSize();
            result = result * 59 + (int) ($size >>> 32 ^ $size);
            long $page = this.getPage();
            result = result * 59 + (int) ($page >>> 32 ^ $page);
            long $total = this.getTotal();
            result = result * 59 + (int) ($total >>> 32 ^ $total);
            long $totalPage = this.getTotalPage();
            result = result * 59 + (int) ($totalPage >>> 32 ^ $totalPage);
            return result;
        }

        public String toString() {
            return "PageResponse.PageInfo(size=" + this.getSize() + ", page=" + this.getPage() + ", total=" + this.getTotal() + ", totalPage=" + this.getTotalPage() + ")";
        }

        public PageInfo() {
        }

        public PageInfo(final long size, final long page, final long total, final long totalPage) {
            this.size = size;
            this.page = page;
            this.total = total;
            this.totalPage = totalPage;
        }
    }
}
