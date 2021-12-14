package com.jdc.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Collect {

	public static void main(String[] args) {
		
		var input = Path.of("inputs");
		var output = Path.of("outputs");
		
		try {
			var list = Files.walk(input)
					.filter(a -> Files.isRegularFile(a))
					.toList();
			
			for(var file : list) {
				var names = Files.lines(file).filter(a -> !a.trim().isEmpty())
					.filter(a -> !a.contains("points"))
					.filter(a -> !a.contains("ssigned"))
					.toList();
				
				var outFile = output.resolve(file.getFileName());
				Files.write(outFile, names, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
