package com.gupao.gp17190.pattern.prototype.optimized;

import com.gupao.gp17190.pattern.prototype.PM_RuleConfig;
import jdk.nashorn.internal.parser.JSONParser;
import sun.plugin.javascript.JSObject;

import java.util.HashMap;
import java.util.Map;

public class OptimizedService {
    public Map<String, Object> queryRoleList(PM_RuleConfig ruleConfig) {
        int total = ruleConfigMapper.queryRuleConfigCounts(param);
        ruleConfig.setTotal(total + "");
        Map<String,Object> param = new org.apache.commons.beanutils.BeanMap(ruleConfig);

        Map<String,Object> result = new HashMap<String, Object>();
        result.put("param", param);
        result.put("total", total);
        return result;
    }


}
