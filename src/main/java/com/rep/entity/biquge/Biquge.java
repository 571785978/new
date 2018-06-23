package com.rep.entity.biquge;

import com.rep.common.BaseEntity;
import com.rep.reptile.annotations.Next;
import com.rep.reptile.annotations.Reptile;

import java.util.List;

@Reptile
public class Biquge extends BaseEntity {

    private String getPath() {
        return("http://www.biqiku.com/xiaoshuodaquan/");
    }
    private String getRepository(){
        return "biQuGeFictionRepository";
    }

    @Next
    private List<Fiction> fictions;

    public List<Fiction> getFictions() {
        return fictions;
    }

    public void setFictions(List<Fiction> fictions) {
        this.fictions = fictions;
    }
}
