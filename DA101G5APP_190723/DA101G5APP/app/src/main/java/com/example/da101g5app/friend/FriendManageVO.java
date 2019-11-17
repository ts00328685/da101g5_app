package com.example.da101g5app.friend;


import java.io.Serializable;
import java.sql.Date;

public class FriendManageVO implements Serializable{
    private String friend_member_id;
    private String friend_member_fid;
    private Date friend_time;
    private Integer friend_status;

    public FriendManageVO() {}

    public String getFriend_member_id() {
        return friend_member_id;
    }
    public void setFriend_member_id(String friend_member_id) {
        this.friend_member_id = friend_member_id;
    }
    public String getFriend_member_fid() {
        return friend_member_fid;
    }
    public void setFriend_member_fid(String friend_member_fid) {
        this.friend_member_fid = friend_member_fid;
    }
    public Date getFriend_time() {
        return friend_time;
    }
    public void setFriend_time(Date friend_time) {
        this.friend_time = friend_time;
    }
    public Integer getFriend_status() {
        return friend_status;
    }
    public void setFriend_status(Integer friend_status) {
        this.friend_status = friend_status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((friend_member_fid == null) ? 0 : friend_member_fid.hashCode());
        result = prime * result + ((friend_member_id == null) ? 0 : friend_member_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FriendManageVO other = (FriendManageVO) obj;
        if (friend_member_fid == null) {
            if (other.friend_member_fid != null)
                return false;
        } else if (!friend_member_fid.equals(other.friend_member_fid))
            return false;
        if (friend_member_id == null) {
            if (other.friend_member_id != null)
                return false;
        } else if (!friend_member_id.equals(other.friend_member_id))
            return false;
        return true;
    }
}
