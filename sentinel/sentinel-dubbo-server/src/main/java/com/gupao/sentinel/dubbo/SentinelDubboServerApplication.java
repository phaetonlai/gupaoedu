package com.gupao.sentinel.dubbo;

import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.gupao.sentinel.dubbo.config.DubboConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;

public class SentinelDubboServerApplication {

	public static void main(String[] args) throws Exception {
//		InitExecutor.doInit();
		initRules();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(DubboConfiguration.class);
		context.refresh();

		System.in.read();
	}

	private static void initRules() {
		FlowRule rule = new FlowRule();
		rule.setResource("com.gupao.dubbo.rpc.service.api.ILoginService:login(java.lang.String)");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setLimitApp("default");
		rule.setCount(30);
//		rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);

		FlowRuleManager.loadRules(Collections.singletonList(rule));
	}

}
