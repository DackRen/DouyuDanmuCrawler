package com.ylu.demoApp;

import com.ylu.beans.Danmu;
import com.ylu.beans.DyMessage;
import com.ylu.beans.RoomInfo;
import com.ylu.dao.DanmuRepository;
import com.ylu.dao.RoomInfoDAO;
import com.ylu.douyuClient.DyCrawler;
import com.ylu.douyuClient.DyCrawlerImpl;
import com.ylu.douyuClient.DyMessageListener;
import com.ylu.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DyDanmuMain implements CommandLineRunner{

	private static final int ROOM_LPL = 288016;
	private static final int ROOM_DYS = 154537;
	private static final int ROOM_LCK = 522423;
	private static final int ROOM_JORKER = 29;
	private static final int ROOM_55KAI = 138286;
	private static final int ROOM_ZDX = 688;
	private static final int ROOM_6324 = 6324;
	private static final int ROOM_AC= 96291;
	private static boolean storage = true;

	@Autowired
	private DanmuRepository danmuRepository;
	@Autowired
	private RoomInfoDAO roomInfoDAO;

	@Override
	public void run(String... args) throws Exception {
		if(args!=null && args.length == 1 && "noDatabase".equals(args[0])){
			storage = false;
		}else if(args!= null && args.length>0){
			System.out.println("Invalid Command!");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("================================================");
		System.out.println("Please input the douyu room id!");
		System.out.println("Example 1: watch 6324");
		System.out.println("Example 2: watch 6324 138286 288016");
		System.out.println("================================================");
		final DyCrawler crawler = new DyCrawlerImpl();
		
		while(true){
			String line = scanner.nextLine();
			if(line.equals("exit")){
				break;
			}
			Collection<Integer> rids = new ArrayList<>();
			try {
				rids = parseCommand(line);
			} catch (Exception e) {
				Logger.v(e.getMessage());
			}

			crawler.crawlRooms(rids, new DyMessageListener() {
				public void onReceiveRoomInfo(RoomInfo info) {
					if(storage){
						roomInfoDAO.save(info);
					}
					Logger.v(info.toString());
				}
				
				public void onReceiveMessage(DyMessage dyMessage) {
					switch (dyMessage.getType()) {
					case Danmu:
						Logger.v((dyMessage).toString());
						if(storage){
							danmuRepository.save((Danmu)dyMessage);
						}
						break;
					case Gift:
						//Logger.v(((Gift)dyMessage).toString());
						break;
					default:
						break;
					}
				}
				
				public void onException(Exception e) {
					//TODO
				}

				public void onReceiveError(int roomId) {
					crawler.stopCrawling(roomId);
				}
			});
			
		}
		scanner.close();
	}

	private static boolean checkRoomId(String rid){
		Pattern pattern = Pattern.compile("[0-9]{2,7}");

		Matcher matcher = pattern.matcher(rid);
		return matcher.find();
	}

	private static Collection<Integer> parseCommand(String line) throws Exception{
		String[] strs = line.split(" ");
		Collection<Integer> rids = new ArrayList<>();
		if("watch".equals(strs[0])){
			for(int i=1;i<strs.length;i++){
				if(checkRoomId(strs[i])){
					rids.add(Integer.parseInt(strs[i]));
				}else{
					throw new Exception("Invalid command "+ strs[i]);
				}
			}

			return rids;
		}else if("testAll".equals(strs[0])){
			rids.add(ROOM_AC);
			rids.add(ROOM_DYS);
			rids.add(ROOM_JORKER);
			rids.add(ROOM_55KAI);
			rids.add(ROOM_6324);
			return rids;
		}else{
			throw new Exception("Invalid command "+ strs[0]);
		}
	}
}
