package org.example.common.constant;

import org.springblade.core.launch.constant.AppConstant;

/**
 * 通用常量
 *
 * @author Chill
 */
public interface LauncherConstant {

	/**
	 * nacos namespace id
	 */
	String NACOS_NAMESPACE = "ea06454c-e35a-40a9-8813-f1c67a5c1c36";

	/**
	 * nacos dev 地址
	 */
	String NACOS_DEV_ADDR = "192.168.236.193:8848";

	/**
	 * nacos prod 地址
	 */
	String NACOS_PROD_ADDR = "172.30.0.48:8848";

	/**
	 * nacos test 地址
	 */
	String NACOS_TEST_ADDR = "172.30.0.48:8848";

	/**
	 * sentinel dev 地址
	 */
	String SENTINEL_DEV_ADDR = "127.0.0.1:8858";

	/**
	 * sentinel prod 地址
	 */
	String SENTINEL_PROD_ADDR = "172.30.0.58:8858";

	/**
	 * sentinel test 地址
	 */
	String SENTINEL_TEST_ADDR = "172.30.0.58:8858";

	/**
	 * zipkin dev 地址
	 */
	String ZIPKIN_DEV_ADDR = "http://127.0.0.1:9411";

	/**
	 * zipkin prod 地址
	 */
	String ZIPKIN_PROD_ADDR = "http://172.30.0.58:9411";

	/**
	 * zipkin test 地址
	 */
	String ZIPKIN_TEST_ADDR = "http://172.30.0.58:9411";

	/**
	 * 动态获取nacos地址
	 *
	 * @param profile 环境变量
	 * @return addr
	 */
	static String nacosAddr(String profile) {
		switch (profile) {
			case (AppConstant.PROD_CODE):
				return NACOS_PROD_ADDR;
			case (AppConstant.TEST_CODE):
				return NACOS_TEST_ADDR;
			default:
				return NACOS_DEV_ADDR;
		}
	}

	/**
	 * 动态获取sentinel地址
	 *
	 * @param profile 环境变量
	 * @return addr
	 */
	static String sentinelAddr(String profile) {
		switch (profile) {
			case (AppConstant.PROD_CODE):
				return SENTINEL_PROD_ADDR;
			case (AppConstant.TEST_CODE):
				return SENTINEL_TEST_ADDR;
			default:
				return SENTINEL_DEV_ADDR;
		}
	}

	/**
	 * 动态获取zipkin地址
	 *
	 * @param profile 环境变量
	 * @return addr
	 */
	static String zipkinAddr(String profile) {
		switch (profile) {
			case (AppConstant.PROD_CODE):
				return ZIPKIN_PROD_ADDR;
			case (AppConstant.TEST_CODE):
				return ZIPKIN_TEST_ADDR;
			default:
				return ZIPKIN_DEV_ADDR;
		}
	}

	static String nacosNameSpace(String profile) {
		switch (profile) {
			case (AppConstant.PROD_CODE):
				return ZIPKIN_PROD_ADDR;
			case (AppConstant.TEST_CODE):
				return ZIPKIN_TEST_ADDR;
			default:
				return NACOS_NAMESPACE;
		}
	}
}
