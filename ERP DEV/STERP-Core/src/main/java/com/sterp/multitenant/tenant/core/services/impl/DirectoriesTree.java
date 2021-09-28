package com.sterp.multitenant.tenant.core.services.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DirectoriesTree {
	static Map<String, Object> responseTree = new HashMap<String, Object>();

	static void RecursivePrint(File[] arr, int index, int level) {
		if (index == arr.length)
			return;
		for (int i = 0; i < level; i++)
			System.out.print("\t");
		if (arr[index].isFile()) {
			System.out.println(arr[index].getName());
			responseTree.put(Integer.toString(level), arr[index].getName());
		} else if (arr[index].isDirectory()) {
			System.out.println("[" + arr[index].getName() + "]");
			responseTree.put(Integer.toString(level), arr[index].getName());
			RecursivePrint(arr[index].listFiles(), 0, level + 1);
		}
		RecursivePrint(arr, ++index, level);
	}

	public static void getDirectoriesAndFiles(File file) {
		File maindir = file;

		if (maindir.exists() && maindir.isDirectory()) {
			File arr[] = maindir.listFiles();

			System.out.println("**********************************************");
			System.out.println("Files from main directory : " + maindir);
			System.out.println("**********************************************");
			RecursivePrint(arr, 0, 0);
			System.out.println(responseTree);
		}
	}
}