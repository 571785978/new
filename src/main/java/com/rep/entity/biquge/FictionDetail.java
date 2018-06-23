package com.rep.entity.biquge;

import com.rep.reptile.annotations.Selector;

import javax.persistence.*;

@Entity
@Table(name = "fiction")
public class FictionDetail{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)       //指定生成策略
    @Column(name="id")
    private Integer id;

    @Selector(valueType = String.class,value = "#info > p:nth-child(2) > a")
    @Column(name = "author")
    private String author;

    @Selector(valueType = String.class,value = "#info > p:nth-child(4) > a")
    @Column(name = "new_chapter")
    private String newChapter;

    @Selector(valueType = String.class,value = "#info > p:nth-child(4) > a",attr="href")
    @Column(name = "new_chapter_url")
    private String newChapterUrl;

    @Selector(valueType = String.class,value = "#wrapper .con_top a:nth-child(2)")
    @Column(name = "type_name")
    private String typeName;

    @Column(name = "name")
    @Selector(value = "#info > h1",valueType = String.class)
    private String name;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNewChapter() {
        return newChapter;
    }

    public void setNewChapter(String newChapter) {
        this.newChapter = newChapter;
    }

    public String getNewChapterUrl() {
        return newChapterUrl;
    }

    public void setNewChapterUrl(String newChapterUrl) {
        this.newChapterUrl = newChapterUrl;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
