package com.ylu.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "danmus")
public class Danmu extends DyMessage{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String groupId;
	private String roomId;
	private String userId;
	private String userNickName;
	private String userLevel;
	//Fan badge
	private String bnn;
	//Content - ChatMsg
	private String txt;
	//Unique Id -ChatMsg
	private String cid;
	//TODO mysql store date
	private Long mtime;

	private Danmu(DyType type, String gid, String rid, String uid, String nn,String bnn, String txt, String cid, String level) {
		super();
		this.groupId = gid;
		this.roomId = rid;
		this.userId = uid;
		this.userNickName = nn;
		this.bnn = bnn;
		this.txt = txt;
		this.cid = cid;
		this.userLevel = level;
		this.mtime = new Date().getTime();
	}

	public static class Builder{
		private String gid;
		private String rid;
		private String uid;
		private String nn;
		private String bnn;
		private String txt;
		private String cid;
		private String level;
		
		public Builder metaData(Map<String,Object> map){
			try{
				nn =(String) map.get("nn");			
				gid =(String) map.get("gid");
				rid =(String) map.get("rid");
				uid =(String) map.get("uid");
				bnn =(String) map.get("bnn");
				txt =(String) map.get("txt");
				cid =(String) map.get("cid");
				level =(String) map.get("level");
				return this;
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public Builder gid(String gid){
			this.gid = gid;
			return this;
		}
		
		public Builder rid(String rid){
			this.rid = rid;
			return this;
		}
		
		public Builder uid(String uid){
			this.uid = uid;
			return this;
		}
		
		public Builder nn(String nn){
			this.nn = nn;
			return this;
		}
		
		public Builder bnn(String bnn){
			this.bnn = bnn;
			return this;
		}
		
		public Builder txt(String txt){
			this.txt = txt;
			return this;
		}
		
		public Builder cid(String cid){
			this.cid = cid;
			return this;
		}
		
		public Builder level(String level){
			this.level = level;
			return this;
		}
		
		public Danmu build(){
			return new Danmu(DyType.Danmu, gid, rid, uid, nn, bnn, txt, cid, level); 
		}

	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(roomId);
		sb.append("|");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(mtime);
		sb.append(dateString);
		sb.append("|");
		sb.append(getType().name());
		sb.append("|");
		sb.append(userNickName);
		sb.append("|");
		sb.append(txt);
		
		return sb.toString();
	}

	@Override
	public DyType getType() {
		return DyType.Danmu;
	}

}
