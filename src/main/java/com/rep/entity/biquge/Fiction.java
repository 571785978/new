package com.rep.entity.biquge;

import com.rep.reptile.annotations.Href;
import com.rep.reptile.annotations.Selector;

public class Fiction {

    @Selector(value = "#main > div:nth-child(1) > ul > li > a", valueType = String.class, attr = "href")
    private String href;

    @Href(hrefSelector = "#main > div:nth-child(1) > ul > li > a",attr = "href")
    private FictionDetail fictionDetail;

    private String getUrl(){
        return "http://www.biqiku.com";
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public FictionDetail getFictionDetail() {
        return fictionDetail;
    }

    public void setFictionDetail(FictionDetail fictionDetail) {
        this.fictionDetail = fictionDetail;
    }
}