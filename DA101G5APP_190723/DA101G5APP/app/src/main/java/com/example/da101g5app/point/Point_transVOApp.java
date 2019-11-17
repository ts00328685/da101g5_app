package com.example.da101g5app.point;

import java.sql.Timestamp;

public class Point_transVOApp extends Point_transVO implements java.io.Serializable{

//	private String point_trans_id;
//	private String member_id;
//	private Double point_record;
//	private Date transdate;


    private Timestamp transdate_app;

    public Timestamp getTransdate_app() {
        return transdate_app;
    }

    public void setTransdate_app(Timestamp transdate_app) {
        this.transdate_app = transdate_app;
    }





}
