/**
 * Copyright 2016 sinosoft Co。 Ltd。
 * All Rights Reserved。
 */
package com.gupao.gp17190.pattern.prototype.old;

import com.gupao.gp17190.pattern.prototype.PM_RuleConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleConfigServiceImpl {

	public Map<String, Object> queryRoleList(PM_RuleConfig ruleConfig) {
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("policyType", ruleConfig.getPolicyType());//保单类型
		param.put("tranType", ruleConfig.getTranType());//交易类型
		param.put("ruleId", ruleConfig.getRuleId());//规则编号
		
		//分页
		param.put("startIndex", ruleConfig.getStartIndex());
		param.put("endIndex", ruleConfig.getEndIndex());
		
		int total = ruleConfigMapper.queryRuleConfigCounts(param);
		result.put("total", total);
		return result;
	}

}
