package com.tigerjay.eurekadatabase.entity;

import java.io.Serializable;
/**
 * <p>
 * 动态路由配置
 * </p>
 *
 * @author tigerJay
 * @since 2019-09-17
 */
public class DynamicProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    private String path;

    private String serviceId;

    private String url;

    private Boolean stripPrefix;

    private Boolean retryable;

    private Boolean enabled;

    private String description;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getStripPrefix() {
        return stripPrefix;
    }

    public void setStripPrefix(Boolean stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
