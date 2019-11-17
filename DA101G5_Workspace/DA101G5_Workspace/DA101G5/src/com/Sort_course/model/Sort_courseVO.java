package com.Sort_course.model;


public class Sort_courseVO implements java.io.Serializable{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sort_course == null) ? 0 : sort_course.hashCode());
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
		Sort_courseVO other = (Sort_courseVO) obj;
		if (sort_course == null) {
			if (other.sort_course != null)
				return false;
		} else if (!sort_course.equals(other.sort_course))
			return false;
		return true;
	}
	private String sort_course_id;
	private String sort_course;
	
	public Sort_courseVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSort_course_id() {
		return sort_course_id;
	}
	public void setSort_course_id(String sort_course_id) {
		this.sort_course_id = sort_course_id;
	}
	public String getSort_course() {
		return sort_course;
	}
	public void setSort_course(String sort_course) {
		this.sort_course = sort_course;
	}

}
