package jdbc.Util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class jdbcUtil_CompositeQuery_Item {
	
	public static String get_aCondition_For_myDB(String columnName, String value) {
		
		String aCondition = null;
		
		if("ItemNo".equals(columnName) || "KindNo".equals(columnName) ||"ItemPrice".equals(columnName) ||
				"ItemState".equals(columnName) ||"WarrantyDate".equals(columnName))//Int用
		aCondition = columnName + "=" + value;
		else if ("ItemName".equals(columnName) || "ItemProdDescription".equals(columnName)) // String用varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("SoldTime".equals(columnName)||"LaunchedTime".equals(columnName))
			aCondition = columnName + "=" + "'"+ value +"'"; 
		
		return aCondition + " ";
	}
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			System.out.println("line29" + value);
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());
				
				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	
	//main測試一下
	public static void main(String[] args) {
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("itemNo", new String[] { "" });
		map.put("KindNo", new String[] { "" });
		map.put("ItemName", new String[] { "智慧" });
		map.put("ItemPrice", new String[] { "" });
		map.put("ItemState", new String[] { "" });
		map.put("SoldTime", new String[] { "" });
		map.put("LaunchedTime", new String[] { "" });
		map.put("WarrantyDate", new String[] { "" });
		map.put("ItemProdDescription", new String[] { "" });
		map.put("action", new String[] { "getXXX" });
		
		String finalSQL = "SELECT * FROM item "
				+ jdbcUtil_CompositeQuery_Item.get_WhereCondition(map)
				+"ORDER BY itemNo";
		System.out.println("測試finalSQL(by jdbcUtil.java) = "+finalSQL);
	}
}
