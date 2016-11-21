/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This transformer will take two lists as input and create a third one that will be the merge of the previous two.
 */
public class Merger {

	/**
	 * The method will merge the objects from the two lists creating a new one.
	 * 
	 * @param listFromSystemA
	 *            list from system A
	 * @param listFromSystemB
	 *            list from system B
	 * @return a list with the merged content of the to input lists
	 */
	List<Map<String, String>> mergeList(List<Map<String, String>> listFromSystemA, List<Map<String, String>> listFromSystemB) {
		List<Map<String, String>> mergedList = new ArrayList<Map<String, String>>();

		// Put all objects from A in the mergedList
		mergedList.addAll(listFromSystemA);

		// Add all objects from B to the mergedList
		mergedList.addAll(listFromSystemB);
		
		return mergedList;
	}
}