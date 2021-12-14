package com.jdc.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Check {
	
	private Map<String, List<String>> classRooms;
	
	public Check() {
		try {
			classRooms = new TreeMap<>();
			
			Files.walk(Path.of("outputs")).filter(a -> Files.isRegularFile(a))
				.forEach(file -> {
					try {
						var list = Files.lines(file).sorted().toList();
						classRooms.put(file.getFileName().toString(), list);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void check() {
		var rooms = new ArrayList<>(classRooms.keySet());
		
		for(var i =0; i < classRooms.size() - 1; i ++) {
			System.out.println("======================================");
			System.out.println(">>> %s".formatted(rooms.get(i)));
			System.out.println("======================================");
			
			var students = classRooms.get(rooms.get(i));
			
			for(var student : students) {
				var target = i + 1;
				var list = new ArrayList<>();
				
				while(target < classRooms.size()) {
					
					var targetRoom = rooms.get(target);
					var studentsInTargetRoom = classRooms.get(targetRoom);
					
					if(studentsInTargetRoom.contains(student)) {
						list.add(targetRoom);
					}

					target ++;
				}
				
				if(!list.isEmpty()) {
					System.out.println("%s is attend in %s".formatted(student, list));
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		new Check().check();
	}

}
