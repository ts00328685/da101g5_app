package com.example.da101g5app.commodity;

import java.sql.Timestamp;

public class SubscribeVO implements java.io.Serializable {

    private String sub_id;
    private String com_id;
    private String b_member_id;
    private String s_member_id;
    private Integer com_price;
    private Timestamp trandate;
    private Integer buyer_evaluation_score;

    public String getSub_id() {
        return sub_id;
    }
    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }
    public String getCom_id() {
        return com_id;
    }
    public void setCom_id(String com_id) {
        this.com_id = com_id;
    }
    public String getB_member_id() {
        return b_member_id;
    }
    public void setB_member_id(String b_member_id) {
        this.b_member_id = b_member_id;
    }
    public String getS_member_id() {
        return s_member_id;
    }
    public void setS_member_id(String s_member_id) {
        this.s_member_id = s_member_id;
    }
    public Integer getCom_price() {
        return com_price;
    }
    public void setCom_price(Integer com_price) {
        this.com_price = com_price;
    }
    public Timestamp getTrandate() {
        return trandate;
    }
    public void setTrandate(Timestamp trandate) {
        this.trandate = trandate;
    }
    public Integer getBuyer_evaluation_score() {
        return buyer_evaluation_score;
    }
    public void setBuyer_evaluation_score(Integer buyer_evaluation_score) {
        this.buyer_evaluation_score = buyer_evaluation_score;
    }
}
