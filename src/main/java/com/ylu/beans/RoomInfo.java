package com.ylu.beans;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roominfos")
public class RoomInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String error;
	@Embedded
	private com.ylu.beans.Data data;
    private Date time;

	public RoomInfo(String error, com.ylu.beans.Data data) {
		super();
		this.error = error;
		this.data = data;
		time = new Date();
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("人气：%s",data.getOnline()));
		sb.append("-");
		sb.append(String.format("体重：%s",data.getOwnerWeight()));
		sb.append("-");
		sb.append(String.format("关注：%s",data.getFansNum()));
		return sb.toString();
	}
}
