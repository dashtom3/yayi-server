package com.yayiabc.common.utils;

/**
 * Created by XiaoJiang01 on 2017/5/12.
 */
public class Page {
    private Integer numberPerPage;//总页数n'n
    private Integer currentPage;//当前页
    private Integer currentNumber;//页面显示条数

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Page() {
    }

    public Page(Integer numberPerPage, Integer currentPage, Integer totalNumber, Integer totalPage) {

        this.numberPerPage = numberPerPage;
        this.currentPage = currentPage;
    }

    public Integer getNumberPerPage() {

        return numberPerPage;
    }

    public void setNumberPerPage(Integer numberPerPage) {
        this.numberPerPage = numberPerPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        if (numberPerPage != 0){
            currentNumber = (currentPage-1)*numberPerPage;
        }
    }
}
