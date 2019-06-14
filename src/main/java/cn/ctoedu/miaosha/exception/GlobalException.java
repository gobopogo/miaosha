package cn.ctoedu.miaosha.exception;

import cn.ctoedu.miaosha.result.CodeMsg;

/**
 * @Author missli
 * @Description 全局异常
 * @Date 2019/6/13 15:23
 */
public class GlobalException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private CodeMsg cm;
	
	public GlobalException(CodeMsg cm) {
		super(cm.toString());
		this.cm = cm;
	}

	public CodeMsg getCm() {
		return cm;
	}

}
