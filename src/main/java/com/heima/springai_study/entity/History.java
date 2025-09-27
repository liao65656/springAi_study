package com.heima.springai_study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("sys_ai_history")
public class History implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value="id",type= IdType.AUTO)
    private int id;

    @Schema(title="聊天时间")
    @TableField("datetime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime;

    @Schema(title="聊天内容")
    @TableField("content")
    private String content;

    @Schema(title="聊天角色")
    @TableField("role")
    private String role;

    @Schema(title="会话ID")
    @TableField("session_id")
    private Long sessionId;
    
    // 显式添加所有 getter 和 setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}