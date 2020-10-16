package com.darin.blog.dto;

public class PageParam {

    private Integer current;
    private Integer pages;
    private Integer elements;

    public PageParam(){}

    public PageParam(Integer current,Integer pages,Integer elements){
        this.current = current;
        this.pages = pages;
        this.elements = elements;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getElements() {
        return elements;
    }

    public void setElements(Integer elements) {
        this.elements = elements;
    }
}
