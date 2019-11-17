package com.example.da101g5app.commodity;

import java.sql.Timestamp;

public class CommodityVO implements java.io.Serializable {

    private String com_id;
    private String member_id;
    private String com_description;
    private byte[] com_pic;
    private String com_download;
    private Integer com_price;
    private Timestamp trandate;
    private Integer com_status;
    private Double e_score;
    private Integer e_pol_statistics;
    private String e_des;

    public String getCom_id() {
        return com_id;
    }
    public void setCom_id(String com_id) {
        this.com_id = com_id;
    }
    public String getMember_id() {
        return member_id;
    }
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public String getCom_description() {
        return com_description;
    }
    public void setCom_description(String com_description) {
        this.com_description = com_description;
    }
    public byte[] getCom_pic() {
        return com_pic;
    }
    public void setCom_pic(byte[] com_pic) {
        this.com_pic = com_pic;
    }
    public String getCom_download() {
        return com_download;
    }
    public void setCom_download(String com_download) {
        this.com_download = com_download;
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
    public Integer getCom_status() {
        return com_status;
    }
    public void setCom_status(Integer com_status) {
        this.com_status = com_status;
    }
    public Double getE_score() {
        return e_score;
    }
    public void setE_score(Double e_score) {
        this.e_score = e_score;
    }
    public Integer getE_pol_statistics() {
        return e_pol_statistics;
    }
    public void setE_pol_statistics(Integer e_pol_statistics) {
        this.e_pol_statistics = e_pol_statistics;
    }
    public String getE_des() {
        return e_des;
    }
    public void setE_des(String e_des) {
        this.e_des = e_des;
    }


}
