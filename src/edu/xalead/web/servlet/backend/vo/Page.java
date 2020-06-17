package edu.xalead.web.servlet.backend.vo;

import java.util.ArrayList;

public class Page {
    private int offset;//当前页索引
    private int total;//文章总数
    private int pageNumber;//每页文章数
    private ArrayList<ArrayList<String>> list = null;

    public int getCurPage(){
        return offset / pageNumber + 1;
    }

    public int getTotalPage(){
        return total % pageNumber ==0 ? total / pageNumber : total / pageNumber +1;
    }

    public ArrayList<ArrayList<String>> getList() {
        return list;
    }

    public void setList(ArrayList<ArrayList<String>> list) {
        this.list = list;
    }

    public int getFirst(){
        return 0;
    }

    public int getLast(){
        return total - (total % pageNumber == 0 ? pageNumber : total % pageNumber);
    }

    public int getPre(){
        return offset - pageNumber < 0 ? 0 : offset - pageNumber;
    }

    public int getNext(){
        return offset + pageNumber >= total ? offset : offset + pageNumber;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
