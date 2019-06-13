package cn.ctoedu.miaosha.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
	
	public static final String MIAOSHA_QUEUE = "miaosha.queue";
	public static final String DIRECT_QUEUE = "queue";
	public static final String TOPIC_QUEUE1 = "topic.queue1";
	public static final String TOPIC_QUEUE2 = "topic.queue2";
	public static final String HEADER_QUEUE = "header.queue";
	public static final String DIRECT_EXCHANGE = "directExchage";
	public static final String TOPIC_EXCHANGE = "topicExchage";
	public static final String FANOUT_EXCHANGE = "fanoutxchage";
	public static final String HEADERS_EXCHANGE = "headersExchage";
	
	/**
	 * Direct模式 交换机Exchange
	 * 按路由键先匹配，再投送：队列的bindingKey = 消息的路由键
	 * */
	//1、队列
	@Bean
	public Queue queue() {
		return new Queue(DIRECT_QUEUE, true);
	}
	//2、交换机
	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange(DIRECT_EXCHANGE);
	}
	//3、队列与交换机绑定
	@Bean
	public Binding directBinding(){
		return BindingBuilder.bind(queue()).to(directExchange()).with("direct.key1");
	}
	
	/**
	 * Topic模式 交换机Exchange
	 *  路由键：以 . 隔开的字符串，相当于该队列的一个标识
	 *  * 表示一个词 例如：agreements..b.* 表示第四个单词是b绑定的队列
	 *  # 表示一个或者多个词 例如：topic.queue1.# 表示投送以topic.queue1开头绑定的所有队列
	 * */
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1, true);
	}
	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2, true);
	}
	@Bean
	public TopicExchange topicExchage(){
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchage()).with("topic.key1");
	}
	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchage()).with("topic.#");
	}
	/**
	 * Fanout模式 交换机Exchange
	 * "扇出" 全部投送： 投送绑定该交换机的所有队列
	 * */
	@Bean
	public FanoutExchange fanoutExchage(){
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	@Bean
	public Binding FanoutBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(fanoutExchage());
	}
	@Bean
	public Binding FanoutBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(fanoutExchage());
	}
	/**
	 * Header模式 交换机Exchange
	 * 设置header attribute参数类型，相较于direct和toipc模式固定使用routingKey，该模式可以自定义key-value，
	 * 即交换机以队列绑定时有一组key-value，消息也有一组key-value（注意是一组，而不是一对），当匹配其中一对以上就投送
	 * */
	@Bean
	public Queue headerQueue1() {
		return new Queue(HEADER_QUEUE, true);
	}
	@Bean
	public HeadersExchange headersExchage(){
		return new HeadersExchange(HEADERS_EXCHANGE);
	}
	@Bean
	public Binding headerBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("header1", "value1");
		map.put("header2", "value2");
		return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).match();
	}
	
	
}
