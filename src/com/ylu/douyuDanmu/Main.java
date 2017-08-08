package com.ylu.douyuDanmu;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ylu.beans.Message;
import com.ylu.beans.Message.Type;
import com.ylu.douyuFormat.Logger;
import com.ylu.ui.DanmuTable;



public class Main {
	
	private static final int ROOM_LPL = 288016;
	private static final int ROOM_XZF = 222349;
	private static final int ROOM_LCK = 522423;
	private static final int ROOM_FG = 338281;
	private static final int ROOM_DOTA = 58428;
	private static final int ROOM_DOTA2 = 2339788;
	//弹幕池分组号，海量模式使用-9999
	private static final int groupId = -9999;
	
	public static void main(String[] args)
	{
/*		int local_room_Id;
		Pattern pattern = Pattern.compile("[0-9]{3,7}");

		if(args.length>0){
			Matcher matcher = pattern.matcher(args[0]);
			if(matcher.find()){
				local_room_Id = Integer.parseInt(args[0]);
			}else{
				Logger.v("Wrong Room Id!");
				local_room_Id = roomId;
			}
		}else{
			local_room_Id = roomId;
		}*/
		Collection<Integer> rids = new ArrayList<Integer>();
		rids.add(ROOM_LPL);
		rids.add(ROOM_XZF);
		rids.add(ROOM_LCK);
		rids.add(ROOM_FG);
		rids.add(ROOM_DOTA);
		rids.add(ROOM_DOTA2);
		DyCrawler crawler = new DyCrawlerImpl();
		//crawler.crawlRooms(rids);
		crawler.crawlRoom(ROOM_LPL);
		
	}

}
