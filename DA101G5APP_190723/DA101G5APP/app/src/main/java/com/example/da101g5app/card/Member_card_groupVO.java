package com.example.da101g5app.card;


public class Member_card_groupVO implements java.io.Serializable {

    private String member_card_group_id;
    private String teacher_card_group_id;
    private String member_id;
    private Integer expiration_num;
    private Integer new_card_num;
    private Integer spend_time;
    private String teacher_card_group_name;
    private String teacher_name;
    private Integer qty;

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }


    public String getTeacher_card_group_name() {
        return teacher_card_group_name;
    }

    public void setTeacher_card_group_name(String teacher_card_group_name) {
        this.teacher_card_group_name = teacher_card_group_name;
    }

    public String getMember_card_group_id() {
        return member_card_group_id;
    }
    public void setMember_card_group_id(String member_card_group_id) {
        this.member_card_group_id = member_card_group_id;
    }
    public String getTeacher_card_group_id() {
        return teacher_card_group_id;
    }
    public void setTeacher_card_group_id(String teacher_card_group_id) {
        this.teacher_card_group_id = teacher_card_group_id;
    }
    public String getMember_id() {
        return member_id;
    }
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public Integer getExpiration_num() {
        return expiration_num;
    }
    public void setExpiration_num(Integer expiration_num) {
        this.expiration_num = expiration_num;
    }
    public Integer getNew_card_num() {
        return new_card_num;
    }
    public void setNew_card_num(Integer new_card_num) {
        this.new_card_num = new_card_num;
    }
    public Integer getSpend_time() {
        return spend_time;
    }
    public void setSpend_time(Integer spend_time) {
        this.spend_time = spend_time;
    }
}
