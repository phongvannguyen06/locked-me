package com.hcl.locked_me;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Proto {

	public static void main(String[] args) throws IOException {
//		try (Stream<Path> walk = Files.list(Paths.get("D:\\crystal-thai\\dist\\crystal-thai"))) {
//
//			List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
//
//			result.forEach(System.out::println);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		
		// "C:/Program Files/Bitdefender/Bitdefender VPN"
        Stream<Path> files = Files.list(Paths.get(path));
        
        List<String> result = files.map(x -> x.getFileName().toString()).collect(Collectors.toList());
        Collections.sort(result, String.CASE_INSENSITIVE_ORDER.reversed());
        for (String name : result) {
        	System.out.println(name);
        }
        
//        files.forEach(System.out::println);
         
        files.close();
	}

}
