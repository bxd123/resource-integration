/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example.system.user;

import org.example.common.annotations.IntegrationCloudApplication;
import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;

/**
 * 用户启动器
 *
 * @author Chill
 */
@IntegrationCloudApplication
public class UserApplication {

	public static void main(String[] args) {
		BladeApplication.run(AppConstant.APPLICATION_USER_NAME, UserApplication.class, args);
	}

}
