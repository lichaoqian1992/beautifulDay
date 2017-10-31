package com.manji.elastic.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Record;
import com.manji.elastic.model.Create;
import com.manji.elastic.model.ESAddress;

import net.sf.json.JSONObject;

public class WriteFileUtils {

	public static void writeFile(List<Record> list) {

		List<Record> newList = new ArrayList<Record>();

		for (int i = 0; i < list.size(); i++) {

			Record rec = list.get(i);
			String id = rec.getInt("id")+"";
			Record ad = new Record();

			ESAddress ea = new ESAddress();
			ea.set_index("goods");
			ea.set_type("goods");
			ea.set_id(id);

			ad.set("create", JSONObject.fromObject(ea).toString());
			rec.remove("remark");
			rec.remove("add_time");
			rec.remove("update_time");
			newList.add(ad);
			newList.add(rec);

		}

		StringBuffer sb = new StringBuffer();

		for (int j = 0; j < newList.size(); j++) {

			Record tempRec = newList.get(j);

			if (j != 0) {
				sb.append("\r\n");
			}

			sb.append(JSONObject.fromObject(tempRec.getColumns()));

		}

		try {
			File file = new File("D:/2.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());

			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(sb.toString());

			bw.close();

			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {
			String content = "sbbbbbbbbbbbbbbbbbbbbbb\r\nsaaaaaaaaaaaaaaaaaa";

			File file = new File("D:/2.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());

			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(content);

			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
