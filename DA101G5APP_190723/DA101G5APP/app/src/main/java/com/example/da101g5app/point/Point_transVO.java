package com.example.da101g5app.point;
import java.sql.Date;

public class Point_transVO implements java.io.Serializable{

    private String point_trans_id;
    private String member_id;
    private Double point_record;
    private Date transdate;

    public String getPoint_trans_id() {
        return point_trans_id;
    }
    public void setPoint_trans_id(String point_trans_id) {
        this.point_trans_id = point_trans_id;
    }
    public String getMember_id() {
        return member_id;
    }
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public Double getPoint_record() {
        return point_record;
    }
    public void setPoint_record(Double point_record) {
        this.point_record = point_record;
    }
    public Date getTransdate() {
        return transdate;
    }
    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }


}
