/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.transformers;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;

import com.google.common.collect.Lists;

/**
 * This transformer will take two lists as input and create a third one that will be the merge of the previous two.
 */
public class MergeAggregationStrategy implements AggregationStrategy {
	
	@Override
	public MuleEvent aggregate(AggregationContext context) throws MuleException {
		List<MuleEvent> muleEventsWithoutException = context.collectEventsWithoutExceptions();
		int muleEventsWithoutExceptionCount = muleEventsWithoutException.size();
		
		// no error should be in any of routes
		if (muleEventsWithoutExceptionCount != 2) {
			throw new IllegalArgumentException("There was an error in one or both routes.");
		}
		
		MuleEvent muleEvent = muleEventsWithoutException.get(0);
		MuleMessage muleMessage = muleEvent.getMessage();
		
		List<Map<String, String>> listA = getList(muleEventsWithoutException, 0);
		List<Map<String, String>> listB = getList(muleEventsWithoutException, 1);
		
		// events are ordered so the event index corresponds to the index of each route
		Merger merger = new Merger();
		List<Map<String, String>> mergedList = merger.mergeList(listA, listB);
		
		muleMessage.setPayload(mergedList.iterator());
		
		return new DefaultMuleEvent(muleMessage, muleEvent);
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, String>> getList(List<MuleEvent> events, int index) {
		Iterator<Map<String, String>> iterator;
		if (events.get(index).getMessage().getPayload() instanceof Collection){
			iterator = ((Collection<Map<String, String>>) events.get(index).getMessage().getPayload()).iterator();
		} else {
			iterator = (Iterator<Map<String, String>>) events.get(index).getMessage().getPayload();
		}
		return Lists.newArrayList(iterator);
	}

}