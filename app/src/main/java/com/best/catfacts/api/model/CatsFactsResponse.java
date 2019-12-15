package com.best.catfacts.api.model;

import java.util.List;

public class CatsFactsResponse {

    private Long currentPage;
    private List<Fact> data = null;
    private String firstPageUrl;
    private Long from;
    private Long lastPage;
    private String lastPageUrl;
    private String nextPageUrl;
    private String path;
    private String perPage;
    private String prevPageUrl;
    private Long to;
    private Long total;

    /**
     * No args constructor for use in serialization
     */
    public CatsFactsResponse() {
    }

    /**
     * @param path
     * @param lastPageUrl
     * @param total
     * @param firstPageUrl
     * @param nextPageUrl
     * @param perPage
     * @param data
     * @param lastPage
     * @param from
     * @param to
     * @param currentPage
     * @param prevPageUrl
     */
    public CatsFactsResponse(Long currentPage, List<Fact> data, String firstPageUrl, Long from, Long lastPage, String lastPageUrl, String nextPageUrl, String path, String perPage, String prevPageUrl, Long to, Long total) {
        super();
        this.currentPage = currentPage;
        this.data = data;
        this.firstPageUrl = firstPageUrl;
        this.from = from;
        this.lastPage = lastPage;
        this.lastPageUrl = lastPageUrl;
        this.nextPageUrl = nextPageUrl;
        this.path = path;
        this.perPage = perPage;
        this.prevPageUrl = prevPageUrl;
        this.to = to;
        this.total = total;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public List<Fact> getData() {
        return data;
    }

    public void setData(List<Fact> data) {
        this.data = data;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getLastPage() {
        return lastPage;
    }

    public void setLastPage(Long lastPage) {
        this.lastPage = lastPage;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
