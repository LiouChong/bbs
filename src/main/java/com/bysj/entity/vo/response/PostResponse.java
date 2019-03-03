package com.bysj.entity.vo.response;


import com.bysj.common.request.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <p>
 * PostResponse 响应对象
 * </p>
 *
 * @author 刘冲
 * @since 2018-12-27
 */

public class PostResponse extends BaseEntity {
    /**
     * 帖子标题
     */
    @ApiModelProperty("帖子标题")
    private String title;
    /**
     * 帖子内容
     */
    @ApiModelProperty("帖子内容")
    private String info;
    /**
     * 作者id
     */
    @ApiModelProperty("作者名称")
    private String posterName;
    /**
     * 是否被推荐:1 被推荐,0 未被推荐
     */
    @ApiModelProperty("是否被推荐:1 被推荐,0 未被推荐")
    private Integer ifGood;
    /**
     * 是否被推荐的中文
     */
    @ApiModelProperty("是否被推荐的中文")
    private String ifGoodStr;
    /**
     * 板块名称
     */
    @ApiModelProperty("板块名称")
    private String plateName;
    /**
     * 回复数
     */
    @ApiModelProperty("回复数")
    private Integer replyCount;
    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer thumbupCount;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date gmtModify;
    /**
     * 阅读数
     */
    @ApiModelProperty("阅读数")
    private Integer readCount;
    /**
     * 文章的来源:1 原创,2 转载
     */
    @ApiModelProperty("文章的来源:1 原创,2 转载")
    private Integer articleFrom;

    @ApiModelProperty("文章的来源的中文")
    private String articleFromStr;

    /**
     * 文章的类型:1讨论帖,2 资源贴,3 求问贴
     */
    @ApiModelProperty("文章的类型:1 讨论帖,2 资源贴,3 求问贴")
    private Integer articleType;

    @ApiModelProperty("文章的类型的中文形式")
    private String articleTypeStr;


    public String getIfGoodStr() {
        return ifGoodStr;
    }

    public void setIfGoodStr(String ifGoodStr) {
        this.ifGoodStr = ifGoodStr;
    }

    public String getArticleFromStr() {
        return articleFromStr;
    }

    public void setArticleFromStr(String articleFromStr) {
        this.articleFromStr = articleFromStr;
    }

    public String getArticleTypeStr() {
        return articleTypeStr;
    }

    public void setArticleTypeStr(String articleTypeStr) {
        this.articleTypeStr = articleTypeStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public Integer getIfGood() {
        return ifGood;
    }

    public void setIfGood(Integer ifGood) {
        this.ifGood = ifGood;
    }

    public String getPlateName() {
        return plateName;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getThumbupCount() {
        return thumbupCount;
    }

    public void setThumbupCount(Integer thumbupCount) {
        this.thumbupCount = thumbupCount;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getArticleFrom() {
        return articleFrom;
    }

    public void setArticleFrom(Integer articleFrom) {
        this.articleFrom = articleFrom;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }
}